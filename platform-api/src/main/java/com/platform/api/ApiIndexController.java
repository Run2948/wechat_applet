package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.platform.annotation.APPLoginUser;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.*;
import com.platform.service.*;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "首页接口文档")
@RestController
@RequestMapping("/api/index")
public class ApiIndexController extends ApiBaseAction {
    @Autowired
    private ApiAdService adService;
    @Autowired
    private ApiChannelService channelService;
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiBrandService brandService;
    @Autowired
    private ApiTopicService topicService;
    @Autowired
    private ApiCategoryService categoryService;
    @Autowired
    private ApiCartService cartService;
    @Autowired
    private LifeServiceSer lifeServiceSer;
    /**
     * 测试
     */
    @IgnoreAuth
    @GetMapping(value = "test")
    public Object test() {
        return toResponsMsgSuccess("请求成功yyy");
    }

    /**
     * app首页
     */
    @ApiOperation(value = "首页")
    @IgnoreAuth
    @GetMapping(value = "index")
    public Object index() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ad_position_id", 1);
        List<AdVo> banner = adService.queryList(param);
        resultObj.put("banner", banner);
        //
        param = new HashMap<String, Object>();
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param);
        resultObj.put("channel", channel);
        //
        param = new HashMap<String, Object>();
        param.put("is_new", 1);
        param.put("is_delete", 0);
        param.put("is_on_sale", 1);
        param.put("fields", "id, name, list_pic_url, retail_price");
        PageHelper.startPage(0, 4, false);
        List<GoodsVo> newGoods = goodsService.queryList(param);
        resultObj.put("newGoodsList", newGoods);
        //
        param = new HashMap<String, Object>();
        param.put("is_hot", "1");
        param.put("is_on_sale", 1);
        param.put("is_delete", 0);
        PageHelper.startPage(0, 3, false);
        List<GoodsVo> hotGoods = goodsService.queryHotGoodsList(param);
        resultObj.put("hotGoodsList", hotGoods);
        // 当前购物车中
        List<CartVo> cartList = new ArrayList<CartVo>();
        if (null != getUserId()) {
            //查询列表数据
            Map<String, Object> cartParam = new HashMap<String, Object>();
            cartParam.put("user_id", getUserId());
            cartList = cartService.queryList(cartParam);
        }
        if (null != cartList && cartList.size() > 0 && null != hotGoods && hotGoods.size() > 0) {
            for (GoodsVo goodsVo : hotGoods) {
                for (CartVo cartVo : cartList) {
                    if (goodsVo.getId().equals(cartVo.getGoods_id())) {
                        goodsVo.setCart_num(cartVo.getNumber());
                    }
                }
            }
        }
        //
        param = new HashMap<String, Object>();
        param.put("is_new", 1);
        param.put("sidx", "new_sort_order ");
        param.put("order", "asc ");
        param.put("offset", 0);
        param.put("limit", 4);
        List<BrandVo> brandList = brandService.queryList(param);
        resultObj.put("brandList", brandList);

        param = new HashMap<String, Object>();
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param);
        resultObj.put("topicList", topicList);

        param = new HashMap<String, Object>();
        param.put("parent_id", 0);
        param.put("notName", "推荐");//<>
        List<CategoryVo> categoryList = categoryService.queryList(param);
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryVo categoryItem : categoryList) {
            param.remove("fields");
            param.put("parent_id", categoryItem.getId());
            List<CategoryVo> categoryEntityList = categoryService.queryList(param);
            List<Integer> childCategoryIds = new ArrayList<>();
            for (CategoryVo categoryEntity : categoryEntityList) {
                childCategoryIds.add(categoryEntity.getId());
            }
            //
            param = new HashMap<String, Object>();
            param.put("categoryIds", childCategoryIds);
            param.put("fields", "id as id, name as name, list_pic_url as list_pic_url, retail_price as retail_price");
            PageHelper.startPage(0, 7, false);
            List<GoodsVo> categoryGoods = goodsService.queryList(param);
            Map<String, Object> newCategory = new HashMap<String, Object>();
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        
        //秒杀列表
        resultObj.put("killList", goodsService.queryKillList());
        return toResponsSuccess(resultObj);
    }


    /**
     * app首页
     */
    @ApiOperation(value = "新商品信息")
    @IgnoreAuth
    @GetMapping(value = "newGoods")
    public Object newGoods() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("is_new", 1);
        param.put("is_delete", 0);
        param.put("is_on_sale", 1);
