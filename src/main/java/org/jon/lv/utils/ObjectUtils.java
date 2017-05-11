package org.jon.lv.utils;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 工具类
 * Author lv bin
 * @date 2016/12/9 18:22
 * version V1.0.0
 */
public class ObjectUtils {

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isBlank(Object object){
        if (null == object)
            return true;
        else if (object instanceof String)
            return "".equals(object.toString().trim());
        else if (object instanceof Iterable)
            return !((Iterable) object).iterator().hasNext();
        else if (object.getClass().isArray())
            return Array.getLength(object) == 0;
        else if (object instanceof Map)
            return ((Map) object).size() == 0;
        else if (Number.class.isAssignableFrom(object.getClass()))
            return false;
        else if (Date.class.isAssignableFrom(object.getClass()))
            return false;
        else
            return false;
    }

    /**
     * 判断对象是否非空
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj){
        return !isBlank(obj);
    }

    /**
    * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
    * @param args
    * @return
    */
    public static net.sf.json.JSONObject isJSONObject(Object args) {
        net.sf.json.JSONObject result = null ;

        if(args instanceof net.sf.json.JSONObject){
            return (net.sf.json.JSONObject)args;
        }

        if(isBlank(args)){
            return result ;
        }
        try {
            return net.sf.json.JSONObject.fromObject(args);
        } catch (Exception e) {
            return result ;
        }
    }

    /**
     * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
     * @param args
     * @return
     */
    public static net.sf.json.JSONArray isJSONArray(Object args) {

        if(args instanceof net.sf.json.JSONArray){
            return (net.sf.json.JSONArray)args;
        }

        net.sf.json.JSONArray result = null ;
        if(isBlank(args)){
            return result ;
        }
        try {
            return net.sf.json.JSONArray.fromObject(args);
        } catch (Exception e) {
            return result ;
        }
    }
    public static String trimToEmpty(Object str){
        return (isBlank(str) ? "" : str.toString().trim());
    }
}
