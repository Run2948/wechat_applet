package com.platform.controller;

import com.platform.entity.BrandEntity;
import com.platform.service.BrandService;
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
 * @date 2017-08-19 17:59:15
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BrandEntity> brandList = brandService.queryList(query);
        int total = brandService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(brandList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("brand:info")
    public R info(@PathVariable("id") Integer id) {
        BrandEntity brand = brandService.queryObject(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("brand:save")
    public R save(@RequestBody BrandEntity brand) {
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("brand:update")
    public R update(@RequestBody BrandEntity brand) {
        brandService.update(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("brand:delete")
    public R delete(@RequestBody Integer[] ids) {
        brandService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<BrandEntity> list = brandService.queryList(params);

        return R.ok().put("list", list);
    }
}
