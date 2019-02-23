package com.platform.controller;

import com.platform.entity.AttributeEntity;
import com.platform.service.AttributeService;
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
 * @date 2017-08-17 16:48:17
 */
@RestController
@RequestMapping("attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attribute:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AttributeEntity> attributeList = attributeService.queryList(query);
        int total = attributeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(attributeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attribute:info")
    public R info(@PathVariable("id") Integer id) {
        AttributeEntity attribute = attributeService.queryObject(id);

        return R.ok().put("attribute", attribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attribute:save")
    public R save(@RequestBody AttributeEntity attribute) {
        attributeService.save(attribute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attribute:update")
    public R update(@RequestBody AttributeEntity attribute) {
        attributeService.update(attribute);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attribute:delete")
    public R delete(@RequestBody Integer[] ids) {
        attributeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<AttributeEntity> list = attributeService.queryList(params);

        return R.ok().put("list", list);
    }
}
