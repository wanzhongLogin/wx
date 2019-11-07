package com.core;



import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类
 * @author wan
 */
public class ReflectUtil {

    public static Map<String, Class> clazzMap = new ConcurrentHashMap<String, Class>();
    public static Map<String, Object> objMap = new ConcurrentHashMap<String, Object>();
    public static Map<String, Method> metMap = new ConcurrentHashMap<String, Method>();

    /**
     * 获取对象
     * @param clazz 类的全路径
     */
    public static Object getObj(String clazz){
        try {
            Object obj = objMap.get(clazz);
            if(obj == null){
                Class cl = clazzMap.get(clazz);
                if(cl == null){
                    cl = Class.forName(clazz);
                    clazzMap.put(clazz,cl);
                }
                obj = cl.newInstance();
                objMap.put(clazz,obj);
            }
            return obj;
        } catch(Exception e) {
            throw new RuntimeException("获取对象失败");
        }
    }

    /**
     * 直接反射方法
     * @param clazz 类的全路径
     * @param method 方法名
     * @param os 方法传入参数
     * @param cs 方法参数类型
     */
    public static Object getMethodInvokeValue(String clazz, String method, Object os, Class[] cs) throws NoSuchMethodException {
        try {
            int size = 0;
            if(cs != null) {
                size = cs.length;
            }
            Method m = metMap.get(clazz + "_" + method + "_" + size);
            Object obj = objMap.get(clazz);
            if(m == null || obj == null) {
                Class cl = clazzMap.get(clazz);
                if(cl == null) {
                    cl = Class.forName(clazz);
                    clazzMap.put(clazz, cl);
                }
                if(obj == null) {
                    obj = cl.newInstance();
                    objMap.put(clazz, obj);
                }
                if(m == null) {
                    m = cl.getMethod(method, cs);
                    metMap.put(clazz + "_" + method + "_" + size, m);
                }
            }
            return m.invoke(obj, os);
        } catch(Exception e) {
            e.printStackTrace();
            throw new NoSuchMethodException();
        }
    }

}
