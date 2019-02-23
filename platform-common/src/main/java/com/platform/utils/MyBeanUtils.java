package com.platform.utils;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * bean 对象工具类
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class MyBeanUtils extends PropertyUtilsBean {

    /**
     * 主要功能:转换
     * 注意事项:无
     *
     * @param dest 结果对象
     * @param orig 来源对象
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    @SuppressWarnings("all")
    private static void convert(Object dest, Object orig)
            throws IllegalAccessException,
            InvocationTargetException {

        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    try {
                        getInstance().setSimpleProperty(dest, name, value);
                    } catch (Exception e) {
                        ; // Should not happen
                    }

                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((Map) orig).get(name);
                    try {
                        getInstance().setSimpleProperty(dest, name, value);
                    } catch (Exception e) {
                        ; // Should not happen
                    }

                }
            }
        } else { /* if (orig is a standard JavaBean) */
            PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(
                    orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                // String type = origDescriptors[i].getPropertyType().toString();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isReadable(orig, name)
                        && PropertyUtils.isWriteable(dest, name)) {
                    try {
                        Object value = PropertyUtils.getSimpleProperty(orig,
                                name);
                        getInstance().setSimpleProperty(dest, name, value);
                    } catch (IllegalArgumentException ie) {
                        ; // Should not happen
                    } catch (Exception e) {
                        ; // Should not happen
                    }

                }
            }
        }

    }

    /**
     * 对象拷贝
     * 数据对象空值不拷贝到目标对象
     *
     * @param databean 数据对象
     * @param tobean   返回对象
     * @throws Exception 异常
     */
    public static void copyBeanNotNull2Bean(Object databean, Object tobean)
            throws Exception {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(
                databean);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            // String type = origDescriptors[i].getPropertyType().toString();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            if (PropertyUtils.isReadable(databean, name)
                    && PropertyUtils.isWriteable(tobean, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean,
                            name);
                    if (value != null) {
                        getInstance().setSimpleProperty(tobean, name, value);
                    }
                } catch (IllegalArgumentException ie) {
                    ; // Should not happen
                } catch (Exception e) {
                    ; // Should not happen
                }

            }
        }
    }

    /**
     * 把orig和dest相同属性的value复制到dest中
     *
     * @param dest 结果
     * @param orig 来源
     * @throws Exception 异常
     */
    public static void copyBean2Bean(Object dest, Object orig)
            throws Exception {
        convert(dest, orig);
    }

    /**
     * 主要功能:bean 转换成 map
     * 注意事项:无
     *
     * @param map  map对象
     * @param bean bean对象
     */
    @SuppressWarnings("all")
    public static void copyBean2Map(Map map, Object bean) {
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            String propname = pd.getName();
            try {
                Object propvalue = PropertyUtils.getSimpleProperty(bean,
                        propname);
                map.put(propname, propvalue);
            } catch (IllegalAccessException e) {
                // e.printStackTrace();
            } catch (InvocationTargetException e) {
                // e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // e.printStackTrace();
            }
        }
    }

    /**
     * 主要功能:bean 转换成 map
     * 注意事项:无
     *
     * @param map  map对象
     * @param bean bean对象
     */
    @SuppressWarnings("all")
    public static void copyBean2Map(Map map, Object bean,
                                    List<String> fieldNameList) {
        for (int i = 0; i < fieldNameList.size(); i++) {
            String oldPropname = fieldNameList.get(i);
            String propname = StringUtils.lineToHump(oldPropname);
            try {
                Object propvalue = getProp(bean, propname);
                map.put(oldPropname, propvalue);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 将Map内的key与Bean中属性相同的内容复制到BEAN中
     *
     * @param bean       Object
     * @param properties Map
     */
    @SuppressWarnings("all")
    public static void copyMap2Bean(Object bean, Map properties) {
        if ((bean == null) || (properties == null)) {
            return;
        }
        // map key 集合
        Iterator names = properties.keySet().iterator();
        // 逐个处理key转换
        while (names.hasNext()) {
            String name = (String) names.next();
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            try {
                // name转换成驼峰
                name = StringUtils.lineToHump(name);
                // 获取属性类型
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();
                // 时间戳
                if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                    if (value == null || value.equals("")) {
                        continue;
                    }
                }
                // 整型INT
                if (className.equalsIgnoreCase("int")) {
                    if (value == null || value.equals("")) {
                        continue;
                    } else {
                        value = ((BigDecimal) value).intValue();
                    }
                }

                // long型
                if (className.equalsIgnoreCase("long")) {
                    if (value == null || value.equals("")) {
                        continue;
                    } else {
                        value = ((BigDecimal) value).longValue();
                    }
                }

                // long型
                if (className.equalsIgnoreCase("java.lang.Long")) {
                    if (value == null || value.equals("")) {
                        continue;
                    } else {
                        value = ((BigDecimal) value).longValue();
                    }
                }

                // 属性赋值
                getInstance().setSimpleProperty(bean, name, value);
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * 自动转Map key值大写
     * 将Map内的key与Bean中属性相同的内容复制到BEAN中
     *
     * @param bean       Object
     * @param properties Map
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    @SuppressWarnings("all")
    public static void copyMap2BeanNobig(Object bean, Map properties)
            throws IllegalAccessException,
            InvocationTargetException {
        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }
        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            // Identify the property name and value(s) to be assigned
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            // 命名应该大小写应该敏感(否则取不到对象的属性)
            // name = name.toLowerCase();
            try {
                if (value == null) { // 不光Date类型，好多类型在null时会出错
                    continue; // 如果为null不用设 (对象如果有特殊初始值也可以保留？)
                }
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) { // 在bean中这个属性不存在
                    continue;
                }
                String className = clazz.getName();
                // 临时对策（如果不处理默认的类型转换时会出错）
                if (className.equalsIgnoreCase("java.util.Date")) {
                    value = new java.util.Date(
                            ((java.sql.Timestamp) value).getTime());// wait to do：貌似有时区问题, 待进一步确认
                }
                // if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                // if (value == null || value.equals("")) {
                // continue;
                // }
                // }
                getInstance().setSimpleProperty(bean, name, value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    /**
     * Map内的key与Bean中属性相同的内容复制到BEAN中
     * 对于存在空值的取默认值
     *
     * @param bean         Object
     * @param properties   Map
     * @param defaultValue String
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    @SuppressWarnings("all")
    public static void copyMap2Bean(Object bean, Map properties,
                                    String defaultValue)
            throws IllegalAccessException,
            InvocationTargetException {
        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }
        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            // Identify the property name and value(s) to be assigned
            if (name == null) {
                continue;
            }
            Object value = properties.get(name);
            try {
                Class clazz = PropertyUtils.getPropertyType(bean, name);
                if (null == clazz) {
                    continue;
                }
                String className = clazz.getName();
                if (className.equalsIgnoreCase("java.sql.Timestamp")) {
                    if (value == null || value.equals("")) {
                        continue;
                    }
                }
                if (className.equalsIgnoreCase("java.lang.String")) {
                    if (value == null) {
                        value = defaultValue;
                    }
                }
                getInstance().setSimpleProperty(bean, name, value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    /**
     * 获取一个类和其父类的所有属性
     *
     * @param clazz 要查找的类
     * @return 属性列表
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Field> findAllFieldsOfSelfAndSuperClass(Class clazz) {
        Field[] fields = null;
        List fieldList = new ArrayList();
        while (true) {
            if (clazz == null) {
                break;
            } else {
                fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    fieldList.add(fields[i]);
                }
                clazz = clazz.getSuperclass();
            }
        }
        return fieldList;
    }

    /**
     * 主要功能:把一个Bean对象转换成Map对象</br>
     * 注意事项:无
     *
     * @param obj     要转换的对象
     * @param ignores 要忽略的属性值
     * @return Map<String, Object>
     */
    public static Map<String, Object> convertBean2Map(Object obj,
                                                      String[] ignores) {
        Map<String, Object> map = new HashMap<String, Object>();
        // Class clazz = obj.getClass();
        List<Field> fieldList = findAllFieldsOfSelfAndSuperClass(
                obj.getClass());
        Field field = null;
        try {
            for (int i = 0; i < fieldList.size(); i++) {
                field = fieldList.get(i);
                // 定义fieldName是否在拷贝忽略的范畴内
                boolean flag = false;
                if (ignores != null && ignores.length != 0) {
                    flag = isExistOfIgnores(field.getName(), ignores);
                }
                if (!flag) {
                    Object value = getProp(obj, field.getName());
                    if (null != value
                            && !StringUtils.EMPTY.equals(value.toString())) {
                        map.put(field.getName(), getProp(obj, field.getName()));
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把一个Bean对象转换成Map对象</br>
     *
     * @param obj 要转换的对象
     * @return Map<String,Object>
     */
    public static Map<String, Object> convertBean2Map(Object obj) {
        return convertBean2Map(obj, null);
    }

    /**
     * 主要功能: 把一个Bean对象转换成Map对象,忽略UID</br>
     * 注意事项:无
     *
     * @param obj 要转换的对象
     * @return Map<String,Object>
     */
    public static Map<String, Object> convertBean2MapForIngoreserialVersionUID(Object obj) {
        return convertBean2Map(obj, new String[]{"serialVersionUID"});
    }

    /**
     * 判断fieldName是否是ignores中排除的
     *
     * @param fieldName 要判断的属性值
     * @param ignores   要忽略的列表
     * @return 匹配为true
     */
    private static boolean isExistOfIgnores(String fieldName,
                                            String[] ignores) {
        boolean flag = false;
        for (String str : ignores) {
            if (str.equals(fieldName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 主要功能: 取得属性信息
     * 注意事项:无
     *
     * @param clazz        要取得的类
     * @param propertyName 要取得的属性
     * @return 属性信息
     */
    public static PropertyDescriptor getPropertyDescriptor(Class<Object> clazz,
                                                           String propertyName) {
        StringBuffer sb = new StringBuffer();// 构建一个可变字符串用来构建方法名称
        Method setMethod = null;
        Method getMethod = null;
        PropertyDescriptor pd = null;
        try {
            Field f = clazz.getDeclaredField(propertyName);// 根据字段名来获取字段
            if (f != null) {
                // 构建方法的后缀
                String methodEnd = propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                sb.append("set" + methodEnd);// 构建set方法
                setMethod = clazz.getDeclaredMethod(sb.toString(),
                        new Class[]{f.getType()});
                sb.delete(0, sb.length());// 清空整个可变字符串
                sb.append("get" + methodEnd);// 构建get方法
                // 构建get 方法
                getMethod = clazz.getDeclaredMethod(sb.toString(),
                        new Class[]{});
                // 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
                pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pd;
    }

    /**
     * 主要功能: 设置某个类的属性值
     * 注意事项:无
     *
     * @param obj          要修改的类实例
     * @param propertyName 要修改的属性
     * @param value        要设置的值
     */
    @SuppressWarnings("unchecked")
    public static void setProp(Object obj, String propertyName, Object value) {
        @SuppressWarnings("rawtypes")
        Class clazz = obj.getClass();// 获取对象的类型

        // 获取 clazz类型中的propertyName的属性描述器
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
        Method setMethod = pd.getWriteMethod();// 从属性描述器中获取 set 方法
        try {
            setMethod.invoke(obj, new Object[]{value});// 调用 set 方法将传入的value值保存属性中去
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主要功能: 取得对象的某属性的值
     * 注意事项:无
     *
     * @param obj          要取值对对象
     * @param propertyName 要取值的属性
     * @return Object 取得的值
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Object getProp(Object obj, String propertyName) {
        Class clazz = obj.getClass();// 获取对象的类型
        // 获取 clazz 类型中的propertyName 的属性描述器
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
        Method getMethod = pd.getReadMethod();// 从属性描述器中获取 get 方法
        Object value = null;
        try {
            // 调用方法获取方法的返回值
            value = getMethod.invoke(obj, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;// 返回值
    }

    /**
     * 构造方法
     */
    public MyBeanUtils() {
        super();
    }

    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {

        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            Map datas = map;
            obj = Class.forName(clazz.getName()).newInstance();
            Method[] ms = clazz.getDeclaredMethods();
            Field[] fs = clazz.getDeclaredFields();
            Set<String> keys = datas.keySet();
            for (int i = 0; i < fs.length; i++) {
                String fieldname = fs[i].getName();
                Object val = null;
                String type = fs[i].getGenericType().toString().split(
                        "[.]")[fs[i].getGenericType().toString().split("[.]").length
                        - 1];
                try {
                    for (Method m : ms) {
                        String temp = m.getName();
                        if (temp.startsWith("set")) {
                            String tmp = temp.substring(3, 4).toLowerCase()
                                    + temp.substring(4);
                            if (igFirstChar(fieldname).equals(
                                    igFirstChar(tmp))) {
                                String keyName = getAnnotationValue(
                                        fs[i].getAnnotations());
                                if ("".equals(keyName)) {

                                    keyName = getAnnotationValue(
                                            clazz.getDeclaredMethod(
                                                    "get" + temp.substring(3),
                                                    null).getAnnotations());
                                    if ("".equals(keyName)) {
                                        keyName = turnToTable(tmp,
                                                m.getAnnotations());
                                    }
                                }

                                // map的key中找不到该字段
                                if (!keys.contains(keyName.toUpperCase())) {
                                    // System.out.println(keyName);
                                    boolean can = true;
                                    int isMore = 0;
                                    for (String str : keys) {
                                        String temp_str = str.replaceAll("_",
                                                "");
                                        String temp_field = fieldname.replaceAll(
                                                "_", "");
                                        if (temp_str.toLowerCase().equals(
                                                temp_field.toLowerCase())) {
                                            if (isMore > 0) {
                                                throw new RuntimeException(
                                                        "java字段" + temp_str + "在数据库中有多个字段相对应！");
                                            }
                                            val = datas.get(str);
                                            can = false;
                                            isMore++;
                                        }
                                    }
                                    if (can) {
                                        String msg = "";
                                        msg = "[警告]:" + clazz.getName() + "."
                                                + fieldname + "在数据库中找不到对应的字段！";
                                        // log.info(msg);
                                    }
                                } else {
                                    val = datas.get(keyName.toUpperCase());
                                }

                                // System.out.println("val " + val);
                                if (val == null) {
                                    continue;
                                }

                                Class<?> paramType = m.getParameterTypes()[0];// set方法的参数

                                if (!String.class.equals(paramType)
                                        && "".equals(val)) {
                                    continue;
                                }

                                // System.out.println(val.getClass().getName() +
                                // ":" + m.getParameterTypes()[0].getName());

                                if ("String".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            m.getParameterTypes()[0].getConstructor(
                                                    String.class).newInstance(
                                                    val.toString()));
                                } else if ("Double".equals(
                                        paramType.getSimpleName())
                                        || "double".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            Double.parseDouble(val.toString()));
                                } else if ("Long".equals(
                                        paramType.getSimpleName())
                                        || "long".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            Long.parseLong(val.toString()));
                                } else if ("Integer".equals(
                                        paramType.getSimpleName())
                                        || "int".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            Integer.parseInt(val.toString()));
                                } else if ("BigInteger".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            new BigInteger(val.toString()));
                                } else if ("Date".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj, DateUtils.convertStringToDate(
                                            val.toString()));
                                } else if ("Short".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            Short.parseShort(val.toString()));
                                } else if ("Byte".equals(
                                        paramType.getSimpleName())
                                        || "byte".equals(
                                        paramType.getSimpleName())) {
                                    m.invoke(obj,
                                            Byte.parseByte(val.toString()));
                                } else if ("char".equals(
                                        paramType.getSimpleName())) {
                                    if (val.toString().length() == 0) {
                                        continue;
                                    }
                                    m.invoke(obj,
                                            (char) (val.toString().charAt(0)));
                                } else if ("Character".equals(
                                        paramType.getSimpleName())) {
                                    if (val.toString().length() == 0) {
                                        continue;
                                    }
                                    m.invoke(obj,
                                            (Character) (val.toString().charAt(0)));
                                } else {
                                    if (val.getClass().equals(paramType)) {
                                        // System.out.println(m.getParameterTypes()[0].getName());
                                        m.invoke(obj, val);
                                    } else {
                                        // System.out.println(val.getClass().getName()
                                        // + ":" +
                                        // m.getParameterTypes()[0].getName());
                                        m.invoke(obj,
                                                m.getParameterTypes()[0].getConstructor(
                                                        String.class).newInstance(
                                                        val.toString()));
                                    }
                                }

                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static String igFirstChar(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        if (fieldName.length() <= 1) {
            return fieldName;
        } else {
            return fieldName.substring(0, 1).toLowerCase()
                    + fieldName.substring(1, fieldName.length());
        }
    }

    private static String getAnnotationValue(Annotation[] annos) {
        String result = "";
        try {
            for (Annotation anno : annos) {
                String simpleName = anno.annotationType().getSimpleName();
                // System.out.println(anno.annotationType().getSimpleName());
                // //Table or IroyUtils
                if ("JNTable".equals(simpleName)) {
                    Method[] ms = anno.annotationType().getDeclaredMethods();
                    for (Method method : ms) {
                        String methodName = method.getName();
                        if ("name".equals(methodName)) {
                            Object val = method.invoke(anno, null);
                            // System.out.println(method.getName() + ":" + val);
                            // //name : value
                            result = val + "";
                        }

                    }
                } else if ("Table".equals(simpleName)) {
                    if ("".equals(result)) {
                        Method[] ms = anno.annotationType().getDeclaredMethods();
                        for (Method method : ms) {
                            String methodName = method.getName();
                            if ("name".equals(methodName)) {
                                Object val = method.invoke(anno, null);
                                // System.out.println(method.getName() + ":" +
                                // val); //name : value
                                result = val + "";
                            }

                        }
                    }
                } else if ("JNField".equals(simpleName)) {
                    Method[] ms = anno.annotationType().getDeclaredMethods();
                    for (Method method : ms) {
                        String methodName = method.getName();
                        if ("name".equals(methodName)) {
                            Object val = method.invoke(anno, null);
                            // System.out.println(method.getName() + ":" + val);
                            // //name : value
                            result = val + "";
                        }

                    }
                } else if ("Column".equals(simpleName)) {
                    if ("".equals(result)) {
                        Method[] ms = anno.annotationType().getDeclaredMethods();
                        for (Method method : ms) {
                            String methodName = method.getName();
                            if ("name".equals(methodName)) {
                                Object val = method.invoke(anno, null);
                                // System.out.println(method.getName() + ":" +
                                // val); //name : value
                                result = val + "";
                            }

                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String turnToTable(String fieldName, Annotation[] annos) {
        // System.out.println(flag);
        String str = getAnnotationValue(annos);
        // System.out.println(fieldName + ":" +str);
        if (!"".equals(str.trim())) {
            return str;
        }
        if (fieldName == null) {
            return "";
        }
        String temp = fieldName.toLowerCase();
        char[] tmp1 = fieldName.toCharArray();
        char[] tmp2 = temp.toCharArray();

        for (int i = 0; i < tmp2.length; i++) {
            if (i == 0) {
                str += tmp2[i] + "";
                if ((tmp1[0] != tmp2[0]) && (tmp1[1] != tmp2[1])) {
                    return fieldName;
                }
                continue;
            }
            if (tmp1[i] != tmp2[i]) {
                if (i < tmp2.length - 1 && (tmp1[i + 1] != tmp2[i + 1])) {
                    return fieldName;
                }
                str += "_" + tmp2[i];
            } else {
                str += tmp2[i] + "";
            }
        }
        return str;
    }

    /**
     * 名称：Struct <br>
     * 描述：〈功能详细描述〉<br>
     *
     * @author yaojie
     * @version 1.0
     * @since 1.0.0
     */
    private static class Struct {
        private Class<?> cls;
        private Object value;
        private String alias;

        public Struct(Class<?> cls, Object value, String alias) {
            this.cls = cls;
            this.value = value;
            this.alias = alias;
        }

        public Class<?> getType() {
            return cls;
        }

        public Object getValue() {
            return value;
        }

        public String getAlias() {
            return alias;
        }

        public String getString(String name) {
            return cls + " " + name + " : " + value;
        }
    }
}
