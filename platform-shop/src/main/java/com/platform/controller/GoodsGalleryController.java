package com.platform.controller;

import com.platform.entity.GoodsGalleryEntity;
import com.platform.service.GoodsGalleryService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 14:41:43
 */
@RestController
@RequestMapping("goodsgallery")
public class GoodsGalleryController {
    @Autowired
    private GoodsGalleryService goodsGalleryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsgallery:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GoodsGalleryEntity> goodsGalleryList = goodsGalleryService.queryList(query);
        int total = goodsGalleryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsGalleryList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsgallery:info")
    public R info(@PathVariable("id") Integer id) {
        GoodsGalleryEntity goodsGallery = goodsGalleryService.queryObject(id);

        return R.ok().put("goodsGallery", goodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsgallery:save")
    public R save(@RequestBody GoodsGalleryEntity goodsGallery) {
        goodsGalleryService.save(goodsGallery);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsgallery:update")
    public R update(@RequestBody GoodsGalleryEntity goodsGallery) {
        goodsGalleryService.update(goodsGallery);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsgallery:delete")
    public R delete(@RequestBody Integer[] ids) {
        goodsGalleryService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GoodsGalleryEntity> list = goodsGalleryService.queryList(params);

        return R.ok().put("list", list);
    }
}
