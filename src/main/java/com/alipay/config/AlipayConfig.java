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
	public static String key = "y3un70nvlitvugizic58yks945m701lg";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	//public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK1nciy0qOfFzW2IGuGRmLDhSunH3zzrf+mZf9q7tsynHHab7MxTiDvamh0GLD3kzRAYz17RebGJS191gfX8LYAksxypLtHd8/yAOU83ZMV4K/9Wl2BNR9G2axCWkuU4FwHfIDjF2TdOZhTEVbZsUzwTUaVL1oyvcWXAHnUOi/YVAgMBAAECgYA24OdEeMR/3Zz/DjUbsF13lUWFKUlgWVO5FWheTEw1Bqo+a2iM3d7eundNujdkOzYe4ws2AeuWoVHmWyUPOYkYYDcmhyMgVoA6P57t7xDQif5+MKbwhzSyQQO8lWeBeAxIWyQif6nz+UGfkvqzaHmH0ZObaFwN2f8QRsym+vv13QJBAOSUZu1iW0+7MYMwIqJlYjrSS9tFOmkk9YBzoqVsSgsWLiKzbSKkHMzZ7GljsE3SJCebmyJZQ+3rOqYlOOQd2T8CQQDCNJ8Rnyv/dYv7FV/N5ovywC4ncDNE032ufj3NwDZwrmwW0R8ToaynRZJiTFr5G0yvOgIQz4Vh98q7ODKnbOerAkA8M+3sBeTA2i/POqVUmllF3s+F3/Tjbo2OmGY1JZFW3C+oihNrdUf0mE0Q8OWliXxmRjCU2mfuyO64hcM3KblnAkBfrQr85H0JksCOx132k3E4+8MBPP6VFthhQeJy3hIz+0pXB1mXE1x64ASZFuLuvKtP3HUuEP62YBxplesmnrmXAkEAp2/rqqQqfbPQ2/HChbezIXvPKlQMZIc1V8HatGgNcVvzlpbscC9+L0J0o4zq+yOiqht4hYdz5HtyvUMWxE6V9Q==";
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPhR5ujoCZyAqxj1HbWK5nlzv7/c94qLipz45rATuHn+G+uQX5H/3hmpd0/7j5nVwKDTcc8M1igYi+E+Tt1vWPmizMrCeg9wR+P4z7g57OacDCK5TqyoBC8pRBSc2+DrPeLRJ/46FDwE6/BOHA4W4luCpy98aRPG6Q70B27hA1NjAgMBAAECgYEAu6KfmeQjxxdOsggdj91KNAGrpZ2hixXQArbtEaWFIuFJVaF9JcQyni94kX5jzvk+mgfMGoj3lcqef+/mBdqqXlKBLoMUGKdBxZQuE/mIIHUTS31uyxVNs+riXp8zoEKzy6XP6hJs+01/coXVcO4axPpZdZ6OdLIEIMuRqDcCr8kCQQD+nrLKEk54Nv+OxWAPBZFu656HFv1C9wC+Dqki2b5ReaXMa3WUfDprO683J2gDRxOt48NIw5mvHe+cfnhi3bS/AkEA+ap2Qtfh1HDK/aHQ2iZlUyfC2inel4xq7M6hrmfFUe+L/CmYh/I/MqqVS2qKe+iPM3Dhi3Cl9DPCfmGzkd/WXQJAKYg1XaSyzAUclms3ifu8hz1DtIFZekzUACxgBmFCHAQduURl/3xsAwXPRM3Wt4ImgZy/Q+Uz8k6NwRcYB/hl/QJAJw4vac1zmlP6rSp/DWEsQJHbvV0HYRWqNFkumaXKDEM41r17CukuUbK1oBKOj0uV+yoRXkrE6yqdpj+KY6tsUQJAFFfhcS1eCMZtx4wRhYS5PeYhyOjOSBTc9p+Zq7EuMgi4N70OS6/ulkplMJ2svDkhdlA/oADw0QELmg6bHEMMLQ==";
	
	// 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	//public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMhWHkVDmgMTcAQnGx27kYta5mw50giyVWMZ9jVByJz25UVU/NbT0AFEj/2DqsSgZaOPz4GunWHnQa6FE3i9aKETlqm3Fjz2jPuQVqugXNVBc4MUzcgwM36l8QTiGIl5vhXrwWNnf1dWR+4oifWmja4HdD8cxn1H2YS3wCwvCScQIDAQAB";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD4Uebo6AmcgKsY9R21iuZ5c7+/3PeKi4qc+OawE7h5/hvrkF+R/94ZqXdP+4+Z1cCg03HPDNYoGIvhPk7db1j5oszKwnoPcEfj+M+4OezmnAwiuU6sqAQvKUQUnNvg6z3i0Sf+OhQ8BOvwThwOFuJbgqcvfGkTxukO9Adu4QNTYwIDAQAB";


	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
