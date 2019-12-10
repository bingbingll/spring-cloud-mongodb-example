package com.example.demo.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 数据拷贝工具类
 */
@Component
public class BeanConversionUtil {

    private static Logger logger = LoggerFactory.getLogger(BeanConversionUtil.class);

    /**
     * @param source：源数据
     * @param clazz：目标数据
     * @author 李衡
     * @data 20180723
     * @describe 数据拷贝
     */
    public synchronized static <S, T> T copyProperties(S source, Class<T> clazz) {
        Assert.notNull(clazz, "clazz must not be null");
        if (source == null) {
            logger.debug("copyProperties-source-null->" + clazz);
            return null;
        }
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
            return null;
        }
    }

    /**
     * @param source：源数据集合
     * @param target：目录数据集合
     * @param clazz：目标数据类型
     * @author 李衡
     * @data 20180723
     * @describe 集合内数据拷贝
     */
    public synchronized static <S, T> void copyCollection(Collection<S> source, Collection<T> target, Class<T> clazz) {
        Assert.notNull(source, "source must not be null");
        Assert.notNull(target, "target must not be null");
        try {
            for (Iterator<S> iterator = source.iterator(); iterator.hasNext(); ) {
                S s = (S) iterator.next();
                T t = clazz.newInstance();
                BeanUtils.copyProperties(s, t);
                target.add(t);
            }
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
        }
    }

    /**
     * 数据拷贝 忽略数据源(source)中值为null的属性
     *
     * @param source
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if (source == null) {
            logger.debug("copyProperties-source-null");
        }
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
        }
    }

    /**
     * 获取属性为null的属性名
     *
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    /**
     * 将源对象中非空属性的值替换到属性名相同的目标对象。
     * TODO 此处会报一个异常，不过这并不妨碍数据正常转换。
     * TODO:建议使用本类的update方法
     *
     * @param source
     * @param target
     */
    @Deprecated
    public synchronized static void copyNoNullProperties(Object source, Object target) {
        Assert.notNull(target, "clazz must not be null");
        try {
            HashMap<String, Object> hashMap = getNoNullProperties(source);
            BeanWrapper srcBean = new BeanWrapperImpl(target);
            srcBean.setPropertyValues(hashMap);
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
        }
    }

    /**
     * @param source 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    private static HashMap<String, Object> getNoNullProperties(Object source) {
        BeanWrapper srcBean = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        HashMap<String, Object> hashMap = new HashMap<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) {
                hashMap.put(p.getName(), value);
            }
        }
        return hashMap;
    }

    /**
     * 将数据转为字符串中间,号隔开数据转换为字符串
     *
     * @param strings
     * @return 0,1,2,3,4,5,6,7,8,9
     */
    public synchronized static String convertString(String[] strings) {
        if (strings == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (String s : strings) {
            stringBuilder.append(s).append(",");
        }
        return stringBuilder.deleteCharAt(stringBuilder.toString().length()-1).toString();
    }


    /**
     * 将 pos集合拷贝到dtos集合中
     *
     * @param source
     * @param clazz
     * @param <S>
     * @param <T>
     * @return
     */
    public synchronized static <S, T> List<T> copyList(List<S> source, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (ObjectUtils.allNotNull(source) && source.size() <= 0) {
            return new ArrayList<T>(0);
        }
        for (S s : source) {
            T t = BeanConversionUtil.copyProperties(s, clazz);
            list.add(t);
        }
        return list;
    }


    /**
     * @param source 传过来的参数dto
     * @param target 用id从数据库中查出来的数po
     *               第三个参数是忽略的值
     */
    public static void update(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }


}