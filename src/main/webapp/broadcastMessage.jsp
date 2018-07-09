<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--推送广播消息</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="main">
		<div class="container" style="min-height:370px;">
			<form name="form1" action="sendpushmessage" method="post">
				<table>
					<tr>
						<td  width="40%">输入要推送给所有在线用户的消息：</td>
						<td>
							<textarea id="message" name="message" class="materialize-textarea" 
							placeholder="请输入要推送给所有在线用户的消息"></textarea>
						</td>
					</tr>
				</table>
				<input type="button" value="确定发送" onclick="ajaxPost()">
			</form>
		</div>
	</div>
	<%@ include file="include/footer.jsp" %>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script>
		function ajaxPost(){
			$.post("sendpushmessage",
					{message:form1.message.value},
					function(data,status){
						console.log(data);
					});
		}
	</script>
	<s:if test="hasActionMessages()">
	<script type="text/javascript">
		toast2();
		function toast2(){
			var msgStr='<s:property value="actionMessages[0]"/>';
		    var $toastContent = $('<span class="teal-text lighten-5"><h4>'+msgStr+'</h4></span>');
		    Materialize.toast($toastContent, 4000, 'rounded');
		}
	</script>
	</s:if>
	

</body>
</html>