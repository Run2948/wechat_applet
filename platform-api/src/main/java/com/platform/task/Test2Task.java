package com.platform.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;
import com.platform.entity.ProductVo;
import com.platform.service.ApiOrderGoodsService;
import com.platform.service.ApiOrderService;
import com.platform.service.ApiProductService;

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
}
