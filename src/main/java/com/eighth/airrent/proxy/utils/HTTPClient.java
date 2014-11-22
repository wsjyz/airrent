package com.eighth.airrent.proxy.utils;

import com.alibaba.fastjson.JSON;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dam on 2014/6/26.
 */
public class HTTPClient {

    //private final static String SERVER_HOST_URL = "http://203.195.131.34:8081/ar/";//测试环境
    private final static String SERVER_HOST_URL = "http://182.92.111.128:8080/ar/";//生产环境
    //参数
    private Map<String,Object> params = new HashMap<String, Object>();
    //参数名和方法名
    private String serviceUri;

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public UrlEncodedFormEntity packageParams(){
        ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        if(params.size() > 0){
            for(String key:params.keySet()){
                Object paramsValueObj = params.get(key);
                String paramsValues = "";
                if(paramsValueObj instanceof String){
                    paramsValues = (String)paramsValueObj;
                }else{
                    paramsValues = JSON.toJSONString(params.get(key));
                }
                System.out.println(key+"|"+paramsValues);
                BasicNameValuePair nameValue = new BasicNameValuePair(key,paramsValues);
                list.add(nameValue);
            }
        }
        UrlEncodedFormEntity urlencodedformentity = null;
        try {
            urlencodedformentity = new UrlEncodedFormEntity(list, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlencodedformentity;
    }
    public String request(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String postUri = SERVER_HOST_URL+getServiceUri();
        System.out.println("request uri:"+postUri);
        HttpPost httpPost = new HttpPost(postUri);

        if(params.size() > 0){
            httpPost.setEntity(packageParams());
        }


        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new RemoteInvokeException(EntityUtils.toString(response.getEntity()));
                }
            }

        };
        String responseBody = "";
        try {
            responseBody = httpClient.execute(httpPost,responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseBody;
    }

}
