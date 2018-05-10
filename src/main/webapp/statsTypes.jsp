<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--分析类型</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
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
							<span class="card-title activator grey-text text-darken-4">成绩列表<i
								class="material-icons right">more_vert</i></span>
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
							<span class="card-title activator grey-text text-darken-4">成绩柱状图<i
								class="material-icons right">more_vert</i></span>
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
							<span class="card-title activator grey-text text-darken-4">题目分析<i
								class="material-icons right">more_vert</i></span>
							<p><a href="<s:url action="questionstats"/>">分析题库中某道题的答题情况</a></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<%@ include file="include/footer.jsp"%>

	<!-- 用户登陆对话框 -->
	<div id="logindialog" class="modal modal-fixed-footer" style="height: 360px;">
        <div class="modal-content">
            <h4>输入登录信息</h4>
            <form name="loginform1" method="post" action="login" class="col s12" style="margin-top: 40px;">
            	<div class="row">
                    <div class="input-field col s11" style="margin: 0 auto;">
                        <i class="material-icons prefix">account_circle</i>
                        <input type="text" placeholder="输入学号" id="registerNo" name="registerNo" class="validate" style="font-size:large">
                        <label for="registerNo">学号</label>
                    </div>
                    <div class="input-field col s11">
                        <i class="material-icons prefix">phone</i>
                        <input type="password" placeholder="输入密码" id="password" name="password" class="validate" style="font-size:large">
                        <label for="password">密码</label>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" onclick="loginform1.submit();"
            class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
        </div>
    </div>
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