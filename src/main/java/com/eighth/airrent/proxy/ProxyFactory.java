package com.eighth.airrent.proxy;



import com.eighth.airrent.proxy.utils.Classes;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by dam on 2014/6/26.
 * 代理工厂，具体的代理类需要实现相应before、onException、after方法
 */
public abstract class ProxyFactory<T> implements InvocationHandler {

    private T target;
    public ProxyFactory(T target){
        super();
        this.target = target;
    }

    public T getProxy(){
        Class<T> cls = (Class<T>)target.getClass();
        if(cls.getInterfaces() != null){
            return (T)Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),this);
        }
        return target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        try{
            before(args);
            obj = method.invoke(target,args);
            String[] argNames = Classes.getMethodParamNames(method);
            after(argNames, args);
        }catch (Exception e){
            onException(e);
        }
        return "123";
    }


    public abstract void onException(Exception ex);

    public abstract void after(String[] argNames,Object[] args);

    public abstract void before(Object[] args);
}
