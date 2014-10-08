package com.eighth.airrent.service.impl;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.eighth.airrent.proxy.service.SmsSendService;

import javax.annotation.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
@Service("SmsSendService")
public class SmsSendServiceImpl implements SmsSendService {

    //短信API
    public final static String SMS_URL="http://sms.uuvio.com:8001/sms.aspx?action=send&userid=8H&account=A142563&password=2014&mobile=";
  
    @Override
    public boolean sendSms(String tel, String authCode) {
    	
        String message="&content=["+authCode+"]恭喜您已成功注册为本站会员【环球之翼航空】";
        String url=SMS_URL+tel+message;
        HttpPost sendUrl = new HttpPost(url);
        HttpClient http = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = http.execute(sendUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            InputStream in = null;
            try{
                in = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                if(in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return false;
    }
}
