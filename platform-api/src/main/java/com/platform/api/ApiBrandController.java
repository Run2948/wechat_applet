package com.platform.api;

import com.platform.annotation.APPLoginUser;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.BrandVo;
import com.platform.entity.MlsUserEntity2;
import com.platform.service.ApiBrandService;
import com.platform.service.ApiGoodsService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class ApiBrandController extends ApiBaseAction {
    @Autowired
    private ApiBrandService brandService;
    @Autowired
    private ApiGoodsService goodsService;

    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @IgnoreAuth
    @GetMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //查询列表数据
        Map params = new HashMap();
        params.put("fields", "id, name, floor_price, app_list_pic_url");
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");

        Query query = new Query(params);
        List<BrandVo> brandEntityList = brandService.queryList(query);
        int total = brandService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(brandEntityList, total, query.getLimit(), query.getPage());
        //
        return toResponsSuccess(pageUtil);
    }
    
    /**
     * 分页获取品牌并且查询每个品牌下四个商品
     */
    @ApiOperation(value = "分页获取品牌")
    @GetMapping("listAndGoods")
    public Object listAndGoods(@APPLoginUser MlsUserEntity2 loginUser, @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "2") Integer size) {
        //查询列表数据
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        //是否显示其他商户 0:不显示，1:显示
        if(loginUser!=null &&loginUser.getAllShow()==0){
            params.put("merchantId",loginUser.getMerchantId());
        }

        Query query = new Query(params);
        List<BrandVo> brandEntityList = brandService.queryList(query);
        for(BrandVo brand : brandEntityList) {
        	brand.setGoods(goodsService.queryTop4(loginUser, brand.getId()));
        }
        int total = brandService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(brandEntityList, total, query.getLimit(), query.getPage());
        //
        return toResponsSuccess(pageUtil);
    }
    
    
    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "获取全部品牌")
    @GetMapping("allList")
    public Object allList(@APPLoginUser MlsUserEntity2 loginUser) {
    	Map params = new HashMap();
        //是否显示其他商户 0:不显示，1:显示
        if(loginUser!=null &&loginUser.getAllShow()==0){
             params.put("merchantId",loginUser.getMerchantId());
         }

        List<BrandVo> brandEntityList = brandService.queryList(params);
        return toResponsSuccess(brandEntityList);
    }

    /**
     * 品牌详情
     */
    @ApiOperation(value = "品牌详情")
    @IgnoreAuth
    @GetMapping("detail")
    public Object detail(@RequestParam Integer id) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        BrandVo entity = brandService.queryObject(id);
        //
        resultObj.put("brand", entity);
        return toResponsSuccess(resultObj);
    }
}