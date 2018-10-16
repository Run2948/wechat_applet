package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.annotation.SysLog;
import com.platform.entity.SmsConfig;
import com.platform.entity.SysSmsLogEntity;
import com.platform.service.SysConfigService;
import com.platform.service.SysSmsLogService;
import com.platform.utils.Constant;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 发送短信日志Controller
 *
 * @author lipengjun
 * @date 2017-12-16 23:38:05
 */
@RestController
@RequestMapping("/sys/smslog")
public class SysSmsLogController {
    @Autowired
    private SysSmsLogService smsLogService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 短信配置KEY
     */
    private final static String KEY = Constant.SMS_CONFIG_KEY;

    /**
     * 所有日志列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:smslog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysSmsLogEntity> smsLogList = smsLogService.queryList(query);
        int total = smsLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(smsLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取日志信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:smslog:info")
    public R info(@PathVariable("id") String id) {
        SysSmsLogEntity smsLog = smsLogService.queryObject(id);

        return R.ok().put("smsLog", smsLog);
    }

    /**
     * 查看所有列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysSmsLogEntity> list = smsLogService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取短信配置信息
     *
     * @return R
     */
    @RequestMapping("/config")
    public R config() {
        SmsConfig config = sysConfigService.getConfigObject(KEY, SmsConfig.class);

        return R.ok().put("config", config);
    }

    /**
     * 保存短信配置信息
     *
     * @param config 短信配置信息
     * @return R
     */
    @SysLog("保存短信配置信息")
    @RequestMapping("/saveConfig")
    public R saveConfig(@RequestBody SmsConfig config) {
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return R.ok();
    }

    /**
     * 发送短信
     *
     * @param smsLog 短信
     * @return R
     */
    @RequestMapping("/sendSms")
    public R sendSms(@RequestBody SysSmsLogEntity smsLog) {
        SysSmsLogEntity sysSmsLogEntity = smsLogService.sendSms(smsLog);
        return R.ok().put("result", sysSmsLogEntity);
    }
}
