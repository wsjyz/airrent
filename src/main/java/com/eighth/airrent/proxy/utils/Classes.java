package com.eighth.airrent.proxy.utils;

import com.alibaba.fastjson.JSON;
import com.eighth.airrent.proxy.annotation.RemoteMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by dam on 2014/6/27.
 */
public class Classes {


    public static Object stringToObject(String s, Type type){
        if(type instanceof Class){
            Class c = (Class)type;
            if(c.isAssignableFrom(String.class)){
                return s;
            }
        }
        return JSON.parseObject(s,type);
    }

    public static String parseClassMethodToUri(String packageClassName,String methodName){
        StringBuilder str = new StringBuilder(packageClassName+"/"+methodName);
        str.delete(0,str.lastIndexOf(".") + 1);
        return str.toString();
    }

    public static String[] parseMethodVarNames(Method method){
        String[] methodVarNames = new String[]{};
        if(method.isAnnotationPresent(RemoteMethod.class)){
            RemoteMethod remoteMethod = method.getAnnotation(RemoteMethod.class);
            methodVarNames = remoteMethod.methodVarNames();
        }
        return methodVarNames;
    }


}
