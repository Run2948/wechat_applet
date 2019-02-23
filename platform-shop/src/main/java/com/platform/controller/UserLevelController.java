package com.platform.controller;

import com.platform.entity.UserLevelEntity;
import com.platform.service.UserLevelService;
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
 * @date 2017-08-16 16:52:22
 */
@RestController
@RequestMapping("userlevel")
public class UserLevelController {
    @Autowired
    private UserLevelService userLevelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userlevel:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserLevelEntity> userLevelList = userLevelService.queryList(query);
        int total = userLevelService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userLevelList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("userlevel:info")
    public R info(@PathVariable("id") Integer id) {
        UserLevelEntity userLevel = userLevelService.queryObject(id);

        return R.ok().put("userLevel", userLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("userlevel:save")
    public R save(@RequestBody UserLevelEntity userLevel) {
        userLevelService.save(userLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("userlevel:update")
    public R update(@RequestBody UserLevelEntity userLevel) {
        userLevelService.update(userLevel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("userlevel:delete")
    public R delete(@RequestBody Integer[] ids) {
        userLevelService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping("queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<UserLevelEntity> userLevelList = userLevelService.queryList(params);
        return R.ok().put("list", userLevelList);
    }
}
