package com.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.util.CommonUtil;
import com.platform.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ApiOrderService {
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
	@Autowired
	private ApiUserCouponService userCouponService;
	@Autowired
	private MlsUserSer mlsUserSer;
	@Autowired
	private ApiGoodsSpecificationService goodsSpecificationService;
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
		
		Map<String, Object> resultObj = new HashMap<String, Object>();
		
		// 判断优惠券是否属于个人（防止个人通过接口请求）
		String couponIds = jsonParam.getString("couponId");
		// payType 1：团购操作，2：秒杀，不传为普通购买
		String payType = jsonParam.getString("payType");
		//团购小组的ID，如果创建团购则id为空
		String groupBuyingId = jsonParam.getString("groupBuyingId");
		Map<String, UserCouponVo> couponMerchant = null;
		if(StringUtils.isNotBlank(couponIds)) {
			couponMerchant = new HashMap<String, UserCouponVo>();//供应商与优惠券对应map
			String[] couponList = couponIds.split(",");
			for(String coupon : couponList) {
				UserCouponVo userCouponVo = userCouponService.queryObject(Integer.parseInt(coupon));
				if(userCouponVo ==null || userCouponVo.getId() == null) {
					resultObj.put("errno", 702);
					resultObj.put("errmsg", "优惠券ID错误！");
					return resultObj;
				}
				if(loginUser.getUserId().intValue() != userCouponVo.getUser_id().intValue()) {
					resultObj.put("errno", 600);
					resultObj.put("errmsg", "优惠券信息错误！");
					return resultObj;
				}
				if(couponMerchant.get(userCouponVo.getMerchantId()) != null) {
					resultObj.put("errno", 700);
					resultObj.put("errmsg", "一个商家只能一张优惠券！");
					return resultObj;
				}
				if(userCouponVo.getCoupon_status() != 1) {
					resultObj.put("errno", 701);
					resultObj.put("errmsg", "优惠券不可用！");
					return resultObj;
				}
				//优惠券还未开始使用、优惠券已经过期
				Long use_start_date = userCouponVo.getUse_start_date().getTime();
				Long use_end_date = userCouponVo.getUse_end_date().getTime();
				Long new_date = new Date().getTime();
				if(use_start_date > new_date || new_date > use_end_date) {
					resultObj.put("errno", 703);
					resultObj.put("errmsg", "优惠券不在使用时间范围！");
					return resultObj;
				}
				couponMerchant.put(userCouponVo.getMerchantId().toString(), userCouponVo);
			}
			
		}
		String type = jsonParam.getString("type");//提交方式
		String postscript = jsonParam.getString("postscript");//留言
		Long promoterId = jsonParam.getLong("promoterId");// 获取推荐人id
		System.out.println("=====================获取推荐人id:"+promoterId);
		if(0 == promoterId.intValue()) {
			MlsUserEntity2 mlsuser = mlsUserSer.getEntityMapper().findByUserId(loginUser.getUserId());
			promoterId = mlsuser.getFid();
		}else {
			MlsUserEntity2 mlsuser = mlsUserSer.getEntityMapper().findByUserId(promoterId);
			promoterId = mlsuser.getMlsUserId();
		}
		AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getInteger("addressId"));//收货地址
		

		//需要一个总订单ID,付款的时候计算全部价格
		String all_order_id = UUID.randomUUID().toString().replaceAll("-", "");
		if (type.equals("cart")) {//购物车提交
			//查询所有购物车根据供应商分类
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("user_id", loginUser.getUserId());
			List<CartVo> mrchantList = apiCartMapper.queryMrchantGroup(param);
			//不同供应商产品集合
			List<List<CartVo>> newMrchantList = new ArrayList<List<CartVo>>();
			//根据不同供应商查询购物车商品
			for(CartVo vo : mrchantList) {
				param.put("merchant_id", vo.getMerchant_id());
				param.put("checked", 1);
				newMrchantList.add(apiCartMapper.queryList(param));
			}
			//每个供应商ID形成一个订单
			BigDecimal goodsTotalPrice;//商品总额
			//循环供应商订单
			for(List<CartVo> checkedGoodsList : newMrchantList) {
				if (null == checkedGoodsList) {
					resultObj.put("errno", 400);
					resultObj.put("errmsg", "请选择商品");
					return resultObj;
				}
				BigDecimal brokerage_price = BigDecimal.ZERO;//总返利
				BigDecimal freightPrice = BigDecimal.ZERO;//运费
				Integer couponId = null;//优惠券ID
				BigDecimal couponPrice = BigDecimal.ZERO;//优惠券金额
				goodsTotalPrice = BigDecimal.ZERO;//商品总额
				Long merchant_id = null;//供应商ID
				//循环供应商中商品信息
				for (CartVo cartItem : checkedGoodsList) {
					//计算商品价格
					goodsTotalPrice = goodsTotalPrice
							.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
					ProductVo productInfo = productService.queryObject(cartItem.getProduct_id());
					//判断库存
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
					//计算反润金额(返利 + 返利比例 * 商品金额 * 商品数量)
					brokerage_price = brokerage_price.add(new BigDecimal(goods.getBrokerage_percent())
							.multiply(goods.getRetail_price()).multiply(new BigDecimal(cartItem.getNumber()))); 
					
					//记录一下供应商ID
					if(merchant_id == null) {
						merchant_id = cartItem.getMerchant_id();
					}
				}
				//根据供应商的所有商品金额判断优惠券是否可以用
				if(couponMerchant != null) {
					UserCouponVo userCoupon = couponMerchant.get(merchant_id.toString());
					if(userCoupon != null) {
						BigDecimal min_goods_amount = userCoupon.getMin_goods_amount();
						if(goodsTotalPrice.compareTo(min_goods_amount) < 0) {
							resultObj.put("errno", 800);
							resultObj.put("errmsg", "优惠券不符合规则！");
							return resultObj;
						}
						couponId = userCoupon.getId();//优惠券ID
						couponPrice = userCoupon.getCoupon_price();//优惠券金额
					}
				}
				
				// 订单价格计算
				BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice); // 订单的总价
				BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

				OrderVo orderInfo = new OrderVo();
				// 总订单编号
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
				orderInfo.setCoupon_id(couponId);
				orderInfo.setCoupon_price(couponPrice);
				// 订单金额
				orderInfo.setAdd_time(new Date());
				orderInfo.setGoods_price(goodsTotalPrice);
				orderInfo.setOrder_price(orderTotalPrice);
				orderInfo.setActual_price(actualPrice);
				orderInfo.setAll_price(actualPrice);
				orderInfo.setFreight_price(freightPrice.intValue());
				// 设置为待付款状态
				orderInfo.setOrder_status(0);
				orderInfo.setShipping_status(0);
				orderInfo.setPay_status(0);
				orderInfo.setShipping_id(0);
				orderInfo.setShipping_fee(freightPrice);
				orderInfo.setIntegral(0);
				orderInfo.setIntegral_money(new BigDecimal(0));
				orderInfo.setOrder_type("1");

				// 加入推荐人
				if (promoterId != null) {
					orderInfo.setPromoter_id(promoterId.intValue());
				}
				// 计算佣金
				orderInfo.setBrokerage(brokerage_price);
				// 供应商ID
				orderInfo.setMerchant_id(merchant_id.intValue());
				// 保存订单信息
				apiOrderMapper.save(orderInfo);
				
				// 循环订单商品表
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
					//保存商品规格等信息
					orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());//规格属性id集合
					orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());//规格属性集合

					// 保存订单商品表
					apiOrderGoodsMapper.save(orderGoodsVo);

				}

				// 清空已购买的商品
