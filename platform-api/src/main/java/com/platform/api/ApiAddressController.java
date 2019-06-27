package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.AddressVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiAddressService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "收货地址")
@RestController
@RequestMapping("/api/address")
public class ApiAddressController extends ApiBaseAction {
    @Autowired
    private ApiAddressService addressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @GetMapping("list")
    public Object list(@LoginUser UserVo loginUser) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", loginUser.getUserId());
        List<AddressVo> addressEntities = addressService.queryList(param);
        return toResponsSuccess(addressEntities);
    }

    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "Integer")})
    @GetMapping("detail")
    public Object detail(Integer id, @LoginUser UserVo loginUser) {
        AddressVo entity = addressService.queryObject(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getUserId())) {
            return toResponsObject(403, "您无权查看", "");
        }
        return toResponsSuccess(entity);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("save")
    public Object save(@LoginUser UserVo loginUser) {
        JSONObject addressJson = this.getJsonRequest();
        AddressVo entity = new AddressVo();

        if (null != addressJson) {
            entity.setId(addressJson.getLong("id"));
            entity.setUserId(loginUser.getUserId());
            entity.setUserName(addressJson.getString("userName"));
            entity.setPostalCode(addressJson.getString("postalCode"));
            entity.setProvinceName(addressJson.getString("provinceName"));
            entity.setCityName(addressJson.getString("cityName"));
            entity.setCountyName(addressJson.getString("countyName"));
            entity.setDetailInfo(addressJson.getString("detailInfo"));
            entity.setNationalCode(addressJson.getString("nationalCode"));
            entity.setTelNumber(addressJson.getString("telNumber"));
            entity.setIs_default(addressJson.getInteger("is_default"));
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", loginUser.getUserId());
        List<AddressVo> addressEntities = addressService.queryList(param);
        if(addressEntities.size()==0){//第一次添加设置为默认地址
            entity.setIs_default(1);
        }
        //设置默认地址
        if(entity.getIs_default()==1){
            AddressVo entity1 = new AddressVo();
            entity1.setUserId(loginUser.getUserId());
            entity1.setIs_default(0);
            addressService.updateIsDefault(entity1);
        }
        if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            addressService.save(entity);
        } else {
            addressService.update(entity);
        }
        return toResponsSuccess(entity);
    }

    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("delete")
    public Object delete(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = this.getJsonRequest();
        Integer id = jsonParam.getIntValue("id");

        AddressVo entity = addressService.queryObject(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getUserId())) {
            return toResponsObject(403, "您无权删除", "");
        }
        addressService.delete(id);
        return toResponsSuccess("");
    }
    /**
     * 获取用户的收货地址
     */
    @IgnoreAuth
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @GetMapping("addressUserlist")
    public Object addressUserlist(@LoginUser UserVo loginUser) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", this.getUserId().intValue());
        List<AddressVo> addressEntities = addressService.queryaddressUserlist(param);
        return toResponsSuccess(addressEntities);
    }
  
}