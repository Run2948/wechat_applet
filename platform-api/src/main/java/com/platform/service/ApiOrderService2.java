package com.platform.service;

import org.springframework.stereotype.Service;


@Service
public class ApiOrderService2 {
//    @Autowired
//    private ApiOrderMapper orderDao;
//    @Autowired
//    private ApiAddressMapper apiAddressMapper;
//    @Autowired
//    private ApiCartMapper apiCartMapper;
//    @Autowired
//    private ApiCouponMapper apiCouponMapper;
//    @Autowired
//    private ApiOrderMapper apiOrderMapper;
//    @Autowired
//    private ApiOrderGoodsMapper apiOrderGoodsMapper;
//    @Autowired
//    private ApiProductService productService;
//    @Autowired
//    private ApiCustomerService customerService;
//
//
//    public OrderVo queryObject(Integer id) {
//        return orderDao.queryObject(id);
//    }
//
//
//    public List<OrderVo> queryList(Map<String, Object> map) {
//        return orderDao.queryList(map);
//    }
//
//
//    public int queryTotal(Map<String, Object> map) {
//        return orderDao.queryTotal(map);
//    }
//
//
//    public void save(OrderVo order) {
//        orderDao.save(order);
//    }
//
//
//    public int update(OrderVo order) {
//        return orderDao.update(order);
//    }
//
//
//    public void delete(Integer id) {
//        orderDao.delete(id);
//    }
//
//
//    public void deleteBatch(Integer[] ids) {
//        orderDao.deleteBatch(ids);
//    }
//
//
//    @Transactional
//    public Map<String, Object> submit(JSONObject jsonParam, UserVo loginUser) {
//        Map<String, Object> resultObj = new HashMap<String, Object>();
//
//        Integer couponId = jsonParam.getInteger("couponId");
//        String type = jsonParam.getString("type");
//        String postscript = jsonParam.getString("postscript");
////        AddressVo addressVo = jsonParam.getObject("checkedAddress",AddressVo.class);
//        AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getInteger("addressId"));
//
//
//        Integer freightPrice = 0;
//
//        // * 获取要购买的商品
//        List<CartVo> checkedGoodsList = new ArrayList<>();
//        BigDecimal goodsTotalPrice;
//        if (type.equals("cart")) {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("user_id", loginUser.getUserId());
//            param.put("session_id", 1);
//            param.put("checked", 1);
//            checkedGoodsList = apiCartMapper.queryList(param);
//            if (null == checkedGoodsList) {
//                resultObj.put("errno", 400);
//                resultObj.put("errmsg", "请选择商品");
//                return resultObj;
//            }
//            //统计商品总价
//            goodsTotalPrice = new BigDecimal(0.00);
//            for (CartVo cartItem : checkedGoodsList) {
//                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
//            }
//        } else {
//            BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId());
//            ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
//            //计算订单的费用
//            //商品总价
//            goodsTotalPrice = productInfo.getRetail_price().multiply(new BigDecimal(goodsVo.getNumber()));
//
//            CartVo cartVo = new CartVo();
//            BeanUtils.copyProperties(productInfo, cartVo);
//            cartVo.setNumber(goodsVo.getNumber());
//            cartVo.setProduct_id(goodsVo.getProductId());
//            cartVo.setCustomers(goodsVo.getCustomers());
//            cartVo.setCustomers_name(goodsVo.getCustomersName());
//            checkedGoodsList.add(cartVo);
//        }
//
//
//        //获取订单使用的优惠券
//        BigDecimal couponPrice = new BigDecimal(0.00);
//        CouponVo couponVo = null;
//        if (couponId != null && couponId != 0) {
//            couponVo = apiCouponMapper.getUserCoupon(couponId);
//            if (couponVo != null && couponVo.getCoupon_status() == 1) {
//                couponPrice = couponVo.getType_money();
//            }
//        }
//
//        //订单价格计算
//        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价
//
//        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额
//
//        Long currentTime = System.currentTimeMillis() / 1000;
//
//        //
//        OrderVo orderInfo = new OrderVo();
//        orderInfo.setOrder_sn(CommonUtil.generateOrderNumber());
//        orderInfo.setUser_id(loginUser.getUserId());
//        //收货地址和运费
//        //orderInfo.setConsignee(addressVo.getUserName());
//        //orderInfo.setMobile(addressVo.getTelNumber());
//        //orderInfo.setCountry(addressVo.getNationalCode());
//        //orderInfo.setProvince(addressVo.getProvinceName());
//        //orderInfo.setCity(addressVo.getCityName());
//        //orderInfo.setDistrict(addressVo.getCountyName());
//        //orderInfo.setAddress(addressVo.getDetailInfo());
//        //
//        orderInfo.setFreight_price(freightPrice);
//        //留言
//        orderInfo.setPostscript(postscript);
//        //使用的优惠券
//        orderInfo.setCoupon_id(couponId);
//        orderInfo.setCoupon_price(couponPrice);
//        orderInfo.setAdd_time(new Date());
//        orderInfo.setGoods_price(goodsTotalPrice);
//        orderInfo.setOrder_price(orderTotalPrice);
//        orderInfo.setActual_price(actualPrice);
//        // 待付款
//        orderInfo.setOrder_status(0);
//        orderInfo.setShipping_status(0);
//        orderInfo.setPay_status(0);
//        orderInfo.setShipping_id(0);
//        orderInfo.setShipping_fee(new BigDecimal(0));
//        orderInfo.setIntegral(0);
//        orderInfo.setIntegral_money(new BigDecimal(0));
//        if (type.equals("cart")) {
//            orderInfo.setOrder_type("1");
//        } else {
//            orderInfo.setOrder_type("4");
//        }
//
//        //开启事务，插入订单信息和订单商品
//        apiOrderMapper.save(orderInfo);
//        if (null == orderInfo.getId()) {
//            resultObj.put("errno", 1);
//            resultObj.put("errmsg", "订单提交失败");
//            return resultObj;
//        }
//        //统计商品总价
//        for (CartVo goodsItem : checkedGoodsList) {
//        	String customersList[] = goodsItem.getCustomers().split(",");
//        	String customersNameList[] = goodsItem.getCustomers_name().split(",");
//        	int customersNum = customersList.length;
//        	if(customersNum > 0) {
//        		for(int i=0; i<customersNum; i++) {
//        			
//        			CustomerVo customerVo = new CustomerVo();
//            		customerVo.setId(Integer.parseInt(customersList[i]));
//            		CustomerVo cvo = customerService.queryObject(customerVo);
//        			
//            		OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
//                    orderGoodsVo.setOrder_id(orderInfo.getId());
//                    orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
//                    orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
//                    orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
//                    orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
//                    orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
//                    orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
//                    orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
//                    orderGoodsVo.setNumber(1);
//                    orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
//                    orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
//                    orderGoodsVo.setCustomers(customersList[i]);
//                    orderGoodsVo.setCustomers_name(customersNameList[i]);
//                    
//                    //收货地址和运费
//                    orderGoodsVo.setConsignee(cvo.getUname());
//                    orderGoodsVo.setMobile(cvo.getMobile());
//                    orderGoodsVo.setCountry(null);
//                    orderGoodsVo.setProvince(null);
//                    orderGoodsVo.setCity(null);
//                    orderGoodsVo.setDistrict(null);
//                    orderGoodsVo.setAddress(cvo.getAddress());
//                    
//                    apiOrderGoodsMapper.save(orderGoodsVo);
//            	}
//        	}else {
//        		OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
//                orderGoodsVo.setOrder_id(orderInfo.getId());
//                orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
//                orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
//                orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
//                orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
//                orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
//                orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
//                orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
//                orderGoodsVo.setNumber(goodsItem.getNumber());
//                orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
//                orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
//                
//                //收货地址和运费
//                orderGoodsVo.setConsignee(addressVo.getUserName());
//                orderGoodsVo.setMobile(addressVo.getTelNumber());
//                orderGoodsVo.setCountry(addressVo.getNationalCode());
//                orderGoodsVo.setProvince(addressVo.getProvinceName());
//                orderGoodsVo.setCity(addressVo.getCityName());
//                orderGoodsVo.setDistrict(addressVo.getCountyName());
//                orderGoodsVo.setAddress(addressVo.getDetailInfo());
//                
//                apiOrderGoodsMapper.save(orderGoodsVo);
//        	}
//        	
//        }
//
//        //清空已购买的商品
//        apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
//        resultObj.put("errno", 0);
//        resultObj.put("errmsg", "订单提交成功");
//        //
//        Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
//        orderInfoMap.put("orderInfo", orderInfo);
//        //
//        resultObj.put("data", orderInfoMap);
//        // 优惠券标记已用
//        if (couponVo != null && couponVo.getCoupon_status() == 1) {
//            couponVo.setCoupon_status(2);
//            apiCouponMapper.updateUserCoupon(couponVo);
//        }
//
//        return resultObj;
//    }

}
