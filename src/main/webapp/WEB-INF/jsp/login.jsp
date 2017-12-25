<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<br>
		<a href="javascript:;" onclick="sendXml();">发送xml请求</a></br>
		<a href="/info/listView" >信息列表</a></br>
		<a href="/info/introduction" >平台介绍及使用说明</a>
	</div>
	<script type="text/javascript" src="/public/javascripts/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function sendXml(){
			var data = '<?xml version="1.0" encoding="UTF-8"?>' +
					'<xml><ToUserName><![CDATA[toUser]]></ToUserName>' +
					'<FromUserName><![CDATA[fromUser]]></FromUserName>' +
					'<CreateTime>1348831860</CreateTime>' +
					'<MsgType><![CDATA[text]]></MsgType>' +
					'<Content><![CDATA[this is a test]]></Content>' +
					'<MsgId>1234567890123456</MsgId>' +
					'</xml>';
			$.ajax({
				url:"/wechat/replay",
				data:data,
				contentType:"application/xml",
				type:"post",
				success:function(data){
					alert(data);
				}
			});
		}
	</script>
</body>
</html>