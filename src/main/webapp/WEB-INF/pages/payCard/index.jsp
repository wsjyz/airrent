<%
	/* *
	 *功能：支付宝手机网页支付调试入口页面
	 *版本：3.3
	 *日期：2012-08-17
	 *说明：
	 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>支付宝手机网页支付</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name=”viewport” content=”width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no”/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-heading">
	            <div class="panel-title">支付宝手机网页支付快速通道</div>
	            <div style="float:right; font-size: 60%; position: relative;">
	            	<a target="_blank" href="http://www.alipay.com/"><span>支付宝首页</span></a>|
	                <a target="_blank" href="https://b.alipay.com/home.htm"><span>商家服务</span></a>|
	                <a target="_blank" href="http://help.alipay.com/support/index_sh.htm"><span>帮助中心</span></a>
	            </div>
	        </div>
	        <div class="panel-body">
				<form name=alipayment action="<%=request.getContextPath()%>/UserOrderService/toAlipayapi" method=post>
				  <div class="form-group">
				    <label for="WIDseller_email">卖家支付宝帐户：</label>
				    <input type="text" class="form-control" id="WIDseller_email" name="WIDseller_email" readonly="readonly" value="<%=request.getAttribute("WIDseller_email")%>">
				  </div>
				  <div class="form-group">
				    <label for="WIDout_trade_no">商户订单号：</label>
				    <input type="text" class="form-control" id="WIDout_trade_no" name="WIDout_trade_no" readonly="readonly"value="<%=request.getAttribute("WIDout_trade_no")%>">
				  </div>
				  <div class="form-group">
				    <label for="WIDsubject">订单名称：</label>
				    <input type="text" class="form-control" id="WIDsubject" name="WIDsubject" readonly="readonly" value="<%=request.getAttribute("WIDsubject")%>">
				  </div>
				  <div class="form-group">
				    <label for="WIDtotal_fee">付款金额：</label>
				    <input type="text" class="form-control" id="WIDtotal_fee" name="WIDtotal_fee" readonly="readonly"  value="<%=request.getAttribute("WIDtotal_fee")%>">
				  </div>
				  <button type="submit" class="btn btn-primary btn-block">确认</button>
				  
				  <div class="form-group" style="margin-top:10px;">
                      <div class="col-md-12 control">
                          <div style="border-top: 1px solid#888;margin-top:10px; font-size:85%" >
                              如果您点击“确认”按钮，即表示您同意该次的执行操作。
                          </div>
                          <div style="font-size:88%" >支付宝版权所有 2011-2015 ALIPAY.COM</div>
                      </div>
                  </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>