package com.platform.service;

import com.platform.dao.ApiUserCouponMapper;
import com.platform.entity.UserCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiUserCouponService {
    @Autowired
    private ApiUserCouponMapper userCouponDao;


    public UserCouponVo queryObject(Integer id) {
        return userCouponDao.queryObject(id);
    }

    public UserCouponVo queryByCouponNumber(String couponNumber) {
        return userCouponDao.queryByCouponNumber(couponNumber);
    }

    public List<UserCouponVo> queryList(Map<String, Object> map) {
        return userCouponDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return userCouponDao.queryTotal(map);
    }


    public void save(UserCouponVo goods) {
        userCouponDao.save(goods);
    }


    public void update(UserCouponVo goods) {
        userCouponDao.update(goods);
    }


    public void delete(Integer id) {
        userCouponDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        userCouponDao.deleteBatch(ids);
    }

}
