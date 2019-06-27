package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.RedisUtils;
import com.platform.util.wechat.WechatRefundApiResult;
import com.platform.util.wechat.WechatUtil;
import com.platform.utils.CharUtil;
import com.platform.utils.MapUtils;
import com.platform.utils.ResourceUtil;
import com.platform.utils.XmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "商户支付")
@RestController
@RequestMapping("/api/pay")
public class ApiPayController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ApiOrderService orderService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private UserRecordSer userRecordSer;
    @Autowired
    private MlsUserSer mlsUserSer;
    @Autowired
    private ApiGoodsService apiGoodsService;
    @Autowired
    private GroupBuyingService groupBuyingService;
    @Autowired
    private GroupBuyingDetailedService groupBuyingDetailedService;
    /**
     */
    @ApiOperation(value = "跳转")
    @GetMapping("index")
    public Object index() {
        //
        return toResponsSuccess("");
    }

    /**
     * 获取支付的请求参数
     */
    @ApiOperation(value = "获取支付的请求参数")
    @GetMapping("prepay")
    public Object payPrepay(@LoginUser UserVo loginUser, Integer orderId) {
    	
    	String allOrderId = orderService.queryObject(orderId).getAll_order_id();
    	
    	List<OrderVo> orders = orderService.queryByAllOrderId(allOrderId);
    	
    	String body = null;
    	BigDecimal allPrice = BigDecimal.ZERO;
    	for(OrderVo o : orders) {
    		if (null == o) {
                return toResponsObject(400, "订单已取消", "");
            }

            if (o.getPay_status().compareTo(1)> 0) {
                return toResponsObject(400, "订单已支付，请不要重复操作", "");
            }
            
            
            //计算总价格
            allPrice = allPrice.add(o.getActual_price());
            
            if(!(body == null)) {
            	continue;
            }
            Map orderGoodsParam = new HashMap();
            orderGoodsParam.put("order_id", o.getId());
            //订单的商品
            List<OrderGoodsVo> orderGoods = orderGoodsService.queryList(orderGoodsParam);
            if (null != orderGoods) {
                for (OrderGoodsVo goodsVo : orderGoods) {
                    if(body == null) {
                        body = goodsVo.getGoods_name();
                    }else {
                        body = body+"等";
                        break;
                    }

                }
               
            }
    	}

        String nonceStr = CharUtil.getRandomString(32);

        //https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=3
        Map<Object, Object> resultObj = new TreeMap();

        try {
            Map<Object, Object> parame = new TreeMap<Object, Object>();
            parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
            // 商家账号。
            parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            parame.put("nonce_str", randomStr);
            // 商户订单编号
            parame.put("out_trade_no", allOrderId);
            // 商品描述
            parame.put("body", body);
            //支付金额
            parame.put("total_fee", allPrice.multiply(new BigDecimal(100)).intValue());
            System.out.println("***************"+parame.get("total_fee")+"***************");
            //System.out.println(orderInfo.getActual_price().multiply(new BigDecimal(100)).intValue() + "***************");
            //parame.put("total_fee", 1);
            // 回调地址
            parame.put("notify_url", ResourceUtil.getConfigByName("wx.notifyUrl"));
            // 交易类型APP
            parame.put("trade_type", ResourceUtil.getConfigByName("wx.tradeType"));
            parame.put("spbill_create_ip", getClientIp());
            parame.put("openid", loginUser.getWeixin_openid());
            String sign = WechatUtil.arraySign(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
            // 数字签证
            parame.put("sign", sign);

            String xml = MapUtils.convertMap2Xml(parame);
            System.out.println("***************xml="+xml+"***************");
            Map<String, Object> resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.uniformorder"), xml));
            System.out.println("================resultUn="+resultUn+"================");
            
            // 响应报文
            String return_code = MapUtils.getString("return_code", resultUn);
            String return_msg = MapUtils.getString("return_msg", resultUn);
            //
            if (return_code.equalsIgnoreCase("FAIL")) {
                return toResponsFail("支付失败," + return_msg);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                // 返回数据
                String result_code = MapUtils.getString("result_code", resultUn);
                String err_code_des = MapUtils.getString("err_code_des", resultUn);
                if (result_code.equalsIgnoreCase("FAIL")) {
                    return toResponsFail("支付失败," + err_code_des);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    String prepay_id = MapUtils.getString("prepay_id", resultUn);
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", ResourceUtil.getConfigByName("wx.appId"));
                    //resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    String paySign = WechatUtil.arraySign(resultObj, ResourceUtil.getConfigByName("wx.paySignKey"));
                    resultObj.put("paySign", paySign);
                    
                    OrderVo newOrder = new OrderVo();
                    newOrder.setPay_id(prepay_id);
                    newOrder.setPay_status(1);
                    newOrder.setAll_order_id(allOrderId.toString());
                    orderService.updateStatus(newOrder);
                    
                    //redis设置订单状态
                    RedisUtils.set(allOrderId.toString(), "51", 60*60*24);
                    return toResponsObject(0, "微信统一订单下单成功", resultObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("下单失败,error=" + e.getMessage());
        }
        return toResponsFail("下单失败");
    }

    /**
     * 微信查询订单状态
     */
    @ApiOperation(value = "查询订单状态")
    @GetMapping("query")
    public Object orderQuery(@LoginUser UserVo loginUser, Integer orderId) {
        if (orderId == null) {
            return toResponsFail("订单不存在");
        }
        
        OrderVo order = orderService.queryObject(orderId);
        
        //处理订单的redis状态
    	String value = RedisUtils.get(order.getAll_order_id());
    	if(value != null && "51".equals(value)) {
    		RedisUtils.del(orderId.toString());
    	}else {
    		//异步回调已结操作过
    		return toResponsMsgSuccess("已完成");
    	}
        
        if(order.getPay_status().intValue() == 2) {
            return toResponsMsgSuccess("支付成功");
        }
        Map<Object, Object> parame = new TreeMap<Object, Object>();
        parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
        // 商家账号。
        parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        parame.put("nonce_str", randomStr);
        // 商户订单编号
        parame.put("out_trade_no", order.getAll_order_id());

        String sign = WechatUtil.arraySign(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
        // 数字签证
        parame.put("sign", sign);

        String xml = MapUtils.convertMap2Xml(parame);
        logger.info("xml:" + xml);
        Map<String, Object> resultUn = null;
        try {
            resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.orderquery"), xml));
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("查询失败,error=" + e.getMessage());
        }
        // 响应报文
        String return_code = MapUtils.getString("return_code", resultUn);
        String return_msg = MapUtils.getString("return_msg", resultUn);

        if (!"SUCCESS".equals(return_code)) {
            return toResponsFail("查询失败,error=" + return_msg);
        }

        String trade_state = MapUtils.getString("trade_state", resultUn);
        if ("SUCCESS".equals(trade_state)) {
            // 更改订单状态
            // 业务处理
        	order.setPay_status(2);
        	order.setOrder_status(201);
        	order.setShipping_status(0);
        	order.setPay_time(new Date());
            orderService.updateStatus(order);
            
            //1购物车、2普通、3秒杀、4团购
            String orderType = order.getOrder_type();
            //只有购物车、普通购买有分润。
            if(orderType == null || "1".equals(orderType) || "2".equals(orderType)) {
            	Map<String, Object> map = new HashMap<String, Object>();
                map.put("all_order_id", order.getAll_order_id());	
                List<OrderVo> lists = orderService.queryList(map);
                OrderVo vo = null;
                for(OrderVo v : lists) {
                	vo = v;
                	try {
                    	//调用分销接口(现在支付成功就分润，后期要改造变成收货后，或者变成不可以体现的分润)
                    	fx(new Long(vo.getPromoter_id()), vo.getBrokerage(), vo.getOrder_price(), vo.getId(), vo.getMerchant_id());
                    }catch(Exception e) {
                    	System.out.println("================分销错误开始================");
                    	e.printStackTrace();
                    	System.out.println("================分销错误结束================");
                    }
                }
            }
            //如果是团购在付款成功后要去增加团购记录表
            else if("4".equals(orderType)){
            	//获取订单商品表信息
            	Map<String, Object> orderGoodsMap = new HashMap<String, Object>();
            	orderGoodsMap.put("order_id", order.getId());
            	List<OrderGoodsVo> OrderGoodsVos = orderGoodsService.queryList(orderGoodsMap);
            	OrderGoodsVo orderGoods = OrderGoodsVos.get(0);
            	
            	//查询根据团ID查询是否已经有创建，有则增加数量，没有则创建
                GroupBuyingVo gb = groupBuyingService.queryObject(order.getGroup_buying_id());
            	if(gb == null) {
            		//创建团购主表
            		gb = new GroupBuyingVo();
            		gb.setId(order.getGroup_buying_id());
            		gb.setGoodsId(orderGoods.getGoods_id());
                	gb.setGoodsName(orderGoods.getGoods_name());
                	gb.setGroupNum(1);
                	GoodsVo goods = apiGoodsService.queryObject(orderGoods.getGoods_id());
                	gb.setSuccessNum(goods.getSuccess_people());//去要从商品表中取值
                	gb.setMerchantId(goods.getMerchantId());
                	Calendar calendar = new GregorianCalendar();
                	Date date = new Date();
                	calendar.setTime(date); 
                	calendar.add(calendar.DATE,1);//把日期往后增加一天
                	gb.setEndTime(calendar.getTime());
                	groupBuyingService.save(gb);
                	
            	}else {
            		gb.setGroupNum(gb.getGroupNum() + 1);
            		//根据判断人数判断是否拼团成功
            		if(gb.getGroupNum() >= gb.getSuccessNum()) {
            			gb.setTypes(1);//拼团成功状态
            		}
            		groupBuyingService.update(gb);
            	}
            	
            	//记录团购买人信息
            	GroupBuyingDetailedVo gbd = new GroupBuyingDetailedVo();
            	gbd.setGroupBuyingId(gb.getId());
            	gbd.setPayTime(new Date());
            	gbd.setUserId(loginUser.getUserId());
            	gbd.setUserName(loginUser.getUsername());
            	gbd.setUserImg(loginUser.getAvatar());
            	groupBuyingDetailedService.save(gbd);
            }
            
            //感觉没用
            //mlsUserSer.upUserProfit(order);
            return toResponsMsgSuccess("支付成功");
        } else if ("USERPAYING".equals(trade_state)) {
            // 重新查询 正在支付中
            Integer num = (Integer) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + order.getAll_order_id() + "");
            if (num == null) {
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "", 1);
                this.orderQuery(loginUser, orderId);
            } else if (num <= 3) {
                J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId);
                this.orderQuery(loginUser, orderId);
            } else {
                return toResponsFail("查询失败,error=" + trade_state);
            }

            OrderVo orderInfo = new OrderVo();
            orderInfo.setAll_order_id(order.getAll_order_id());
            orderInfo.setPay_status(0);
            orderService.updateStatus(orderInfo);
        } else {
            // 失败
        	OrderVo orderInfo = new OrderVo();
            orderInfo.setAll_order_id(order.getAll_order_id());
            orderInfo.setPay_status(0);
            orderService.updateStatus(orderInfo);
            return toResponsFail("查询失败,error=" + trade_state);
        }
        return toResponsFail("查询失败，未知错误");
    }

    
    /**
     * 微信订单回调接口
     *
     * @return
     */
    @ApiOperation(value = "微信订单回调接口")
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @IgnoreAuth
    @ResponseBody
    public void notify(HttpServletRequest request, HttpServletResponse response) {
    	try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String reponseXml = new String(out.toByteArray(), "utf-8");

            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);

        	//处理订单的redis状态
        	String value = RedisUtils.get(result.getOut_trade_no().toString());
        	if(value != null && "51".equals(value)) {
        		RedisUtils.del(result.getOut_trade_no().toString());
        	}else {
        		//查询支付已结操作过
        		response.getWriter().write(setXml("SUCCESS", "OK"));
        		return;
        	}
            
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付失败");
                response.getWriter().write(setXml("SUCCESS", "OK"));
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                Map<Object, Object> retMap = XmlUtil.xmlStrToTreeMap(reponseXml);
            	String sign = WechatUtil.arraySign(retMap, ResourceUtil.getConfigByName("wx.paySignKey"));
            	if(!sign.equals(result.getSign())) {//判断签名
            		return;
            	}
                
                // 更改订单状态
                // 业务处理
                OrderVo orderInfo = new OrderVo();
                orderInfo.setAll_order_id(result.getOut_trade_no());
                orderInfo.setPay_status(2);
                orderInfo.setOrder_status(201);
                orderInfo.setShipping_status(0);
                orderInfo.setPay_time(new Date());
                orderService.updateStatus(orderInfo);
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("all_order_id", result.getOut_trade_no());	
                List<OrderVo> lists = orderService.queryList(map);
                OrderVo vo = null;
                for(OrderVo v : lists) {
                	vo = v;
                	try {
                    	//调用分销接口(现在支付成功就分润，后期要改造变成收货后，或者变成不可以体现的分润)
                    	fx(new Long(vo.getPromoter_id()), vo.getBrokerage(), vo.getOrder_price(), vo.getId(), vo.getMerchant_id());
                    }catch(Exception e) {
                    	System.out.println("================分销错误开始================");
                    	e.printStackTrace();
                    	System.out.println("================分销错误结束================");
                    }
                }
                
                
                response.getWriter().write(setXml("SUCCESS", "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

//    /**
//     * 订单退款请求（暂无使用）
//     */
//    @ApiOperation(value = "订单退款请求")
//    @PostMapping("refund")
//    public Object refund(Integer orderId) {
//    	
//    	
//        OrderVo orderInfo = orderService.queryObject(orderId);
//
//        if (null == orderInfo) {
//            return toResponsObject(400, "订单已取消", "");
//        }
//
//        if (orderInfo.getOrder_status() == 401 || orderInfo.getOrder_status() == 402) {
//            return toResponsObject(400, "订单已退款", "");
//        }
//
//        if (orderInfo.getPay_status() != 2) {
//            return toResponsObject(400, "订单未付款，不能退款", "");
//        }
//
//		WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getAll_order_id().toString(),
//				orderInfo.getAll_price().doubleValue(), orderInfo.getAll_price().doubleValue());
//        if (result.getResult_code().equals("SUCCESS")) {
//            if (orderInfo.getOrder_status() == 201) {
//                orderInfo.setOrder_status(401);
//            } else if (orderInfo.getOrder_status() == 300) {
//                orderInfo.setOrder_status(402);
//            }
//            
//            //修改订单状态
//            OrderVo neworderInfo = new OrderVo();
//            neworderInfo.setAll_order_id(orderInfo.getAll_order_id());
//            neworderInfo.setOrder_status(orderInfo.getOrder_status());
//            orderService.updateStatus(orderInfo);
//            
//            //还原优惠券使用状态
//			UserCouponVo uc = new UserCouponVo();
//			uc.setId(orderInfo.getCoupon_id());
//			uc.setCoupon_status(1);
//			uc.setUsed_time(null);
//			userCouponService.updateCouponStatus(uc);
//            
//            return toResponsObject(400, "成功退款", "");
//        } else {
//            return toResponsObject(400, "退款失败", "");
//        }
//    }


    //返回微信服务
    public static String setXml(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    //模拟微信回调接口
    public static String callbakcXml(String orderNum) {
        return "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[" + orderNum + "]]></out_trade_no>  <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
    }
    
    
    /**
     * 计算分润
     * @param userId		用户Id
     * @param fx_money		分销的分润金额
     * @param order_price	订单金额
     * @param orderId		订单ID
     */
    @ApiOperation(value = "微信订单回调接口")
    @RequestMapping(value = "/fx", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @IgnoreAuth
    public void fx(Long userId, BigDecimal fx_money, BigDecimal orderPrice, int orderId, Integer merchantId) {
    	if(userId == null || userId==0L || fx_money.compareTo(BigDecimal.ZERO)==0) {
    		System.out.println("*****分润参数错误userId="+userId+",fx_money="+fx_money);
    		return;
    	}
    	
    	
    	Map<String, Object> map = mlsUserSer.getEntityMapper().getSysUserByMid(new Long(merchantId));
    	if(map == null) {
    		System.out.println("==============分销报错："+merchantId +"=====没有找到供应商");
    		return;
    	}
    	Integer order_price = orderPrice.multiply(new BigDecimal("100")).intValue();
    	//MlsUserEntity2 user = mlsUserSer.queryObject(userId);
    	MlsUserEntity2 user = mlsUserSer.getEntityMapper().getById(userId);
    	if(user == null) {
    		System.out.println("==============分销报错："+userId +"=====没有找到用户");
    		return;
    	}
    	BigDecimal fx = new BigDecimal(map.get("FX").toString());//上级分销比例
    	BigDecimal fx1 = new BigDecimal(map.get("FX1").toString());//上级分销比例
    	BigDecimal fx2 = new BigDecimal(map.get("FX2").toString());//上上级分销比例
    	BigDecimal pfx = new BigDecimal(map.get("PFX").toString());//平台分销比例
    	Integer fx1_money = 0;//上级分润金额
    	Integer fx2_money = 0;//上上级分润金额
    	Integer pfx_money = 0;//上上级分润金额
    	Integer user_money = 0;//本人分润金额
    	Integer brand_money = 0;//供应商金额
    	Long fid1 = 0L;//上级
    	Long fid2 = 0L;//上上级
    	
    	//读取系统分润比例
//    	String sys_fx = sysConfigService.getValue("sys_fx", null);
//    	if(StringUtils.isNotBlank(sys_fx)) {
//    		sys_fx_money = computeFx(orderPrice, new BigDecimal(sys_fx));
//    		addUserRecord(0L, sys_fx_money, userId, order_price, orderId);//0L固定留给平台ID
//    	}
    	
    	//分人分润=总金额-系统分润（默认0）
    	//个人分润金额包括自己的分润、上级分润和上上级分润
    	//第一情况：没有上级或者上上级的情况分润给个人(现在使用)
    	//第二情况：没有上级或者上上级的情况分润给系统
    	
    	//获得上级和上上级ID
    	fid1 = user.getFid();
    	if(fid1 != null && fid1>0) {
    		 MlsUserEntity2 fuser = mlsUserSer.queryObject(fid1);
    		 if(fuser != null) {
    			 fid2 = fuser.getFid();
    		 }
    	}
    	//判断是否存在上级和上上级增加分润比例
    	if(fxblIsNull(fx1) == true && fid1 != null && fid1 > 0) {
    		fx1_money = computeFx(fx_money, fx1);
    		addUserRecord(fid1, fx1_money, userId, order_price, orderId);
    	}
    	if(fxblIsNull(fx2) == true && fid2 != null && fid2 > 0) {
    		fx2_money = computeFx(fx_money, fx2);
    		addUserRecord(fid2, fx2_money, userId, order_price, orderId);
    	}
    	//计算平台分佣比例
    	if(fxblIsNull(pfx)) {
    		pfx_money = computeFx(fx_money, pfx);
    		addUserRecord(0L, pfx_money, userId, order_price, orderId);
    	}
    	//计算自己的分润比例
    	user_money = computeFx(fx_money, fx);
    	addUserRecord(user.getMlsUserId(), user_money, userId, order_price, orderId);
    	//计算供应商金额
    	brand_money = order_price - user_money - fx1_money - fx2_money - pfx_money;
    	//查询供应商ID
    	MlsUserEntity2 u = mlsUserSer.getEntityMapper().findByMerchantId(merchantId);
    	addUserRecord(u.getMlsUserId(), brand_money, userId, order_price, orderId);//1L固定留给供应商ID，多商户版本填写他的MLS_USER_ID
    }
    
    /**
     * 增加分润记录
     * @param userId
     * @param price
     */
    private void addUserRecord(Long userId, int price, Long fxuser, int order_price, int orderId) {
    	UserRecord ur = new UserRecord();
    	ur.setMlsUserId(userId);
    	ur.setTypes(2);
    	ur.setTypesStr("分润");
    	ur.setPrice(price);
    	ur.setRemarks("id:"+fxuser+"分润，金额："+price);
    	ur.setOrderId(orderId);
    	userRecordSer.save(ur);
    	
    	MlsUserEntity2 mlsUserVo= new MlsUserEntity2();
    	mlsUserVo.setMlsUserId(userId);
		mlsUserVo.setTodaySales(order_price);
		mlsUserVo.setGetProfit(price);
		mlsUserSer.updateMoney(mlsUserVo);
    }
    
    /**
     * 根据金额和比例计算出金额
     * @param all 金额
     * @param bl 比例
     * @return
     */
    private Integer computeFx(BigDecimal all, BigDecimal bl) {
    	return all.multiply(bl).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).intValue();
    	//return all.multiply(bl).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
    }
    
    private Boolean fxblIsNull(BigDecimal fx) {
    	if(fx != null && fx.compareTo(BigDecimal.ZERO) > 0) {
    		return true;
    	}
    	return false;
    }
}