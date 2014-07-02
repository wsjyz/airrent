package com.eighth.airrent.domain;

/**
 * Created by dam on 14-6-25.
 */
public class UserInfo {

    private String userId;
    private String loginName;//登录名
    private String password;
    private String mobile;//手机号
    private String userName;//姓名
    private String identityCard;//身份证号
    private String sex;//MALE|FAMALE
    private String age;//年龄
    private String address;//居住地址
    private String workOrg;//工作单位
    private String zhifubao;//支付宝账号
    private String registToken;//注册时的验证码

    private String loginTip;
    private String loginStatus;//LOGIN_INFO_NULL请输入用户名密码

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginTip() {
        return loginTip;
    }

    public void setLoginTip(String loginTip) {
        this.loginTip = loginTip;
    }
}