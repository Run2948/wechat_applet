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

import com.platform.entity.FootprintEntity;
import com.platform.service.FootprintService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:08
 */
@RestController
@RequestMapping("footprint")
public class FootprintController {
    @Autowired
    private FootprintService footprintService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("footprint:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FootprintEntity> footprintList = footprintService.queryList(query);
        int total = footprintService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(footprintList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("footprint:info")
    public R info(@PathVariable("id") Integer id) {
        FootprintEntity footprint = footprintService.queryObject(id);

        return R.ok().put("footprint", footprint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("footprint:save")
    public R save(@RequestBody FootprintEntity footprint) {
        footprintService.save(footprint);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("footprint:update")
    public R update(@RequestBody FootprintEntity footprint) {
        footprintService.update(footprint);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("footprint:delete")
    public R delete(@RequestBody Integer[] ids) {
        footprintService.deleteBatch(ids);

        return R.ok();
    }

}
