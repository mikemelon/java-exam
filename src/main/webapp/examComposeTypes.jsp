<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--试题类型</title>
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
				<div class="col s12 m6">
					<a href="<s:url action="examcompose"/>">
						<div class="card">
							<div
								class="card-image waves-effect waves-block waves-light my-opacity">
								<img class="activator" src="images/choicequestion1.jpg">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4">固定组卷<i
									class="material-icons right">more_vert</i></span>
								<p>固定组卷</p>
							</div>
						</div>
					</a>
				</div>
				<div class="col s12 m6">
					<a href="<s:url action="examcomposerandom"/>">
						<div class="card">
							<div
								class="card-image waves-effect waves-block waves-light my-opacity">
								<img class="activator" src="images/blankquestion1.jpg">
							</div>
							<div class="card-content">
								<span class="card-title activator grey-text text-darken-4">随机抽题组卷<i
									class="material-icons right">more_vert</i></span>
								<p>随机抽题组卷</p>
							</div>
						</div>
					</a>
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
<s:debug></s:debug>
</body>
</html>