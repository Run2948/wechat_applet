package com.platform.controller;

import com.platform.entity.UpkeepEntity;
import com.platform.service.UpkeepService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 维护信息管理
 */
@RestController
@RequestMapping("upkeep")
public class UpkeepController {
    @Autowired
    private UpkeepService upkeepService;

    /**
     * 查看列表
     */
    @Ignore
    @RequestMapping("/list")
    @RequiresPermissions("upkeep:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UpkeepEntity> upKeepList = upkeepService.queryList(query);
        int total = upkeepService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(upKeepList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @Ignore
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UpkeepEntity upkeepEntity = upkeepService.queryObject(id);

        return R.ok().put("user", upkeepEntity);
    }

    /**
     * 保存
     */
    @Ignore
    @RequestMapping("/save")
    public R save(@RequestBody UpkeepEntity upkeep) {
        upkeepService.save(upkeep);

        return R.ok();
    }

    /**
     * 修改
     */
    @Ignore
    @RequestMapping("/update")
    public R update(@RequestBody UpkeepEntity upkeep) {
        upkeepService.update(upkeep);

        return R.ok();
    }

    /**
     * 删除
     */
    @Ignore
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        upkeepService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @Ignore
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UpkeepEntity> upkeepEntityList = upkeepService.queryList(params);

        return R.ok().put("list", upkeepEntityList);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {
        int sum = upkeepService.queryTotal(params);

        return R.ok().put("upkeepSum", sum);
    }

    /**
     * 导出会员
     */
    /*@RequestMapping("/export")
    @RequiresPermissions("customer:export")
    public R export(@RequestParam Map<String, Object> params, HttpServletResponse response) {

        List<UpkeepEntity> upkeepEntityListList = upKeepService.queryList(params);

        ExcelExport ee = new ExcelExport("会员列表");

        String[] header = new String[]{"会员名称", "性别", "会员级别", "手机号码"};

        List<Map<String, Object>> list = new ArrayList<>();

        if (userList != null && userList.size() != 0) {
            for (UserEntity userEntity : userList) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("USERNAME", userEntity.getUsername());
                map.put("GENDER", userEntity.getGender() == 1 ? "男" : (userEntity.getGender() == 2 ? "女" : "未知"));
                map.put("LEVEL_NAME", userEntity.getLevelName());
                map.put("MOBILE", userEntity.getMobile());
                list.add(map);
            }
        }

        ee.addSheetByMap("会员", list, header);
        ee.export(response);
        return R.ok();
    }*/
}
