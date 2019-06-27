package com.platform.task;

import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.Kuai100Util;
import com.platform.util.wechat.WechatRefundApiResult;
import com.platform.util.wechat.WechatUtil;
import com.platform.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试定时任务(演示Demo，可删除)
 * <p>
 * testTask为spring bean的名称
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("test2Task")
public class Test2Task {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiOrderService orderService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;
    @Autowired
    private ApiProductService productService;
    @Autowired
	private MlsUserSer mlsUserSer;
    @Autowired
    private UserRecordSer userRecordSer;
    @Autowired
	private GroupBuyingDetailedService  groupBuyingDetailedService;
    @Autowired
	private GroupBuyingService groupBuyingService;
	@Autowired
	private ApiUserCouponService userCouponService;

    public void invalidOrderTask(String params) {
        logger.info("luckDrawTask======我是不带参数的test3方法，正在被执行");
        List<OrderGoodsVo> ordergoods = orderGoodsService.queryInvalidOrder();
        for(OrderGoodsVo o : ordergoods) {
        	
        	ProductVo product = productService.queryObject(o.getProduct_id());
        	product.setGoods_number(product.getGoods_number() + o.getNumber());
        	productService.update(product);
        	
        	OrderVo order = orderService.queryObject(o.getOrder_id());
        	order.setOrder_status(103);
        	orderService.update(order);
        }
    }
    
    //每天凌晨0点执行清空每日销售额
    public void updateTodaySalesTask() {
        logger.info("updateTodaySalesTask======清空每日销售额");
        mlsUserSer.getEntityMapper().updateTodaySales();
    }
    
    //每天更新订单的签收状态
    public void updateConfirmOrderTask() {
        logger.info("updateGetProfitTask======分润金额进入可体现金额");
        //查询出所有已发货但是没有确认收货的订单
        List<OrderVo> orders = orderService.queryWaitList();
        for(OrderVo o : orders) {
        	String sCode = o.getShipping_no();//快递单号
        	String sName = o.getShipping_name();//快递公司拼音
        	Map<String, String> retMap = Kuai100Util.getExpressInfo(sName, sCode);
        	String ischeck = retMap.get("ischeck");
    		if("1".equals(ischeck)) {
    			//如果是已签收，系统自己修改签收状态
    			OrderVo orderVo = orderService.queryObject(o.getId());
                orderVo.setOrder_status(301);
                orderVo.setShipping_status(2);
                String checkTime = retMap.get("checkTime").toString();
                orderVo.setConfirm_time(DateUtils.strToDate(checkTime));
                orderService.update(orderVo);
                
    		}
        }
        
        //完成签收状态，调用分润方法
        updateGetProfitTask();
    }
    
    
    //每天凌晨0点执行更新已收货大于7天的订单，分润金额进入可体现金额
    private void updateGetProfitTask() {
    	//查询所有带分润的订单
    	List<OrderVo> orders = orderService.queryFxList();
    	for(OrderVo o : orders) {
    		Integer orderId = o.getId();
    		UserRecord entity = new UserRecord();
        	entity.setOrderId(orderId);
        	entity.setTypes(2);
        	//根据订单ID查询所有分润人员及金额
        	List<UserRecord> userRecordList = userRecordSer.getEntityMapper().findByEntity(entity);
        	for(UserRecord ur : userRecordList) {
        		//更新体现金额
        		MlsUserEntity2  mlsUserVo = new MlsUserEntity2();
        		mlsUserVo.setMlsUserId(ur.getMlsUserId());
        		mlsUserVo.setGetProfit(ur.getPrice());
        		mlsUserSer.updateGetProfit(mlsUserVo);
        		
        		//增加ur体现记录
        		UserRecord newur = new UserRecord();
        		newur.setMlsUserId(ur.getMlsUserId());
        		newur.setTypes(4);
        		newur.setTypesStr("转入可提现");
        		newur.setPrice(ur.getPrice());
        		newur.setRemarks("转入提现金额="+ur.getPrice());
        		newur.setOrderId(orderId);
            	userRecordSer.save(newur);
        		//修改order体现状态
        		OrderVo newOrder = new OrderVo();
        		newOrder.setId(orderId);
        		newOrder.setFx_status(1);
        		orderService.update(newOrder);
        	}
    	}
    }

	/**
	 * 定时任务
	 * 不成团退款
	 */

	private void groupBuyRefund() {
		Map map = new HashMap();
		map.put("types", 0);
		List<GroupBuyingVo> list = groupBuyingService.queryLoseList(map);
		for (GroupBuyingVo groupBuyingVo : list) {
			Map map1 = new HashMap();
			map1.put("group_buying_id", groupBuyingVo.getId());
			map1.put("order_status", 201);
			List<OrderVo> orderList = orderService.queryGroupBuyRefundList(map);
			//发起退款
			for (OrderVo orderVo : orderList) {
				List<OrderVo> orders = orderService.queryByAllOrderId(orderVo.getAll_order_id());
				BigDecimal allPrice = BigDecimal.ZERO;
				for (OrderVo o : orders) {
					allPrice = allPrice.add(o.getAll_price());
				}
				if (orderVo.getOrder_status() == 300) {
					return;
				} else if (orderVo.getOrder_status() == 301) {
					return;
				}
				// 需要退款
				if (orderVo.getPay_status() == 2) {
					WechatRefundApiResult result = WechatUtil.wxRefund(orderVo.getAll_order_id().toString(),
							allPrice.doubleValue(), orderVo.getAll_price().doubleValue());
					if (result.getResult_code().equals("SUCCESS")) {
						if (orderVo.getOrder_status() == 201) {
							orderVo.setOrder_status(401);
						} else if (orderVo.getOrder_status() == 300) {
							orderVo.setOrder_status(402);
						}
						orderVo.setPay_status(4);
						orderService.update(orderVo);

						//更新优惠券状态和实际
						UserCouponVo uc = new UserCouponVo();
						uc.setId(orderVo.getCoupon_id());
						uc.setCoupon_status(1);
						uc.setUsed_time(null);
						userCouponService.updateCouponStatus(uc);

						//去掉订单成功成立分润退还
						orderService.cancelFx(orderVo.getId(), orderVo.getPay_time(), orderVo.getAll_price().multiply(new BigDecimal("100")).intValue());
						orderVo.setOrder_status(101);
						orderService.update(orderVo);
					}

				}
			}
		}
	}




}
