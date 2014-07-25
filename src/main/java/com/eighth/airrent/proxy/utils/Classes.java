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
        Object obj = null;
        try{
            obj = JSON.parseObject(s,type);
        }catch (Exception e){
            System.out.println("服务端返回值序列化异常！");
        }
        return obj;
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
