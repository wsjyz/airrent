package com.eighth.airrent.proxy.service;

public interface SmsSendService {
	public boolean sendSms(String tel, String authCode);
}