//        param.put("fields", "id, name, list_pic_url, retail_price");
        PageHelper.startPage(0, 4, false);
        List<GoodsVo> newGoods = goodsService.queryList(param);
        resultObj.put("newGoodsList", newGoods);
        //

        return toResponsSuccess(resultObj);
    }
    
    /**
     * app首页秒杀商品
     */
    @ApiOperation(value = "秒杀商品")
    @IgnoreAuth
    @GetMapping(value = "secKill")
    public Object secKill() {
    	Map<String, Object> resultObj = new HashMap<String, Object>();
    	//秒杀列表
        resultObj.put("killList", goodsService.queryKillList());
        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "新热门商品信息")
    @IgnoreAuth
    @GetMapping(value = "hotGoods")
    public Object hotGoods() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("is_hot", "1");
        param.put("is_delete", 0);
        param.put("is_on_sale", 1);
        PageHelper.startPage(0, 3, false);
        List<GoodsVo> hotGoods = goodsService.queryHotGoodsList(param);
        resultObj.put("hotGoodsList", hotGoods);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "新热门商品信息")
    @GetMapping(value = "newHotGoods")
    public Object newHotGoods(@APPLoginUser MlsUserEntity2 loginUser, @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "5") Integer size) {
    	  //查询列表数据
        Map params = new HashMap();
        params.put("is_delete", 0);
        params.put("is_on_sale", 1);
        params.put("is_hot", "1");
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        //是否显示其他商户 0:不显示，1:显示
        if(loginUser!=null &&loginUser.getAllShow()==0){
            params.put("merchantId",loginUser.getMerchantId());
        }
        Query query = new Query(params);
        List<GoodsVo> goodsList = goodsService.queryFxList(query);
        int total = goodsService.queryFxTotal(query);
        for(GoodsVo vo : goodsList) {
	    	vo.setDiscount(vo.getRetail_price().multiply(new BigDecimal("10")).divide(vo.getMarket_price(), 1, BigDecimal.ROUND_HALF_UP).toString());
	    	vo.setUser_brokerage_price(vo.getRetail_price().multiply(new BigDecimal(vo.getBrokerage_percent())).multiply(new BigDecimal(loginUser.getFx()).divide(new BigDecimal("10000"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
        ApiPageUtils pageUtil = new ApiPageUtils(goodsList, total, query.getLimit(), query.getPage());
        return toResponsSuccess(pageUtil);
    }
    
    @ApiOperation(value = "服务性商品信息")
    @IgnoreAuth
    @GetMapping(value = "serviceGoods")
    public Object serviceGoods(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        
        Map params = new HashMap();
        params.put("is_delete", 0);
        params.put("is_on_sale", 1);
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "is_service");
        params.put("order", "desc");
        //查询列表数据
        Query query = new Query(params);
        List<GoodsVo> serviceGoods = goodsService.queryList(query);
        int total = goodsService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(serviceGoods, total, query.getLimit(), query.getPage());

        return toResponsSuccess(pageUtil);
    }

    @ApiOperation(value = "topic")
    @IgnoreAuth
    @GetMapping(value = "topic")
    public Object topic() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param);
        resultObj.put("topicList", topicList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "brand")
    @IgnoreAuth
    @GetMapping(value = "brand")
    public Object brand() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("is_new", 1);
        param.put("sidx", "new_sort_order ");
        param.put("order", "asc ");
        param.put("offset", 0);
        param.put("limit", 4);
        List<BrandVo> brandList = brandService.queryList(param);
        resultObj.put("brandList", brandList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "category")
    @IgnoreAuth
    @GetMapping(value = "category")
    public Object category() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("parent_id", 0);
        param.put("notName", "推荐");//<>
        List<CategoryVo> categoryList = categoryService.queryList(param);
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryVo categoryItem : categoryList) {
            param.remove("fields");
            param.put("parent_id", categoryItem.getId());
            List<CategoryVo> categoryEntityList = categoryService.queryList(param);
            List<Integer> childCategoryIds = null;
            if (categoryEntityList != null && categoryEntityList.size() > 0) {
                childCategoryIds = new ArrayList<>();
                for (CategoryVo categoryEntity : categoryEntityList) {
                    childCategoryIds.add(categoryEntity.getId());
                }
            }
            //
            param = new HashMap<String, Object>();
            param.put("categoryIds", childCategoryIds);
            param.put("fields", "id as id, name as name, list_pic_url as list_pic_url, retail_price as retail_price");
            param.put("is_delete",0);
            param.put("is_on_sale",1);
            PageHelper.startPage(0, 7, false);
            List<GoodsVo> categoryGoods = goodsService.queryList(param);
            Map<String, Object> newCategory = new HashMap<String, Object>();
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "banner")
    @IgnoreAuth
    @GetMapping(value = "banner")
    public Object banner() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ad_position_id", 1);
        List<AdVo> banner = adService.queryList(param);
        resultObj.put("banner", banner);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "channel")
    @IgnoreAuth
    @GetMapping(value = "channel")
    public Object channel() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param);
        resultObj.put("channel", channel);
        //

        return toResponsSuccess(resultObj);
    }
    
    @ApiOperation(value = "idcardCheck")
    @IgnoreAuth
    @GetMapping(value = "idcardCheck")
    public Object idcardCheck(String idcard, String realname) {
    	Long userId = this.getUserId();
        Map<String, Object> resultObj = new HashMap<String, Object>();
        resultObj = lifeServiceSer.idcardCheck(idcard, realname, userId);
        return toResponsObject(Integer.parseInt(resultObj.get("errno").toString()), resultObj.get("errmsg").toString(), null);
    }
}