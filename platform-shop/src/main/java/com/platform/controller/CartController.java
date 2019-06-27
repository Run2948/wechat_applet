package com.platform.controller;

import com.platform.entity.CartEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.CartService;
import com.platform.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:06
 */
@RestController
@RequestMapping("cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("cart:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
		Query query = new Query(params);
		query.put("merchantId",sysUserEntity.getMerchantId());
		List<CartEntity> cartList = cartService.queryList(query);
		int total = cartService.queryTotal(query);
		for(CartEntity user : cartList) {
        	user.setUserName(Base64.decode(user.getUserName()));
        }
		PageUtils pageUtil = new PageUtils(cartList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cart:info")
	public R info(@PathVariable("id") Integer id){
		CartEntity cart = cartService.queryObject(id);
		
		return R.ok().put("cart", cart);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("cart:save")
	public R save(@RequestBody CartEntity cart){
		cartService.save(cart);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("cart:update")
	public R update(@RequestBody CartEntity cart){
		cartService.update(cart);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("cart:delete")
	public R delete(@RequestBody Integer[] ids){
		cartService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
