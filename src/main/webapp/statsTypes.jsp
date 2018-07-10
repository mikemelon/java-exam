<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--分析类型</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	font-family: Roboto, "Microsoft YaHei";
}
.my-opacity{
	opacity:0.6;
}
.my-opacity:hover{
	opacity:1;
}
a{
	color: #2e7d32;
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>

	<div id="main">
		<div class="container" style="padding: 20px;">
			<div class="divider" style="height: 20px; background: #fff;"></div>
			<div class="row">
			
				<div class="col s12 m4">
					<div class="card">
						<div class="card-image waves-effect waves-block waves-light my-opacity">
							<a href="<s:url action="examscorelist"/>"><img class="activator" src="images/bar_stats1.png"></a>
						</div>
						<div class="card-content">
							<a href="<s:url action="examscorelist"/>">
							<span class="card-title activator light-green-text text-darken-4">成绩列表
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
							<p><a href="<s:url action="examscorelist"/>">列出所有学生成绩</a></p>
						</div>
					</div>
				</div>
				
				<div class="col s12 m4">
					<div class="card">
						<div class="card-image waves-effect waves-block waves-light my-opacity">
							<a href="<s:url action="scorestats"/>"><img class="activator" src="images/bar_stats2.png"></a>
						</div>
						<div class="card-content">
							<a href="<s:url action="scorestats"/>">
							<span class="card-title activator light-green-text text-darken-4">成绩柱状图
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
							<p><a href="<s:url action="scorestats"/>">以柱状图显示成绩</a></p>
						</div>
					</div>
				</div>
				
				<div class="col s12 m4">
					<div class="card">
						<div class="card-image waves-effect waves-block waves-light my-opacity">
							<a href="<s:url action="questionstats"/>"><img class="activator" src="images/bar_stats3.png"></a>
						</div>
						<div class="card-content">
							<a href="<s:url action="questionstats"/>">
							<span class="card-title activator light-green-text text-darken-4">题目分析
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
							<p><a href="<s:url action="questionstats"/>">分析题库中某道题的答题情况</a></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<%@ include file="include/footer.jsp"%>

	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	<script>
	   $(document).ready(function () {
	       $('.modal-trigger').leanModal({
	           dismissible: true, //是否点模态对话框外面就可以关闭
	           opacity: 0.6, //接近1，不透明
	       });//使用模态对话框，必须有这句
	       
	       $(".button-collapse").sideNav({
	           menuWidth: 200, // Default is 240
	           edge: 'left', // Choose the horizontal origin
	           closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
	       });
	       
	       $(".collapsible").collapsible({
	           accordion: true
	       });
	   })
	  	
	</script>
</body>
</html>