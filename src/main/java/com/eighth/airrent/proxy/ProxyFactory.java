package com.eighth.airrent.proxy;


import com.eighth.airrent.proxy.utils.Classes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by dam on 2014/6/27.
 * 暂不支持接口，因为无法通过Interface获取方法的参数名（asm/javassist都试过，无法获取，需要研究一下）
 */
public abstract class ProxyFactory<M> implements InvocationHandler {

    private Class<M> targetClass;

    private String targetClassName;

    private String methodName;

    public String getTargetClassName() {
        return targetClassName;
    }

    public String getMethodName() {
        return methodName;
    }


    public ProxyFactory(Class clazz){
        super();
        this.targetClass = clazz;
    }
    public M getProxy(){
        return (M) Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            //获取方法名
            this.methodName = method.getName();
            //获取参数名
            String[] argNames = Classes.parseMethodVarNames(method);
            before(argNames, args);
            this.targetClassName = targetClass.getName();
            result = after(method.getGenericReturnType());

        } catch (Exception e) {
            onException(e);
        }
        return result;
    }
    public abstract void onException(Exception ex);

    public abstract Object after(Type type);

    public abstract void before(String[] argNames,Object[] args);
}
