package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.RelatedGoodsEntity;
import com.platform.service.RelatedGoodsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;


/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
@RestController
@RequestMapping("relatedgoods")
public class RelatedGoodsController {
	@Autowired
	private RelatedGoodsService relatedGoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("relatedgoods:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RelatedGoodsEntity> relatedGoodsList = relatedGoodsService.queryList(query);
		int total = relatedGoodsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(relatedGoodsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("relatedgoods:info")
	public R info(@PathVariable("id") Integer id){
		RelatedGoodsEntity relatedGoods = relatedGoodsService.queryObject(id);
		
		return R.ok().put("relatedGoods", relatedGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("relatedgoods:save")
	public R save(@RequestBody RelatedGoodsEntity relatedGoods){
		relatedGoodsService.save(relatedGoods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("relatedgoods:update")
	public R update(@RequestBody RelatedGoodsEntity relatedGoods){
		relatedGoodsService.update(relatedGoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("relatedgoods:delete")
	public R delete(@RequestBody Integer[] ids){
		relatedGoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
