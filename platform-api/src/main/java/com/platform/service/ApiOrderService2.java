package com.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.DateUtil;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.util.CommonUtil;
import com.platform.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

//@Service
public class ApiOrderService2 {
	@Autowired
	private ApiOrderMapper orderDao;
	@Autowired
	private ApiAddressMapper apiAddressMapper;
	@Autowired
	private ApiCartMapper apiCartMapper;
	@Autowired
	private ApiCouponMapper apiCouponMapper;
	@Autowired
	private ApiOrderMapper apiOrderMapper;
	@Autowired
	private ApiOrderGoodsMapper apiOrderGoodsMapper;
	@Autowired
	private ApiProductService productService;
	@Autowired
	private ApiGoodsService goodsService;
	@Autowired
	UserRecordSer userRecordSer;

	public OrderVo queryObject(Integer id) {
		return orderDao.queryObject(id);
	}

	public List<OrderVo> queryList(Map<String, Object> map) {
		return orderDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return orderDao.queryTotal(map);
	}

	public void save(OrderVo order) {
		orderDao.save(order);
	}

	public int update(OrderVo order) {
		return orderDao.update(order);
	}

	public void delete(Integer id) {
		orderDao.delete(id);
	}

	public void deleteBatch(Integer[] ids) {
		orderDao.deleteBatch(ids);
	}

	@Transactional
	public Map<String, Object> submit(JSONObject jsonParam, UserVo loginUser) {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("user_id", loginUser.getUserId());
//		param.put("merchant_id", "2");
//		List<CartVo> checkedGoodsList = apiCartMapper.queryList(param);
//		System.out.println(checkedGoodsList);
//		param.put("merchant_id", "1");
//		checkedGoodsList = apiCartMapper.queryList(param);
//		System.out.println(checkedGoodsList);
//		param.put("merchant_id", "1");
//		checkedGoodsList = apiCartMapper.queryList(param);
//		System.out.println(checkedGoodsList);
		
		Map<String, Object> resultObj = new HashMap<String, Object>();
		// 原来couponId换成couponId和good组合的list集合
		String couponGoodList = jsonParam.getString("couponGoodList");
		Integer couponId = jsonParam.getInteger("couponId");
		String type = jsonParam.getString("type");
		String postscript = jsonParam.getString("postscript");
		// 获取推荐人id
		String promoterId = jsonParam.getString("promoterId");
		AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getInteger("addressId"));
		// * 获取要购买的商品
		List<CartVo> checkedGoodsList = new ArrayList<>();
		BigDecimal goodsTotalPrice;
		if (type.equals("cart")) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("user_id", loginUser.getUserId());
			List<CartVo> mrchantList = apiCartMapper.queryMrchantGroup(param);
			for(CartVo vo : mrchantList) {
				param.put("merchant_id", vo.getMerchant_id());
				checkedGoodsList = apiCartMapper.queryList(param);
				
				if (null == checkedGoodsList) {
					resultObj.put("errno", 400);
					resultObj.put("errmsg", "请选择商品");
					return resultObj;
				}
				// 统计商品总价
				double brokerage_percent = 0.0;
				BigDecimal freightPrice = BigDecimal.ZERO;
				BigDecimal couponPrice = new BigDecimal(0.00);
				goodsTotalPrice = new BigDecimal(0.00);
				for (CartVo cartItem : checkedGoodsList) {
					goodsTotalPrice = goodsTotalPrice
							.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
					ProductVo productInfo = productService.queryObject(cartItem.getProduct_id());
					if (null == productInfo || productInfo.getGoods_number() < cartItem.getNumber()) {
						resultObj.put("errno", 500);
						resultObj.put("errmsg", cartItem.getGoods_name() + "库存不足");
						return resultObj;
					} else {
						productInfo.setGoods_number(productInfo.getGoods_number() - cartItem.getNumber());
						productService.update(productInfo);
					}

					// 运费统计
					Integer goodId = cartItem.getGoods_id();
					GoodsVo goods = goodsService.queryObject(goodId);
					if (goods.getExtra_price() != null) {
						freightPrice = freightPrice
								.add(goods.getExtra_price().multiply(new BigDecimal(cartItem.getNumber())));
					}
				}

				// 获取订单使用的优惠券
				couponGoodList = "100=1152004&100=1131017&100=1152008&100=1023003";
				String[] couponGoods = couponGoodList.split("&");
				for (String couponGood : couponGoods) {
					Integer ncouponId = Integer.parseInt(couponGood.split("=")[0]);
					Integer goodsId = Integer.parseInt(couponGood.split("=")[1]);

					if (ncouponId != null && ncouponId != 0 && goodsId != null && goodsId != 0) {
						GoodsVo goods = goodsService.queryObject(goodsId);
						CouponVo couponVo = apiCouponMapper.getUserCoupon(ncouponId);
						// 代金券状态1可用，并且最小商品小雨订单金额
						if (couponVo != null && couponVo.getCoupon_status() == 1
								&& couponVo.getMin_goods_amount().compareTo(goods.getRetail_price()) <= 0) {
							couponPrice = couponVo.getType_money();
						}
					}
				}
				
				// 订单价格计算
				BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice); // 订单的总价
				BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

