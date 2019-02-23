package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.cache.RegionCacheUtil;
import com.platform.entity.SysRegionEntity;
import com.platform.service.SysRegionService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.TreeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 地址管理Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-11-04 11:19:31
 */
@RestController
@RequestMapping("sys/region")
public class SysRegionController {
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 查看列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:region:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysRegionEntity> regionList = sysRegionService.queryList(query);
        int total = sysRegionService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(regionList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:region:info")
    public R info(@PathVariable("id") Integer id) {
        SysRegionEntity region = sysRegionService.queryObject(id);

        return R.ok().put("region", region);
    }

    /**
     * 新增地址
     *
     * @param region 地址
     * @return R
     */
    @SysLog("新增地址")
    @RequestMapping("/save")
    @RequiresPermissions("sys:region:save")
    public R save(@RequestBody SysRegionEntity region) {
        sysRegionService.save(region);

        return R.ok();
    }

    /**
     * 修改地址
     *
     * @param region 地址
     * @return R
     */
    @SysLog("修改地址")
    @RequestMapping("/update")
    @RequiresPermissions("sys:region:update")
    public R update(@RequestBody SysRegionEntity region) {
        sysRegionService.update(region);

        return R.ok();
    }

    /**
     * 删除地址
     *
     * @param ids 主键集
     * @return R
     */
    @SysLog("删除地址")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:region:delete")
    public R delete(@RequestBody Integer[] ids) {
        sysRegionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查询所有国家
     *
     * @return R
     */
    @RequestMapping("/getAllCountry")
    public R getAllCountry() {
        List<SysRegionEntity> list = RegionCacheUtil.getAllCountry();
        return R.ok().put("list", list);
    }

    /**
     * 查询所有省份
     *
     * @return R
     */
    @RequestMapping("/getAllProvice")
    public R getAllProvice(@RequestParam(required = false) Integer areaId) {
        List<SysRegionEntity> list = RegionCacheUtil.getAllProviceByParentId(areaId);
        return R.ok().put("list", list);
    }
    /**
     * 查询所有省份
     *
     * @return R
     */
    @RequestMapping("/getAllProviceByName")
    public R getAllProviceByName(@RequestParam(required = false) String  areaName) {
        List<SysRegionEntity> list = RegionCacheUtil.getAllProviceByParentByName(areaName);
        return R.ok().put("list", list);
    }
    /**
     * 查询所有城市
     *
     * @return R
     */
    @RequestMapping("/getAllCity")
    public R getAllCity(@RequestParam(required = false) Integer areaId) {
        List<SysRegionEntity> list = RegionCacheUtil.getChildrenCity(areaId);
        return R.ok().put("list", list);
    }
    /**
     * 查询所有城市
     *
     * @return R
     */
    @RequestMapping("/getAllCityByName")
    public R getAllCityByName(@RequestParam(required = false) String  areaName) {
        List<SysRegionEntity> list = RegionCacheUtil.getChildrenCityByName(areaName);
        return R.ok().put("list", list);
    }

    /**
     * 查询所有区县
     *
     * @return R
     */
    @RequestMapping("/getChildrenDistrictByName")
    public R getChildrenDistrictByName(@RequestParam(required = false) String areaName) {
        List<SysRegionEntity> list = RegionCacheUtil.getChildrenDistrictByName(areaName);
        return R.ok().put("list", list);
    }
    /**
     * 查询所有区县
     *
     * @return R
     */
    @RequestMapping("/getChildrenDistrict")
    public R getChildrenDistrict(@RequestParam(required = false) Integer areaId) {
        List<SysRegionEntity> list = RegionCacheUtil.getChildrenDistrict(areaId);
        return R.ok().put("list", list);
    }

    /**
     * 查看信息(全部加载页面渲染太慢！)
     *
     * @return R
     */
    @RequestMapping("/getAreaTree")
    public R getAreaTree() {
        List<SysRegionEntity> list = RegionCacheUtil.sysRegionEntityList;
        for (SysRegionEntity sysRegionEntity : list) {
            sysRegionEntity.setValue(sysRegionEntity.getId() + "");
            sysRegionEntity.setLabel(sysRegionEntity.getName());
        }
        List<SysRegionEntity> node = TreeUtils.factorTree(list);

        return R.ok().put("node", node);
    }

    /**
     * 根据类型获取区域
     *
     * @param type 类型
     * @return R
     */
    @RequestMapping("/getAreaByType")
    public R getAreaByType(@RequestParam(required = false) Integer type) {

        List<SysRegionEntity> list = new ArrayList<>();
        if (type.equals(0)) {

        } else if (type.equals(1)) {//省份
            list = RegionCacheUtil.getAllCountry();
        } else if (type.equals(2)) {
            list = RegionCacheUtil.getAllProvice();
        } else if (type.equals(3)) {
            list = RegionCacheUtil.getAllCity();
        }
        return R.ok().put("list", list);
    }
}
