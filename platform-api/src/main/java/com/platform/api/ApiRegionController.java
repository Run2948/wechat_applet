package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.cache.RegionCacheUtil;
import com.platform.entity.RegionVo;
import com.platform.entity.SysRegionEntity;
import com.platform.util.ApiBaseAction;
import com.platform.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "评论")
@RestController
@RequestMapping("/api/region")
public class ApiRegionController extends ApiBaseAction {

	@ApiOperation(value = "地区列表")
    @IgnoreAuth
    @GetMapping("list")
    public Object list(Integer parentId) {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenByParentId(parentId);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @GetMapping("provinceList")
    public Object provinceList() {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getAllProvice();
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @GetMapping("cityList")
    public Object provinceList(String proviceName) {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenCity(proviceName);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @GetMapping("distinctList")
    public Object distinctList(String proviceName, String cityName) {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenDistrict(proviceName, cityName);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @GetMapping("info")
    public Object info(Integer regionId) {
        SysRegionEntity regionEntity = RegionCacheUtil.getAreaByAreaId(regionId);
        return toResponsSuccess(new RegionVo(regionEntity));
    }

    @IgnoreAuth
    @GetMapping("regionIdsByNames")
    public Object regionIdsByNames(String provinceName, String cityName, String districtName) {
        Map<String, Integer> resultObj = new HashMap<String, Integer>();
        Integer provinceId = 0;
        Integer cityId = 0;
        Integer districtId = 0;
        if (null != provinceName) {
            provinceId = RegionCacheUtil.getProvinceIdByName(provinceName);
        }
        if (null != provinceId && !StringUtils.isNullOrEmpty(cityName)) {
            cityId = RegionCacheUtil.getCityIdByName(provinceId, cityName);
        }
        if (null != provinceId && null != cityId && !StringUtils.isNullOrEmpty(districtName)) {
            districtId = RegionCacheUtil.getDistrictIdByName(provinceId, cityId, districtName);
        }
        resultObj.put("provinceId", provinceId);
        resultObj.put("cityId", cityId);
        resultObj.put("districtId", districtId);
        return toResponsSuccess(resultObj);
    }
}