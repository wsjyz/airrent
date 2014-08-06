package com.eighth.airrent.proxy;



import com.eighth.airrent.proxy.utils.Classes;
import com.eighth.airrent.proxy.utils.HTTPClient;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dam on 2014/6/26.
 */
public class RemoteProxy<M> extends ProxyFactory<M> {

    private HTTPClient client = new HTTPClient();

    private Map<String,Object> map = new HashMap<String, Object>();

    public RemoteProxy<M> putOtherParameters(String parameterName,Object parameterValue){
        map.put(parameterName,parameterValue);
        return this;
    }

    public RemoteProxy(Class clazz){
        super(clazz);
    }
    @Override
    public void onException(Exception ex){
        ex.printStackTrace();
    }

    @Override
    public Object after(Type returnType) {
        String serviceUri = Classes.parseClassMethodToUri(super.getTargetClassName(), super.getMethodName());
        client.setServiceUri(serviceUri);
        String responseStr = client.request();
        return Classes.stringToObject(responseStr,returnType);
    }

    @Override
    public void before(String[] argNames, Object[] args) {
        map.clear();
        for(int i = 0;i < argNames.length;i ++){
            String argName = argNames[i];
            map.put(argName,args[i]);
        }
        client.setParams(map);
    }
}
