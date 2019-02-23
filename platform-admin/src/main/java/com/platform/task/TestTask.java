package com.platform.task;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.entity.SysUserEntity;
import com.platform.service.SysOssService;
import com.platform.service.SysUserService;

/**
 * 测试定时任务(演示Demo，可删除)
 * <p>
 * testTask为spring bean的名称
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("testTask")
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysOssService sysOssService;

    public void test(String params) {
        logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
    }

    public void test2() {
        logger.info("我是不带参数的test2方法，正在被执行");
    }
}
