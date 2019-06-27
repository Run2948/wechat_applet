package com.platform.service;

import com.platform.dao.ApiGoodsMapper;
import com.platform.entity.GoodsVo;
import com.platform.entity.MlsUserEntity2;
import com.platform.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsService {
    @Autowired
    private ApiGoodsMapper goodsDao;


    public GoodsVo queryObject(Integer id) {
        return goodsDao.queryObject(id);
    }


    public List<GoodsVo> queryList(Map<String, Object> map) {
        return goodsDao.queryList(map);
    }
    
    public List<GoodsVo> queryKillList() {
        return goodsDao.queryKillList();
    }


    public int queryTotal(Map<String, Object> map) {
        return goodsDao.queryTotal(map);
    }


    public void save(GoodsVo goods) {
        goodsDao.save(goods);
    }


    public void update(GoodsVo goods) {
        goodsDao.update(goods);
    }


    public void delete(Integer id) {
        goodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsDao.deleteBatch(ids);
    }

    public List<GoodsVo> queryHotGoodsList(Map<String, Object> map) {
        return goodsDao.queryHotGoodsList(map);
    }

    public List<GoodsVo> queryCatalogProductList(Map<String, Object> map) {
        return goodsDao.queryCatalogProductList(map);
    }
    
    public Integer queryMaxId() {
    	return goodsDao.queryMaxId();
    }
    
    public List<GoodsVo> queryTop4(MlsUserEntity2 loginUser, Integer brand_id){
    	List<GoodsVo> goodsList = goodsDao.queryTop4(brand_id);
    	for(GoodsVo vo : goodsList) {
	    	vo.setDiscount(vo.getRetail_price().multiply(new BigDecimal("10")).divide(vo.getMarket_price(), 1, BigDecimal.ROUND_HALF_UP).toString());
	    	vo.setUser_brokerage_price(vo.getRetail_price().multiply(new BigDecimal(vo.getBrokerage_percent())).multiply(new BigDecimal(loginUser.getFx()).divide(new BigDecimal("10000"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
    	return goodsList;
    }

    public List<GoodsVo> queryFxList(Map<String, Object> map) {
        return goodsDao.queryFxList(map);
    }

    public int queryFxTotal(Query query) {
        return goodsDao.queryFxTotal(query);
    }


    public List<GoodsVo> queryGroupList(Query query) {
        return goodsDao.queryGroupList(query);
    }

    public int queryGroupTotal(Query query) {
        return goodsDao.queryGroupTotal(query);
    }

    public int queryKillTotal(Query query) {
        return goodsDao.queryKillTotal(query);
    }

    public List<GoodsVo> queryKillPage(Query query) {
        return goodsDao.queryKillPage(query);
    }
}
