<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--填空题详细</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
	body {
		font-family: Roboto, "Microsoft YaHei";
	}
	#main{
		margin-top: 20px;
		min-height: 310px;
	}
	.mytable {
    	width: 80%;
    	margin: 0 auto;
    	font-size: large;
		border-collapse: collapse;
	}
	
	.mytable td {
		padding: 5px 10px;
	}
	
	.namelink a {
		text-decoration: none;
	}
	
	.namelink a:LINK {
		color: #12f;
	}
	
	.namelink a:VISITED {
		color: #a2e;
	}
	
	.namelink a:HOVER {
		color: #17f;
	}
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="main">
		<table class="mytable">
			<tr style="background-color:#f9fbe7">
				<td style="width:220px;">
					<button class="orange darken-4 waves-effect waves-teal btn-flat" type="button" name="action">
						<span class="yellow-text text-lighten-4">第<s:property value="question.id"/>题
			       		<i class="material-icons left">question_answer</i></span>
			   		</button>
				</td>
				<td style="text-align:left;">
					<s:property value="question.content"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"  class="red-text text-darken-2" style="height:20px;">
					<span class="blue-text text-darken-2">答案：</span><s:property value="question.answer"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button class="teal darken-4 waves-effect waves-teal btn-flat" type="button" name="action" onclick="window.history.go(-1);">
						<span class="yellow-text text-lighten-1">返回
			       		<i class="material-icons right">arrow_back</i></span>
			   		</button>
				</td>
			</tr>
		</table>
		
	</div>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>