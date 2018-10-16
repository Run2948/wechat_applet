package com.platform.controller;

import com.platform.entity.GoodsIssueEntity;
import com.platform.service.GoodsIssueService;
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
 * @date 2017-08-23 14:12:34
 */
@RestController
@RequestMapping("goodsissue")
public class GoodsIssueController {
    @Autowired
    private GoodsIssueService goodsIssueService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsissue:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GoodsIssueEntity> goodsIssueList = goodsIssueService.queryList(query);
        int total = goodsIssueService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsIssueList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsissue:info")
    public R info(@PathVariable("id") Integer id) {
        GoodsIssueEntity goodsIssue = goodsIssueService.queryObject(id);

        return R.ok().put("goodsIssue", goodsIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsissue:save")
    public R save(@RequestBody GoodsIssueEntity goodsIssue) {
        goodsIssueService.save(goodsIssue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsissue:update")
    public R update(@RequestBody GoodsIssueEntity goodsIssue) {
        goodsIssueService.update(goodsIssue);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsissue:delete")
    public R delete(@RequestBody Integer[] ids) {
        goodsIssueService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GoodsIssueEntity> list = goodsIssueService.queryList(params);

        return R.ok().put("list", list);
    }
}
