package com.platform.cache;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.platform.dao.SysMacroDao;
import com.platform.entity.SysMacroEntity;
import com.platform.utils.SpringContextUtils;
import com.platform.utils.StringUtils;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-16 10:14<br>
 * 描述: CacheUtil <br>
 */
public class CacheUtil implements InitializingBean {

    public  void init() {
        SysMacroDao macroDao = SpringContextUtils.getBean(SysMacroDao.class);
        if (null != macroDao) {
            J2CacheUtils.put("macroList", macroDao.queryList(new HashMap<String, Object>()));
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    /**
     * 根据字典标识获取字典中文
     *
     * @param preName 父级name
     * @param value   字典value
     * @return
     */
    public static String getCodeName(String preName, String value) {
        String name = "";
        Long parentId = 0L;
        List<SysMacroEntity> sysMacroEntityList = (List<SysMacroEntity>) J2CacheUtils.get("macroList");

        if (!StringUtils.isNullOrEmpty(sysMacroEntityList)) {
            for (SysMacroEntity macroEntity : sysMacroEntityList) {
                if (preName.equals(macroEntity.getName())) {
                    parentId = macroEntity.getId();
                }
            }
            for (SysMacroEntity macroEntity : sysMacroEntityList) {
                if (value.equals(macroEntity.getValue()) && parentId.equals(macroEntity.getParentId())) {
                    name = macroEntity.getName();
                }
            }
        }
        return name;
    }

}