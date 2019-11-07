package com.wx.base.util;

import com.wx.base.error.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import com.wx.base.error.Error;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.regex.Pattern;


/**
 * 断言工具类
 * @author wan
 */
public class AssertUtils {

    /**
     * 不为null,为null抛异常
     * @param obj
     */
    public static void notNull(Object obj,Error message){
        if(obj == null){
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object obj,Error error){
        if(obj != null){
            throw new BusinessException(error);
        }
    }

    /**
     * 字符串不为空,为空抛异常
     * @param str
     * @param error
     */
    public static void notBlank(String str,Error error){
        if(StringUtils.isBlank(str)){
            throw new BusinessException(error);
        }
    }

    public static void notBlank(Error error,String... strs){
        for (String str : strs) {
            if (StringUtils.isBlank(str)){
                throw new BusinessException(error);
            }
        }
    }

    /**
     * 判断字符串相等,不相等抛异常
     * @param str1
     * @param str2
     * @param error
     */
    public static void notEq(String str1,String str2,Error error){
        if(!StringUtils.equals(str1,str2)){
            throw new BusinessException(error);
        }
    }


    /**
     * 为true,不为true抛异常
     * @param flag
     * @param error
     */
    public static void notTrue(boolean flag,Error error){
        if(!flag){
            throw new BusinessException(error);
        }
    }

    /**
     * 为false,不为false抛异常
     * @param flag
     * @param error
     */
    public static void notFlase(boolean flag,Error error){
        if(flag){
            throw new BusinessException(error);
        }
    }

    /**
     * 集合不为空,为空抛异常
     * @param coll
     * @param error
     */
    public static void listNotEmpty(Collection<?> coll,Error error){
        if(CollectionUtils.isEmpty(coll)){
            throw new BusinessException(error);
        }
    }

    /**
     * 集合不为空并且size不能大于 num
     * @param coll
     * @param num
     * @param error
     */
    public static void listNotEmptyAndSizeGtNum(Collection<?> coll,int num,Error error){
        if(!CollectionUtils.isEmpty(coll) && coll.size() > num){
            throw new BusinessException(error);
        }
    }

    /**
     * 集合为空,不为空抛异常
     * @param coll
     * @param error
     */
    public static void listIsEmpty(Collection<?> coll,Error error){
        if(!CollectionUtils.isEmpty(coll)){
            throw new BusinessException(error);
        }
    }


    public static void fieldNotNull(Object obj, Error error, String... notNullFields){
        RelectHelper rf = new RelectHelper(obj);
        for (String notNullField : notNullFields) {
            Object methodValue = rf.getMethodValue(notNullField);
            notNull(methodValue,error);
            if(methodValue instanceof String){
                String value = (String) methodValue;
                notBlank(value,error);
            }
        }
    }

    static class RelectHelper{
        private Class cls;
        private Object obj;
        private Hashtable<String, Method> getMethods = null;
        private Hashtable<String, Method> setMethods = null;
        public RelectHelper(Object o) {
            obj = o;
            initMethods();
        }
        public void initMethods() {
            getMethods = new Hashtable<String, Method>();
            setMethods = new Hashtable<String, Method>();
            cls = obj.getClass();
            Method[] methods = cls.getMethods();
            String gs = "get(\\w+)";
            Pattern getM = Pattern.compile(gs);
            String ss = "set(\\w+)";
            Pattern setM = Pattern.compile(ss);
            String rapl = "$1";
            String param;
            for (int i = 0; i < methods.length; ++i) {
                Method m = methods[i];
                String methodName = m.getName();
                if (Pattern.matches(gs, methodName)) {
                    param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
                    getMethods.put(param, m);
                } else if (Pattern.matches(ss, methodName)) {
                    param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();
                    setMethods.put(param, m);
                }
            }
        }
        public boolean setMethodValue(String property, Object object) {
            Method m = setMethods.get(property.toLowerCase());
            if (m != null) {
                try {
                    m.invoke(obj, object);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
            return false;
        }
        public Object getMethodValue(String property) {
            Object value = null;
            Method m = getMethods.get(property.toLowerCase());
            if (m != null) {
                try {
                    value = m.invoke(obj, new Object[] {});
                } catch (Exception ex) {
                }
            }
            return value;
        }
        public Method returnGetMethod(String property){
            return getMethods.get(property.toLowerCase());
        }
        public Method returnSetMethod(String property){
            return setMethods.get(property.toLowerCase());
        }
    }

}
