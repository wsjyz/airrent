package com.eighth.airrent.proxy;

import com.eighth.airrent.proxy.utils.Classes;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dam on 2014/6/27.
 * 暂不支持接口，因为无法通过Interface获取方法的参数名（asm/javassist都试过，无法获取，需要研究一下）
 */
public abstract class CglibProxyFactory<M> implements MethodInterceptor {

    private M target;

    Class<M> cls;

    private String targetClassName;

    private String methodName;

    public String getTargetClassName() {
        return targetClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public CglibProxyFactory(Class clazz){
        super();
        cls = clazz;
    }
    public CglibProxyFactory(M target){
        super();
        this.target = target;
    }
    public M getProxy(){
        Enhancer enhancer = new Enhancer();
        //如果是类代理
        if(target != null){
            cls = (Class<M>)target.getClass();
        }
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return (M)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try{
            //获取方法名
            this.methodName = method.getName();
            //获取参数名
            String[] argNames = Classes.getMethodParamNames(method);
            before(argNames, args);
            //对象代理
            if(target != null){
                this.targetClassName = o.getClass().getSuperclass().getInterfaces()[0].getName();
                result = after(method.getGenericReturnType());


            }else{//类代理要返回类的方法的返回值


                result = method.invoke(target,args);
            }

        }catch (Exception e){
            onException(e);
        }
        return result;
    }
    public abstract void onException(Exception ex);

    public abstract Object after(Type type);

    public abstract void before(String[] argNames,Object[] args);
}
