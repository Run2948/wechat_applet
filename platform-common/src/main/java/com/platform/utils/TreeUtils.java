package com.platform.utils;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称：TreeUtils <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class TreeUtils {
    /**
     * 包装成树形结构 (全部属性)
     * 必须要有id parentId children
     *
     * @param tree
     * @return
     * @throws Exception
     */
    public static List factorTree(List tree) {
        try {
            if (tree != null) {
                List t_list = new ArrayList();
                Map map = new HashMap();
                for (Object o : tree) {
                    Class clazz = o.getClass();
                    Field id = clazz.getDeclaredField("id");
                    if (!id.isAccessible()) {
                        id.setAccessible(true);
                    }
                    Integer lId = (Integer) id.get(o);
                    map.put(lId, o);
                }
                for (Object o : map.keySet()) {
                    Integer cId = (Integer) o;
                    Object obj = map.get(cId);
                    Class clazz = obj.getClass();
                    Field pId = clazz.getDeclaredField("parentId");
                    if (!pId.isAccessible()) {
                        pId.setAccessible(true);
                    }
                    Integer id = (Integer) pId.get(obj);
                    if (null == map.get(id)) {
                        t_list.add(obj);
                    } else {
                        Object object = map.get(id);
                        Class clazz1 = object.getClass();
                        Field children = null;
                        try {
                            //没有children属性就到父类查找
                            children = clazz1.getDeclaredField("children");
                        } catch (Exception e) {
                            children = clazz1.getSuperclass().getDeclaredField("children");
                        }
                        if (!children.isAccessible()) {
                            children.setAccessible(true);
                        }
                        List list = (List) children.get(object);
                        if (CollectionUtils.isEmpty(list)) {
                            list = new ArrayList();
                        }
                        list.add(obj);
                        children.set(object, list);
                    }
                }
                return t_list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
