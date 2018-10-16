package com.platform.controller;

import com.platform.entity.SpecificationEntity;
import com.platform.service.SpecificationService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 规格表
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:10
 */
@RestController
@RequestMapping("specification")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("specification:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SpecificationEntity> specificationList = specificationService.queryList(query);
        int total = specificationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(specificationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("specification:info")
    public R info(@PathVariable("id") Integer id) {
        SpecificationEntity specification = specificationService.queryObject(id);

        return R.ok().put("specification", specification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("specification:save")
    public R save(@RequestBody SpecificationEntity specification) {
        specificationService.save(specification);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("specification:update")
    public R update(@RequestBody SpecificationEntity specification) {
        specificationService.update(specification);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("specification:delete")
    public R delete(@RequestBody Integer[] ids) {
        specificationService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SpecificationEntity> list = specificationService.queryList(params);

        return R.ok().put("list", list);
    }
}