				OrderVo orderInfo = new OrderVo();

				String all_order_id = UUID.randomUUID().toString().replaceAll("-", "");
				orderInfo.setAll_order_id(all_order_id);
				orderInfo.setOrder_sn(CommonUtil.generateOrderNumber());
				orderInfo.setUser_id(loginUser.getUserId());
				// 收货地址和运费
				orderInfo.setConsignee(addressVo.getUserName());
				orderInfo.setMobile(addressVo.getTelNumber());
				orderInfo.setCountry(addressVo.getNationalCode());
				orderInfo.setProvince(addressVo.getProvinceName());
				orderInfo.setCity(addressVo.getCityName());
				orderInfo.setDistrict(addressVo.getCountyName());
				orderInfo.setAddress(addressVo.getDetailInfo());
				// 留言
				orderInfo.setPostscript(postscript);
				// 使用的优惠券
				// orderInfo.setCoupon_id(couponId);
				orderInfo.setCoupon_price(couponPrice);
				orderInfo.setAdd_time(new Date());
				orderInfo.setGoods_price(goodsTotalPrice);
				orderInfo.setOrder_price(orderTotalPrice);
				orderInfo.setActual_price(actualPrice);
				orderInfo.setAll_price(actualPrice);
				orderInfo.setFreight_price(freightPrice.intValue());
				// 待付款
				orderInfo.setOrder_status(0);
				orderInfo.setShipping_status(0);
				orderInfo.setPay_status(0);
				orderInfo.setShipping_id(0);
				orderInfo.setShipping_fee(freightPrice);
				orderInfo.setIntegral(0);
				orderInfo.setIntegral_money(new BigDecimal(0));
				if (type.equals("cart")) {
					orderInfo.setOrder_type("1");
				} else {
					orderInfo.setOrder_type("4");
				}

				// 开启事务，插入订单信息和订单商品
				// 添加订单分销信息

				if (StringUtils.isNotEmpty(promoterId)) {
					orderInfo.setPromoter_id(Integer.valueOf(promoterId));
				}
				// 计算佣金
				orderInfo.setBrokerage(orderInfo.getActual_price().multiply(new BigDecimal(brokerage_percent)));
				apiOrderMapper.save(orderInfo);
				for (CartVo goodsItem : checkedGoodsList) {

					OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
					orderGoodsVo.setOrder_id(orderInfo.getId());
					orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
					orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
					orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
					orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
					orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
					orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
					orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
					orderGoodsVo.setNumber(goodsItem.getNumber());
					orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
					orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
					if (type.equals("cart")) {
						couponGoodList = "123=456&567=098";
						for (String couponGood : couponGoods) {
							Integer ncouponId = Integer.parseInt(couponGood.split("=")[0]);
							Integer goodsId = Integer.parseInt(couponGood.split("=")[1]);
							if(goodsId.intValue() == goodsItem.getGoods_id().intValue()) {
								orderGoodsVo.setCoupon_id(ncouponId);
							}
						}
					} else {
						orderGoodsVo.setCoupon_id(couponId);
					}
					apiOrderGoodsMapper.save(orderGoodsVo);

				}

