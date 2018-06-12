<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--修改系统设置</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="main">
		<div class="container" style="min-height:370px;">
			<form name="form1" action="changesettings" method="post">
				<table>
					<tr>
						<td  width="40%">默认（创建的）考试的时长(以秒为单位，例如1小时，则设置为3600；再如100分钟，则设置为6000)</td>
						<td><input type="text" name="defaultExamScheduledTime" value="${defaultExamScheduledTime }"></td>
					</tr>
					<tr>
						<td>学生试卷列表页面（studentExamList.jsp）中是否允许查看试卷详细</td>
						<td>
							<select name="examDetailAllowed" id="examDetailAllowed">
								<option value="true" <s:if test="examDetailAllowed=='true'">selected</s:if>>true</option>
								<option value="false" <s:if test="examDetailAllowed=='false'">selected</s:if>>false</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>考试超时，是否强制自动提交</td>
						<td>
							<select name="examTimeoutAutoSubmit" id="examTimeoutAutoSubmit">
								<option value="true" <s:if test="examTimeoutAutoSubmit=='true'">selected</s:if>>true</option>
								<option value="false" <s:if test="examTimeoutAutoSubmit=='false'">selected</s:if>>false</option>
							</select>
						</td>
					</tr>	
				</table>
				<input type="submit" value="确定修改">
			</form>
		</div>
	</div>
	<%@ include file="include/footer.jsp" %>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('select').material_select();
		});
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