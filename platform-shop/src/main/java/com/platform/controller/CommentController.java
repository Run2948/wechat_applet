package com.platform.controller;

import com.platform.entity.CommentEntity;
import com.platform.service.CommentService;
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
 * @date 2017-08-28 17:03:40
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("comment:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CommentEntity> commentList = commentService.queryList(query);
        int total = commentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(commentList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("comment:info")
    public R info(@PathVariable("id") Integer id) {
        CommentEntity comment = commentService.queryObject(id);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("comment:save")
    public R save(@RequestBody CommentEntity comment) {
        commentService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("comment:update")
    public R update(@RequestBody CommentEntity comment) {
        commentService.update(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("comment:delete")
    public R delete(@RequestBody Integer[] ids) {
        commentService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CommentEntity> list = commentService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 修改状态
     */
    @RequestMapping("/toggleStatus")
    @RequiresPermissions("comment:toggleStatus")
    public R toggleStatus(@RequestBody CommentEntity comment) {
        commentService.toggleStatus(comment);

        return R.ok();
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {
        int sum = commentService.queryTotal(params);

        return R.ok().put("sum", sum);
    }
}
