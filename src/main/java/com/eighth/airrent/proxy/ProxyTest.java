package com.eighth.airrent.proxy;

import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.*;
import com.eighth.airrent.proxy.service.UserService;
import com.eighth.airrent.proxy.service.UserServiceImpl;

import java.lang.reflect.*;
import java.util.List;

/**
 * Created by dam on 2014/6/27.
 */
public class ProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxy = new RemoteProxy<UserService>(new UserServiceImpl()).getProxy();
        //UserService proxy = new RemoteProxy<UserService>(UserService.class).getProxy();
        try {
            UserInfo userInfo = proxy.login("sef", "ddd");
            System.out.println(userInfo.getLoginTip());
        } catch (RemoteInvokeException e) {
            e.printStackTrace();
        }
//
//        InfoServiceRemote infoServiceRemote = new RemoteProxy<InfoServiceRemote>(new InfoServiceRemoteImpl()).getProxy();
//
//        List<Information> list = infoServiceRemote.getInformations("{ \"pageNo\": 0, \"pageSize\": 10 }").getRows();
//        for(Information info:list){
//            System.out.println(info.getContent());
//        }

//        String jsonStr = "{\"pageNo\":1,\"pageSize\":10,\"autoCount\":true,\"autoPaging\":true,\"rows\":[{\"informationId\":\"26123b1cf5c211e3bede52540077b0c2\",\"title\":\"title1\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-17 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42bc6f5c211e3bede52540077b0c2\",\"title\":\"title2\",\"content\":\"content内容content内容content内容content内容content内容\",\"postTime\":\"2014-06-18 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42c84f5c211e3bede52540077b0c2\",\"title\":\"title3\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-19 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42cc0f5c211e3bede52540077b0c2\",\"title\":\"title4\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-20 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42cfcf5c211e3bede52540077b0c2\",\"title\":\"title5\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-21 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42d42f5c211e3bede52540077b0c2\",\"title\":\"title6\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-22 00:00:00.0\",\"status\":\"POST\"},{\"informationId\":\"b9d42d88f5c211e3bede52540077b0c2\",\"title\":\"title7\",\"content\":\"内容content内容content内容contentcontent内容content内容\",\"postTime\":\"2014-06-23 00:00:00.0\",\"status\":\"POST\"}],\"total\":7,\"totalPages\":1,\"hasNext\":false,\"nextPage\":1,\"hasPre\":false,\"prePage\":1,\"first\":1}";
//
//        Method m = null;
//        try {
//            m = InfoServiceRemote.class.getMethod("getInformations",String.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        Type type = m.getGenericReturnType();
//        ProxyTest pt = new ProxyTest();
//        OpenPage<Information> l1 = JSON.parseObject(jsonStr,type);
//         //= JSON.parseObject(jsonStr,new TypeReference<OpenPage<Information>>(){});
//        for(Information info:l1.getRows()){
//            System.out.println(info.getContent());
//        }

    }
    private Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else {
            return (Class) genericClass;
        }
    }
    public static Class<?> getClass(Type type)
    {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