//				apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
				// 返回对象
				Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
				orderInfoMap.put("orderInfo", orderInfo);
				resultObj.put("errno", 0);
				resultObj.put("errmsg", "订单提交成功");
				resultObj.put("data", orderInfoMap);
				
				// 优惠券标记已用
				UserCouponVo uc = new UserCouponVo();
				uc.setId(couponId);
				uc.setCoupon_status(2);
				uc.setUsed_time(new Date());
				userCouponService.updateCouponStatus(uc);
			}
			
		} else {//直接购买
			if(StringUtils.isBlank(payType) || "2".equals(payType)) {//普通和秒杀
				BigDecimal freightPrice = BigDecimal.ZERO;//运费
				Integer couponId = null;
				BigDecimal couponPrice = BigDecimal.ZERO;//优惠券金额
				
				BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME,
						"goods" + loginUser.getUserId());
				if (null == goodsVo) {
					resultObj.put("errno", 400);
					resultObj.put("errmsg", "请选择商品");
					return resultObj;
				}
				// 商品规格信息
				ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
				// 商品总价
				BigDecimal goodsTotalPrice = productInfo.getRetail_price().multiply(new BigDecimal(goodsVo.getNumber()));
				
				//判断库存
				if (null == productInfo || productInfo.getGoods_number() < goodsVo.getNumber()) {
					resultObj.put("errno", 500);
					resultObj.put("errmsg", productInfo.getGoods_name() + "库存不足");
					return resultObj;
				} else {
					productInfo.setGoods_number(productInfo.getGoods_number() - goodsVo.getNumber());
					productService.update(productInfo);
				}
				// 运费统计
				Integer goodId = goodsVo.getGoodsId();
				GoodsVo goods = goodsService.queryObject(goodId);
				if (goods.getExtra_price() != null) {
					freightPrice = freightPrice
							.add(goods.getExtra_price().multiply(new BigDecimal(goodsVo.getNumber())));
				}
				//计算反润金额(返利 + 返利比例 * 商品金额 * 商品数量)
				BigDecimal brokerage_price = goods.getRetail_price()
						.multiply(new BigDecimal(goods.getBrokerage_percent() * goodsVo.getNumber())).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
				
				

				//根据供应商的所有商品金额判断优惠券是否可以用
				if(couponMerchant != null) {
					UserCouponVo userCoupon = couponMerchant.get(goods.getMerchantId().toString());
					BigDecimal min_goods_amount = userCoupon.getMin_goods_amount();
					if(goodsTotalPrice.compareTo(min_goods_amount) < 0) {
						resultObj.put("errno", 800);
						resultObj.put("errmsg", "优惠券不符合规则！");
						return resultObj;
					}
					couponId = userCoupon.getId();//优惠券ID
					couponPrice = userCoupon.getCoupon_price();//优惠券金额
				}
				
				// 订单价格计算
				BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice); // 订单的总价
				BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

				OrderVo orderInfo = new OrderVo();
				// 总订单编号
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
				orderInfo.setCoupon_id(couponId);
				orderInfo.setCoupon_price(couponPrice);
				// 订单金额
				orderInfo.setAdd_time(new Date());
				orderInfo.setGoods_price(goodsTotalPrice);
				orderInfo.setOrder_price(orderTotalPrice);
				orderInfo.setActual_price(actualPrice);
				orderInfo.setAll_price(actualPrice);
				orderInfo.setFreight_price(freightPrice.intValue());
				// 设置为待付款状态
				orderInfo.setOrder_status(0);
				orderInfo.setShipping_status(0);
				orderInfo.setPay_status(0);
				orderInfo.setShipping_id(0);
				orderInfo.setShipping_fee(freightPrice);
				orderInfo.setIntegral(0);
				orderInfo.setIntegral_money(new BigDecimal(0));
				orderInfo.setOrder_type(payType == null?"2":"3");

				// 加入推荐人
				if (promoterId !=null) {
					orderInfo.setPromoter_id(promoterId.intValue());
				}
				// 计算佣金
				orderInfo.setBrokerage(brokerage_price);
				// 供应商ID
				orderInfo.setMerchant_id(goods.getMerchantId().intValue());
				// 保存订单信息
				apiOrderMapper.save(orderInfo);
				
				
				// 保存订单商品表
				OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
				orderGoodsVo.setOrder_id(orderInfo.getId());
				orderGoodsVo.setGoods_id(productInfo.getGoods_id());
				orderGoodsVo.setGoods_sn(productInfo.getGoods_sn());
				orderGoodsVo.setProduct_id(goodsVo.getProductId());
				orderGoodsVo.setGoods_name(productInfo.getGoods_name());
				orderGoodsVo.setList_pic_url(productInfo.getList_pic_url());
				orderGoodsVo.setMarket_price(productInfo.getMarket_price());
				orderGoodsVo.setRetail_price(productInfo.getRetail_price());
				orderGoodsVo.setNumber(goodsVo.getNumber());
				orderGoodsVo.setCoupon_id(couponId);
				//保存规格信息
				//保存商品规格等信息
				orderGoodsVo.setGoods_specifition_ids(productInfo.getGoods_specification_ids());//规格属性id集合
				//添加规格名和值
				String[] goodsSepcifitionValue = null;
				if (null != productInfo.getGoods_specification_ids() && productInfo.getGoods_specification_ids().length() > 0) {
					Map specificationParam = new HashMap();
					String[] idsArray = getSpecificationIdsArray(productInfo.getGoods_specification_ids());
					specificationParam.put("ids", idsArray);
					specificationParam.put("goods_id", productInfo.getGoods_id());
					List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
					goodsSepcifitionValue = new String[specificationEntities.size()];
					for (int i = 0; i < specificationEntities.size(); i++) {
						goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
					}
				}
				//规格属性集合
				if (null != goodsSepcifitionValue) {
					orderGoodsVo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
				}


				apiOrderGoodsMapper.save(orderGoodsVo);
				
				// 返回对象
				Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
				orderInfoMap.put("orderInfo", orderInfo);
				resultObj.put("errno", 0);
				resultObj.put("errmsg", "订单提交成功");
				resultObj.put("data", orderInfoMap);
				
				// 优惠券标记已用
				UserCouponVo uc = new UserCouponVo();
				uc.setId(couponId);
				uc.setCoupon_status(2);
				uc.setUsed_time(new Date());
				userCouponService.updateCouponStatus(uc);
			}
			//团购购买
			else {
				BigDecimal freightPrice = BigDecimal.ZERO;//运费
				Integer couponId = null;
				BigDecimal couponPrice = BigDecimal.ZERO;//优惠券金额
				
				BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME,
						"goods" + loginUser.getUserId());
				if (null == goodsVo) {
					resultObj.put("errno", 400);
					resultObj.put("errmsg", "请选择商品");
					return resultObj;
				}
				// 商品规格信息
				ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
				GoodsVo goods = goodsService.queryObject(goodsVo.getGoodsId());
				// 商品总价
				BigDecimal goodsTotalPrice = productInfo.getGroup_price().multiply(new BigDecimal(goodsVo.getNumber()));
				
				//判断库存（团购不判断库存）
				/*if (null == productInfo || productInfo.getGoods_number() < goodsVo.getNumber()) {
					resultObj.put("errno", 500);
					resultObj.put("errmsg", productInfo.getGoods_name() + "库存不足");
					return resultObj;
				} else {
					productInfo.setGoods_number(productInfo.getGoods_number() - goodsVo.getNumber());
					productService.update(productInfo);
				}*/
				// 运费统计(团购免运费)
				/*if (goods.getExtra_price() != null) {
					freightPrice = freightPrice
							.add(goods.getExtra_price().multiply(new BigDecimal(goodsVo.getNumber())));
				}*/
				//计算反润金额(返利 + 返利比例 * 商品金额 * 商品数量)(团购不返利)
				/*BigDecimal brokerage_price = goods.getRetail_price()
						.multiply(new BigDecimal(goods.getBrokerage_percent() * goodsVo.getNumber())).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
				*/
				

				//根据供应商的所有商品金额判断优惠券是否可以用（团购无法使用优惠卷）
				if(couponMerchant != null) {
					UserCouponVo userCoupon = couponMerchant.get(goods.getMerchantId().toString());
					BigDecimal min_goods_amount = userCoupon.getMin_goods_amount();
					if(goodsTotalPrice.compareTo(min_goods_amount) < 0) {
						resultObj.put("errno", 800);
						resultObj.put("errmsg", "优惠券不符合规则！");
						return resultObj;
					}
					couponId = userCoupon.getId();//优惠券ID
					couponPrice = userCoupon.getCoupon_price();//优惠券金额
				}
				
				// 订单价格计算
				BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice); // 订单的总价
				BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

				OrderVo orderInfo = new OrderVo();
				// 总订单编号
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
				orderInfo.setCoupon_id(couponId);
				orderInfo.setCoupon_price(couponPrice);
				// 订单金额
				orderInfo.setAdd_time(new Date());
				orderInfo.setGoods_price(goodsTotalPrice);
				orderInfo.setOrder_price(orderTotalPrice);
				orderInfo.setActual_price(actualPrice);
				orderInfo.setAll_price(actualPrice);
				orderInfo.setFreight_price(freightPrice.intValue());
				// 设置为待付款状态
				orderInfo.setOrder_status(0);
				orderInfo.setShipping_status(0);
				orderInfo.setPay_status(0);
				orderInfo.setShipping_id(0);
				orderInfo.setShipping_fee(freightPrice);
				orderInfo.setIntegral(0);
				orderInfo.setIntegral_money(new BigDecimal(0));
				orderInfo.setOrder_type("4");

				// 加入推荐人
				if (promoterId !=null) {
					orderInfo.setPromoter_id(promoterId.intValue());
				}
				// 计算佣金
				//orderInfo.setBrokerage(brokerage_price);
				// 供应商ID
				orderInfo.setMerchant_id(goods.getMerchantId().intValue());
				if(StringUtils.isBlank(groupBuyingId)) {
					groupBuyingId = UUID.randomUUID().toString().replaceAll("-", "");
				}
				orderInfo.setGroup_buying_id(groupBuyingId);
				// 保存订单信息
				apiOrderMapper.save(orderInfo);
				
				
				// 保存订单商品表
				OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
				orderGoodsVo.setOrder_id(orderInfo.getId());
				orderGoodsVo.setGoods_id(productInfo.getGoods_id());
				orderGoodsVo.setGoods_sn(productInfo.getGoods_sn());
				orderGoodsVo.setProduct_id(goodsVo.getProductId());
				orderGoodsVo.setGoods_name(productInfo.getGoods_name());
				orderGoodsVo.setList_pic_url(productInfo.getList_pic_url());
				orderGoodsVo.setMarket_price(productInfo.getMarket_price());
				orderGoodsVo.setRetail_price(productInfo.getRetail_price());
				orderGoodsVo.setNumber(goodsVo.getNumber());
				orderGoodsVo.setCoupon_id(couponId);
				//保存规格信息
				//保存商品规格等信息
				orderGoodsVo.setGoods_specifition_ids(productInfo.getGoods_specification_ids());//规格属性id集合
				//添加规格名和值
				String[] goodsSepcifitionValue = null;
				if (null != productInfo.getGoods_specification_ids() && productInfo.getGoods_specification_ids().length() > 0) {
					Map specificationParam = new HashMap();
					String[] idsArray = getSpecificationIdsArray(productInfo.getGoods_specification_ids());
					specificationParam.put("ids", idsArray);
					specificationParam.put("goods_id", productInfo.getGoods_id());
					List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
					goodsSepcifitionValue = new String[specificationEntities.size()];
					for (int i = 0; i < specificationEntities.size(); i++) {
						goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
					}
				}
				//规格属性集合
				if (null != goodsSepcifitionValue) {
					orderGoodsVo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
				}

				apiOrderGoodsMapper.save(orderGoodsVo);
				
				// 返回对象
				Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
				orderInfoMap.put("orderInfo", orderInfo);
				resultObj.put("errno", 0);
				resultObj.put("errmsg", "订单提交成功");
				resultObj.put("data", orderInfoMap);
				
				// 优惠券标记已用
				UserCouponVo uc = new UserCouponVo();
				uc.setId(couponId);
				uc.setCoupon_status(2);
				uc.setUsed_time(new Date());
				userCouponService.updateCouponStatus(uc);
			}
			
		}

		
		

		return resultObj;
	}
	private String[] getSpecificationIdsArray(String ids) {
		String[] idsArray = null;
		if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
			String[] tempArray = ids.split("_");
			if (null != tempArray && tempArray.length > 0) {
				idsArray = tempArray;
			}
		}
		return idsArray;
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

	public List<OrderVo> queryByAllOrderId(String all_order_id){
		return apiOrderMapper.queryByAllOrderId(all_order_id);
	}

    public List<OrderVo> queryGroupBuyRefundList(Map map) {
		return apiOrderMapper.queryGroupBuyRefundList(map);
    }


	public void cancelFx(int orderId, Date payTime, int orderPrice) {
		UserRecord entity = new UserRecord();
		entity.setOrderId(orderId);
		List<UserRecord> userRecordList = userRecordSer.getEntityMapper().findByEntity(entity);
		for(UserRecord ur : userRecordList) {
			UserRecord newur = new UserRecord();
			newur.setMlsUserId(ur.getMlsUserId());
			newur.setTypes(3);
			newur.setTypesStr("退回分润");
			newur.setPrice(ur.getPrice());
			newur.setRemarks("退回:"+ur.getRemarks());
			newur.setOrderId(orderId);
			userRecordSer.save(newur);

			MlsUserEntity2 mlsUserVo= new MlsUserEntity2();
			mlsUserVo.setMlsUserId(ur.getMlsUserId());
			mlsUserVo.setTodaySales(orderPrice);
			if(DateUtils.format(new Date()).equals(DateUtils.format(payTime))) {//如果支付是今天，则扣除今天的金额
				mlsUserVo.setGetProfit(ur.getPrice());
			}else {
				mlsUserVo.setGetProfit(0);
			}
			mlsUserSer.getEntityMapper().cancelMoney(mlsUserVo);
		}
	}
}