				// 清空已购买的商品
				apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
				resultObj.put("errno", 0);
				resultObj.put("errmsg", "订单提交成功");
				//
				Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
				orderInfoMap.put("orderInfo", orderInfo);
				//
				resultObj.put("data", orderInfoMap);
				// 优惠券标记已用
				for (String couponGood : couponGoods) {
					Integer ncouponId = Integer.parseInt(couponGood.split("=")[0]);
					CouponVo couponVo = apiCouponMapper.queryObject(ncouponId);
					if (couponVo != null && couponVo.getCoupon_status() == 1) {
						couponVo.setCoupon_status(2);
						apiCouponMapper.updateUserCoupon(couponVo);
					}
				}
			}
			
		} else {//直接购买

			double brokerage_percent = 0.0;
			BigDecimal freightPrice = BigDecimal.ZERO;
			BigDecimal couponPrice = new BigDecimal(0.00);
			
			BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME,
					"goods" + loginUser.getUserId());
			ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
			// 计算订单的费用
			// 商品总价
			goodsTotalPrice = productInfo.getRetail_price().multiply(new BigDecimal(goodsVo.getNumber()));

			CartVo cartVo = new CartVo();
			BeanUtils.copyProperties(productInfo, cartVo);
			cartVo.setNumber(goodsVo.getNumber());
			cartVo.setProduct_id(goodsVo.getProductId());
			checkedGoodsList.add(cartVo);

			if (null == productInfo || productInfo.getGoods_number() < goodsVo.getNumber()) {
				resultObj.put("errno", 500);
				resultObj.put("errmsg", goodsVo.getName() + "库存不足");
				return resultObj;
			} else {
				productInfo.setGoods_number(productInfo.getGoods_number() - goodsVo.getNumber());
				productService.update(productInfo);
			}

			// 运费统计
			Integer goodId = goodsVo.getGoodsId();
			GoodsVo goods = goodsService.queryObject(goodId);
			brokerage_percent = goods.getBrokerage_percent();
			if (goods.getExtra_price() != null) {
				freightPrice = freightPrice.add(goods.getExtra_price().multiply(new BigDecimal(goodsVo.getNumber())));
			}

			//获取订单使用的优惠券
	        CouponVo couponVo = null;
	        if (couponId != null && couponId != 0) {
	            couponVo = apiCouponMapper.getUserCoupon(couponId);
	            if (couponVo != null && couponVo.getCoupon_status() == 1) {
	                couponPrice = couponVo.getType_money();
	            }
	        }
			
			// 订单价格计算
			BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice); // 订单的总价
			BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

			OrderVo orderInfo = new OrderVo();

			String all_order_id = UUID.randomUUID().toString().replaceAll("-", "");
			orderInfo.setAll_order_id(all_order_id);
			orderInfo.setOrder_sn(CommonUtil.generateOrderNumber());
			orderInfo.setUser_id(loginUser.getUserId());
			// 收货地址和运费
			orderInfo.setConsignee(addressVo.getUserName());
			orderInfo.setMobile(addressVo.getTelNumber());
			orderInfo.setCountry(addressVo.getNationalCode());
			orderInfo.setProvince(addressVo.getProvinceName());
			orderInfo.setCity(addressVo.getCityName());
			orderInfo.setDistrict(addressVo.getCountyName());
			orderInfo.setAddress(addressVo.getDetailInfo());
			//
			orderInfo.setFreight_price(freightPrice.intValue());
			// 留言
			orderInfo.setPostscript(postscript);
			// 使用的优惠券
			// orderInfo.setCoupon_id(couponId);
			orderInfo.setCoupon_price(couponPrice);
			orderInfo.setAdd_time(new Date());
			orderInfo.setGoods_price(goodsTotalPrice);
			orderInfo.setOrder_price(orderTotalPrice);
			orderInfo.setActual_price(actualPrice);
			orderInfo.setAll_price(actualPrice);
			orderInfo.setFreight_price(actualPrice.subtract(goodsTotalPrice).intValue());
			// 待付款
			orderInfo.setOrder_status(0);
			orderInfo.setShipping_status(0);
			orderInfo.setPay_status(0);
			orderInfo.setShipping_id(0);
			orderInfo.setShipping_fee(freightPrice);
			orderInfo.setIntegral(0);
			orderInfo.setIntegral_money(new BigDecimal(0));
			if (type.equals("cart")) {
				orderInfo.setOrder_type("1");
			} else {
				orderInfo.setOrder_type("4");
			}

			// 开启事务，插入订单信息和订单商品
			// 添加订单分销信息

			System.out.println("promoterId=============" + promoterId);

			if (StringUtils.isNotEmpty(promoterId)) {
				orderInfo.setPromoter_id(Integer.valueOf(promoterId));
			}
			// 计算佣金
			orderInfo.setBrokerage(orderInfo.getActual_price().multiply(new BigDecimal(brokerage_percent)));
			apiOrderMapper.save(orderInfo);
			for (CartVo goodsItem : checkedGoodsList) {

				OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
				orderGoodsVo.setOrder_id(orderInfo.getId());
				orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
				orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
				orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
				orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
				orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
				orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
				orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
				orderGoodsVo.setNumber(goodsItem.getNumber());
				orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
				orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
				orderGoodsVo.setCoupon_id(couponId);
				apiOrderGoodsMapper.save(orderGoodsVo);

			}

			// 清空已购买的商品
			apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
			resultObj.put("errno", 0);
			resultObj.put("errmsg", "订单提交成功");
			//
			Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
			orderInfoMap.put("orderInfo", orderInfo);
			//
			resultObj.put("data", orderInfoMap);
			// 优惠券标记已用
	        if (couponVo != null && couponVo.getCoupon_status() == 1) {
	            couponVo.setCoupon_status(2);
	            apiCouponMapper.updateUserCoupon(couponVo);
	        }
		}

		
		

		return resultObj;
	}

	public void updateStatus(OrderVo vo) {
		apiOrderMapper.updateStatus(vo);
	}

	public List<OrderVo> queryWaitList() {
		return apiOrderMapper.queryWaitList();
	}

	public List<OrderVo> queryFxList() {
		return apiOrderMapper.queryFxList();
	}
}
