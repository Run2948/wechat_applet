package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.SysMacroEntity;
import com.platform.service.SysMacroService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通用字典表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 11:48:16
 */
@RestController
@RequestMapping("sys/macro")
public class SysMacroController {
    @Autowired
    private SysMacroService sysMacroService;

    /**
     * 所有字典列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:macro:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysMacroEntity> sysMacroList = sysMacroService.queryList(query);
        int total = sysMacroService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysMacroList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取字典信息
     *
     * @param macroId 主键
     * @return R
     */
    @RequestMapping("/info/{macroId}")
    @RequiresPermissions("sys:macro:info")
    public R info(@PathVariable("macroId") Long macroId) {
        SysMacroEntity sysMacro = sysMacroService.queryObject(macroId);

        return R.ok().put("macro", sysMacro);
    }

    /**
     * 新增字典
     *
     * @param sysMacro 字典
     * @return R
     */
    @SysLog("新增字典")
    @RequestMapping("/save")
    @RequiresPermissions("sys:macro:save")
    public R save(@RequestBody SysMacroEntity sysMacro) {
        sysMacroService.save(sysMacro);

        return R.ok();
    }

    /**
     * 修改字典
     *
     * @param sysMacro 字典
     * @return R
     */
    @SysLog("修改字典")
    @RequestMapping("/update")
    @RequiresPermissions("sys:macro:update")
    public R update(@RequestBody SysMacroEntity sysMacro) {
        sysMacroService.update(sysMacro);

        return R.ok();
    }

    /**
     * 删除字典
     *
     * @param macroIds 主键集
     * @return R
     */
    @SysLog("删除字典")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:macro:delete")
    public R delete(@RequestBody Long[] macroIds) {
        sysMacroService.deleteBatch(macroIds);

        return R.ok();
    }

    /**
     * 查看字典列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysMacroEntity> list = sysMacroService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据value查询数据字典
     *
     * @param value value
     * @return R
     */
    @RequestMapping("/queryMacrosByValue")
    public R queryMacrosByValue(@RequestParam String value) {

        List<SysMacroEntity> list = sysMacroService.queryMacrosByValue(value);

        return R.ok().put("list", list);
    }
}
