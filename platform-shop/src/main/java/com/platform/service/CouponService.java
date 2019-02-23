package com.platform.service;

import com.platform.entity.CouponEntity;
import com.platform.utils.R;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 12:53:26
 */
public interface CouponService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CouponEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CouponEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param coupon 实体
     * @return 保存条数
     */
    int save(CouponEntity coupon);

    /**
     * 根据主键更新实体
     *
     * @param coupon 实体
     * @return 更新条数
     */
    int update(CouponEntity coupon);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);

    /**
     * 按用户、商品下发优惠券
     *
     * @param params
     * @return
     */
    R publish(Map<String, Object> params);
}
