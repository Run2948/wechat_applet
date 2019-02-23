package com.platform.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 名称：BeanUtils <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * 默认忽略字段<br>
     */
    private static String[] IGNORE_PROPERTIES = {"createUser", "createTime"};

    /**
     * 重写copyProperties，NULL值,可以拷贝
     * 增加默认忽略字段 modify by zhangguoqing at 2017年4月8日16:13:55
     *
     * @param source 拷贝元实体
     * @param target 拷贝目标实体
     * @throws BeansException 抛出异常
     */
    public static void copyProperties(Object source, Object target, String[] ignoreList)
            throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        List<String> ignorePropertyList = new ArrayList<String>();
        ignorePropertyList.addAll(Arrays.asList(IGNORE_PROPERTIES));
        // 传入的忽略数组非空扩展忽略数组
        if (ignoreList != null && ignoreList.length != 0) {
            ignorePropertyList.addAll(Arrays.asList(ignoreList));
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(),
                        targetPd.getName());

                if (sourcePd != null && sourcePd.getReadMethod() != null
                        && !ignorePropertyList.contains(targetPd.getName())) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断value是否为空,过滤Integer类型字段 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        // if (value != null &&
                        // !"java.lang.Integer".equals(readMethod.getReturnType().getName())) {
                        // if (value != null && !"".equals(value)) {
                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);
                        // }
                    } catch (Throwable ex) {
                        throw new FatalBeanException(
                                "Could not copy properties '" + targetPd.getName()
                                        + "' from source to target",
                                ex);
                    }
                }
            }
        }
    }

    /**
     * 重写copyProperties，忽略NULL值
     *
     * @param source
     * @param target
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断value是否为空,过滤Integer类型字段 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
//                        if (value != null && !"java.lang.Integer".equals(readMethod.getReturnType().getName())) {
                        if (value != null && !"".equals(value)) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties '" + targetPd.getName() + "' from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * bean 转为map
     *
     * @param obj         bean对象
     * @param isAllowNull 空字段是否转换 false 不转换
     * @return map值
     */
    public static Map<String, Object> bean2Map(Object obj, boolean isAllowNull) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (isAllowNull || value != null && !value.toString().isEmpty()) {
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;
    }

    /**
     * map转bean
     *
     * @param targetMap 被转化的map
     * @param obj       对象
     */
    public static void map2Bean(Map<String, Object> targetMap, Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String key : targetMap.keySet()) {
            Object value = targetMap.get(key);
            map.put(StringUtils.lineToHump(key), value);
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    try {
                        Object value = map.get(key);
                        // 得到property对应的setter方法
                        Method setter = property.getWriteMethod();
                        setter.invoke(obj, value);
                    } catch (Exception e) {
                        throw new RRException("实体转换错误:" + key);
                    }
                }

            }
        } catch (Exception e) {
            e.getStackTrace();
            throw new RRException("数据转换异常！");
        }
    }
}
