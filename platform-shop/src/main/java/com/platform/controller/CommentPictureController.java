package com.platform.controller;

import com.platform.entity.CommentPictureEntity;
import com.platform.service.CommentPictureService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 评价图片Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-29 14:45:55
 */
@RestController
@RequestMapping("commentpicture")
public class CommentPictureController {
    @Autowired
    private CommentPictureService commentPictureService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("commentpicture:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CommentPictureEntity> commentPictureList = commentPictureService.queryList(query);
        int total = commentPictureService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(commentPictureList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("commentpicture:info")
    public R info(@PathVariable("id") Integer id) {
        CommentPictureEntity commentPicture = commentPictureService.queryObject(id);

        return R.ok().put("commentPicture", commentPicture);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("commentpicture:save")
    public R save(@RequestBody CommentPictureEntity commentPicture) {
        commentPictureService.save(commentPicture);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("commentpicture:update")
    public R update(@RequestBody CommentPictureEntity commentPicture) {
        commentPictureService.update(commentPicture);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("commentpicture:delete")
    public R delete(@RequestBody Integer[] ids) {
        commentPictureService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CommentPictureEntity> list = commentPictureService.queryList(params);

        return R.ok().put("list", list);
    }
}
