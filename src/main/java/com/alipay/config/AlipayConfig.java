package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088002197126523";
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public static String key = "yc42c5hwbtx5sny1xj367wpj75ocd42y";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	//public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKdWfME6WfmJah5uO09WqfA0o+4uD7iJpbg5m9UOpkyH33DTut8XjQClIa3OsiBj0LiaUfw1kEEJw7CddouKRYrSy7b4UETLXDPQsEbL8jvwvvhwdjStapkqYY+Dpx9MLiTmyHnVM7GcxAgCvK8X6sYmz16r3/vHKuYiF/Vysog5AgMBAAECgYBsoQh7p5WhGEN7XbPC3U+UT5fblqs98J1T6pHuzYPHSq0SYWB+UpuGP6yNWBDqVeG49PUj9OGgVuZiUNYT378kQtPKepLr4evn22a8ZLhPSd+KZWb4lVkBT6Egm64JcxasMitqjU/d3MSNr7IAkE6xfPg3Z3wNZ28qN87R/mnewQJBANHQR8P5wjqNL6f2J/Jl+bYDEdUvJSrYhT5u94IktKi9bsyzgpgXq+vuKARtP/pwbyPHlSnc7tv1AFU51+hNKZMCQQDMLI1ArVAWZNxBq9MbeBzcY8gnQX0YXiUByay5gKcX7zkte+fIKq9ybv0LHfI68WxpUHMmPUS9BLC1tPI7kfaDAkA0lxtV8QWRPZBjblzm8k6cI7rVj8KmMa18T740XdT2srzSDHDAhLZwJbrcGTI/KO2Cr2UhICKy+X9C3YPT03uHAkBDWHqrF7+v80rXeoaiq+bRJzSRBj/t9re8NDp94Zq8AVNZ1mHom3PNiiB7N0B8AsAgqMGinlj7A/4JoYq47XdjAkEAprnppgNivo8300nGO4jlkp/pV2S3Mlzc9lBivMuKQUTaMIJWTGVKwavQTItkt8cSf1hvELhLxnF9bjNHYAFy4w==";
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPhR5ujoCZyAqxj1HbWK5nlzv7/c94qLipz45rATuHn+G+uQX5H/3hmpd0/7j5nVwKDTcc8M1igYi+E+Tt1vWPmizMrCeg9wR+P4z7g57OacDCK5TqyoBC8pRBSc2+DrPeLRJ/46FDwE6/BOHA4W4luCpy98aRPG6Q70B27hA1NjAgMBAAECgYEAu6KfmeQjxxdOsggdj91KNAGrpZ2hixXQArbtEaWFIuFJVaF9JcQyni94kX5jzvk+mgfMGoj3lcqef+/mBdqqXlKBLoMUGKdBxZQuE/mIIHUTS31uyxVNs+riXp8zoEKzy6XP6hJs+01/coXVcO4axPpZdZ6OdLIEIMuRqDcCr8kCQQD+nrLKEk54Nv+OxWAPBZFu656HFv1C9wC+Dqki2b5ReaXMa3WUfDprO683J2gDRxOt48NIw5mvHe+cfnhi3bS/AkEA+ap2Qtfh1HDK/aHQ2iZlUyfC2inel4xq7M6hrmfFUe+L/CmYh/I/MqqVS2qKe+iPM3Dhi3Cl9DPCfmGzkd/WXQJAKYg1XaSyzAUclms3ifu8hz1DtIFZekzUACxgBmFCHAQduURl/3xsAwXPRM3Wt4ImgZy/Q+Uz8k6NwRcYB/hl/QJAJw4vac1zmlP6rSp/DWEsQJHbvV0HYRWqNFkumaXKDEM41r17CukuUbK1oBKOj0uV+yoRXkrE6yqdpj+KY6tsUQJAFFfhcS1eCMZtx4wRhYS5PeYhyOjOSBTc9p+Zq7EuMgi4N70OS6/ulkplMJ2svDkhdlA/oADw0QELmg6bHEMMLQ==";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	//public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnVnzBOln5iWoebjtPVqnwNKPuLg+4iaW4OZvVDqZMh99w07rfF40ApSGtzrIgY9C4mlH8NZBBCcOwnXaLikWK0su2+FBEy1wz0LBGy/I78L74cHY0rWqZKmGPg6cfTC4k5sh51TOxnMQIAryvF+rGJs9eq9/7xyrmIhf1crKIOQIDAQAB";
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
