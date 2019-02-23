package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CouponGoodsDao;
import com.platform.entity.CouponGoodsEntity;
import com.platform.service.CouponGoodsService;

/**
 * 优惠券关联商品Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-29 21:50:17
 */
@Service("couponGoodsService")
public class CouponGoodsServiceImpl implements CouponGoodsService {
    @Autowired
    private CouponGoodsDao couponGoodsDao;

    @Override
    public CouponGoodsEntity queryObject(Integer id) {
        return couponGoodsDao.queryObject(id);
    }

    @Override
    public List<CouponGoodsEntity> queryList(Map<String, Object> map) {
        return couponGoodsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return couponGoodsDao.queryTotal(map);
    }

    @Override
    public int save(CouponGoodsEntity couponGoods) {
        return couponGoodsDao.save(couponGoods);
    }

    @Override
    public int update(CouponGoodsEntity couponGoods) {
        return couponGoodsDao.update(couponGoods);
    }

    @Override
    public int delete(Integer id) {
        return couponGoodsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return couponGoodsDao.deleteBatch(ids);
    }
}
