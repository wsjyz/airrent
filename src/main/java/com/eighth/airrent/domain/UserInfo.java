package com.eighth.airrent.domain;

/**
 * Created by dam on 14-6-25.
 */
public class UserInfo extends BaseDomain {

	private String userId;
	private String loginName;// 登录名
	private String password;
	private String mobile;// 手机号
	private String userName;// 姓名
	private String identityCard;// 身份证号
	private String sex;// MALE|FAMALE
	private String age;// 年龄
	private String address;// 居住地址
	private String workOrg;// 工作单位
	private String zhifubao;// 支付宝账号
	private String registToken;// 注册时的验证码

	private String loginTip;// 登录提示信息
	private String hint;// LOGIN_INFO_NULL请输入用户名密码 ，NAME_PASSWORD_ERROR 用户名或密码错误
    private String type;
    private String status;

    private String commonAddress;
    private String avatar;
    private String level;
    private String home;

    public void setHint(String hint) {
		this.hint = hint;
	}

	public String getHint() {
		return hint;
	}

	public String getLoginTip() {
		return loginTip;
	}

	public void setLoginTip(String loginTip) {
		this.loginTip = loginTip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkOrg() {
		return workOrg;
	}

	public void setWorkOrg(String workOrg) {
		this.workOrg = workOrg;
	}

	public String getZhifubao() {
		return zhifubao;
	}

	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}

	public String getRegistToken() {
		return registToken;
	}

	public void setRegistToken(String registToken) {
		this.registToken = registToken;
	}

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(String commonAddress) {
        this.commonAddress = commonAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}