package com.wx.base.util;



import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 反射工具类
 * @author wan
 */
public class RelectHelper {

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
