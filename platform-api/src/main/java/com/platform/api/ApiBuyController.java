package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.entity.*;
import com.platform.service.ApiGoodsService;
import com.platform.service.ApiProductService;
import com.platform.service.GroupBuyingDetailedService;
import com.platform.service.GroupBuyingService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "商品购买")
@RestController
@RequestMapping("/api/buy")
public class ApiBuyController extends ApiBaseAction {

    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiProductService productService;
    @Autowired
    private GroupBuyingService groupBuyingService;
    @Autowired
    private GroupBuyingDetailedService groupBuyingDetailedService;
    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public Object addBuy(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        
        //判断商品是否可以购买
        GoodsVo goodsInfo = goodsService.queryObject(goodsId);
        if (null == goodsInfo || goodsInfo.getIs_delete() == 1 || goodsInfo.getIs_on_sale() != 1) {
            return this.toResponsObject(400, "商品已下架", "");
        }
        //取得规格的信息,判断规格库存
        ProductVo productInfo = productService.queryObject(productId);
        if (null == productInfo || productInfo.getGoods_number() < number) {
            return this.toResponsObject(400, "库存不足", "");
        }
        
        
        BuyGoodsVo goodsVo = new BuyGoodsVo();
        goodsVo.setGoodsId(goodsId);
        goodsVo.setProductId(productId);
        goodsVo.setNumber(number);
        
        J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId() + "", goodsVo);
        return toResponsMsgSuccess("添加成功");
    }

    /**
     * 获取用户拼团列表
     *
     * @return
     */
    @IgnoreAuth
    @ApiOperation(value = "获取用户拼团列表")
    @PostMapping("/getGroupBuyList")
    public  Object getGroupBuyList(@LoginUser  UserVo loginUser){
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Map map=new HashMap<>();
        map.put("goodsId",goodsId);
        map.put("types","0");
        int groupNum=0;
        List<GroupBuyingVo> groupBuyingEntityList = groupBuyingService.queryList(map);
        if(groupBuyingEntityList!=null){
            for(GroupBuyingVo groupBuyingVo:groupBuyingEntityList ){
                groupNum=groupNum+groupBuyingVo.getGroupNum();
                Map map1=new HashMap();
                map1.put("group_buying_id",groupBuyingVo.getId());
                map1.put("sidx","pay_time");
                map1.put("order","asc");

                List<GroupBuyingDetailedVo> groupBuyingDetailedVoList =groupBuyingDetailedService.queryList(map1);
               // groupBuyingEntity.setGroupBuyingDetailedList(groupBuyingDetailedVoList);
                for(GroupBuyingDetailedVo groupBuyingDetailedVo:groupBuyingDetailedVoList){
                    groupBuyingDetailedVo.setUserName(Base64.decode(groupBuyingDetailedVo.getUserName()));
                }

                groupBuyingVo.setGroupBuyingDetailedList(groupBuyingDetailedVoList);
            }
        }
        Map retMap=new HashMap();
        retMap.put("groupBuyingEntityList",groupBuyingEntityList);
        retMap.put("groupNum",groupNum);

     return toResponsSuccess(retMap);
    }


}
