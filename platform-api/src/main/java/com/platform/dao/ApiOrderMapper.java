package com.platform.dao;

import com.platform.entity.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiOrderMapper extends BaseDao<OrderVo> {
	public void updateStatus(OrderVo vo);
	public List<OrderVo> queryWaitList();
	public List<OrderVo> queryFxList();
	public List<OrderVo> queryByAllOrderId(String allOrderId);

    List<OrderVo> queryGroupBuyRefundList(Map map);
}
