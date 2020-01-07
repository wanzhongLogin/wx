package com.wx.base.util;

import com.wx.base.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;


/**
 * 断言工具类
 * @author wan
 */
public class AssertUtils {

    /**
     * 不为null,为null抛异常
     * @param obj
     */
    public static void notNull(Object obj,String message){
        if(obj == null){
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object obj,String error){
        if(obj != null){
            throw new BusinessException(error);
        }
    }

    /**
     * 字符串不为空,为空抛异常
     * @param str
     * @param error
     */
    public static void notBlank(String str,String error){
        if(StringUtils.isBlank(str)){
            throw new BusinessException(error);
        }
    }

    public static void notBlank(String error,String... strs){
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
    public static void notEq(String str1,String str2,String error){
        if(!StringUtils.equals(str1,str2)){
            throw new BusinessException(error);
        }
    }


    /**
     * 为true,不为true抛异常
     * @param flag
     * @param error
     */
    public static void notTrue(boolean flag,String error){
        if(!flag){
            throw new BusinessException(error);
        }
    }

    /**
     * 为false,不为false抛异常
     * @param flag
     * @param error
     */
    public static void notFlase(boolean flag,String error){
        if(flag){
            throw new BusinessException(error);
        }
    }

    /**
     * 集合不为空,为空抛异常
     * @param coll
     * @param error
     */
    public static void listNotEmpty(Collection<?> coll,String error){
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
    public static void listNotEmptyAndSizeGtNum(Collection<?> coll,int num,String error){
        if(!CollectionUtils.isEmpty(coll) && coll.size() > num){
            throw new BusinessException(error);
        }
    }

    /**
     * 集合为空,不为空抛异常
     * @param coll
     * @param error
     */
    public static void listIsEmpty(Collection<?> coll,String error){
        if(!CollectionUtils.isEmpty(coll)){
            throw new BusinessException(error);
        }
    }


    public static void fieldNotNull(Object obj, String error, String... notNullFields){
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


}
