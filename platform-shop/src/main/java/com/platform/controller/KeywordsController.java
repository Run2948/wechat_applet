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

import com.platform.entity.KeywordsEntity;
import com.platform.service.KeywordsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 热闹关键词表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-25 21:23:41
 */
@RestController
@RequestMapping("keywords")
public class KeywordsController {
    @Autowired
    private KeywordsService keywordsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("keywords:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<KeywordsEntity> keywordsList = keywordsService.queryList(query);
        int total = keywordsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(keywordsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("keywords:info")
    public R info(@PathVariable("id") Integer id) {
        KeywordsEntity keywords = keywordsService.queryObject(id);

        return R.ok().put("keywords", keywords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("keywords:save")
    public R save(@RequestBody KeywordsEntity keywords) {
        keywordsService.save(keywords);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("keywords:update")
    public R update(@RequestBody KeywordsEntity keywords) {
        keywordsService.update(keywords);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("keywords:delete")
    public R delete(@RequestBody Integer[]ids) {
        keywordsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<KeywordsEntity> list = keywordsService.queryList(params);

        return R.ok().put("list", list);
    }
}
