package com.platform.controller;

import com.platform.entity.UserRecordEntity;
import com.platform.service.UserRecordService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author zhouhaisheng
 * @email
 * @date
 */
@RestController
@RequestMapping("userRecord")
public class UserRecordController {
    @Autowired
    private UserRecordService userRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userRecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserRecordEntity> userRecordList = userRecordService.queryList(query);
        int total = userRecordService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userRecordList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }



}
