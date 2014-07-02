package com.eighth.airrent.domain;

/**
 * Created by dam on 2014/7/2.
 */
public class VerifyCode {

    private String tokenId;//当前验证码ID
    private String token;//当前验证码值
    private String optTime;//验证码生成时间yyyy-MM-dd HH:mm:ss

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
}
