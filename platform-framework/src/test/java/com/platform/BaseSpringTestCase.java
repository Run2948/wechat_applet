package com.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 基于spring的单元测试基类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-09 10:06:23
 */
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class BaseSpringTestCase extends AbstractJUnit4SpringContextTests {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取Logger
     */
    public Logger getLogger() {
        return logger;
    }
}
