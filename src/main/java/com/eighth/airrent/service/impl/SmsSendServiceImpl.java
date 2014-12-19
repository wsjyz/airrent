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

	// 短信API
	public final static String SMS_URL = "http://sh2.ipyy.com/sms.aspx?action=send&userid=8H&account=jkwl015&password=a142563&mobile=";

	@Override
	public boolean sendSms(String tel, String authCode) {

		String message = "&content=飞机租赁：[" + authCode + "]您正在申请注册为本站会员【打飞的】";
		String url = SMS_URL + tel + message;
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
			try {
				in = entity.getContent();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		return false;
	}

	@Override
	public boolean sendSmsByOrder(String tel, String authCode) {

		String message = "&content=飞机租赁：[" + authCode + "]您有新的订单，请注意查看【打飞的】";
		String url = SMS_URL + tel + message;
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
			try {
				in = entity.getContent();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
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
