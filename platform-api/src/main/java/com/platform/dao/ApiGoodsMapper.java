package com.platform.dao;

import com.platform.entity.GoodsVo;
import com.platform.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:16:45
 */
public interface ApiGoodsMapper extends BaseDao<GoodsVo> {

    List<GoodsVo> queryHotGoodsList(Map<String, Object> params);

    List<GoodsVo> queryCatalogProductList(Map<String, Object> params);
    List<GoodsVo> queryKillList();
    Integer queryMaxId();
    List<GoodsVo> queryTop4(Integer brand_id);
    List<GoodsVo> queryFxList(Map<String, Object> map);

    int queryFxTotal(Query query);

    List<GoodsVo> queryGroupList(Query query);

    int queryGroupTotal(Query query);

    int queryKillTotal(Query query);

    List<GoodsVo> queryKillPage(Query query);
}
