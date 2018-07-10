<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--按知识点查看题目</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/materialize.min.css">
<link rel="stylesheet" type="text/css" href="css/material_icons.css">
<link type="text/css" rel="stylesheet" href="css/jquery.jOrgChart.lyq.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	font-family: Roboto, "Microsoft YaHei";
}
footer.page-footer{
	margin-top: 0;
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<div id="main" style=" background: url(images/bkgd.png); overflow-x: scroll">
		<div class="container" style="padding: 20px;width: 100%;max-width: 100%;">

			<ul id="org" style="display: none">
				<li>Java程序设计
					<ul>
						<li>编程基础<br>(面向过程)
							<ul>
								<li>标识符与注释</li>
								<li>基本数据类型</li>
								<li>流程控制
									<ul>
										<li>选择结构
											<ul>
												<li>if...else语句</li>
												<li>switch...case语句</li>
											</ul>										
										</li>
										<li>循环结构
											<ul>
												<li>while循环</li>
												<li>do...while循环</li>
												<li>for循环</li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>面向对象
							<ul>
								<li>封装
									<ul>
										<li>成员变量与成员方法</li>
										<li>this关键字</li>
									</ul>
								</li>
								<li>继承
									<ul>
										<li>super关键字</li>
										<li>方法的重写</li>
										<li>final关键字</li>
									</ul>								
								</li>
								<li>多态
									<ul>
										<li>抽象类</li>
										<li>接口</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
			<div id="chart" class="orgChart"></div>
		</div>
	</div>

	<%@ include file="include/footer.jsp"%>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script type="text/javascript" src="js/jquery.jOrgChart.lyq.js"></script>

	<script>
		$(document).ready(function() {
	        $('.modal-trigger').leanModal({
	            dismissible: true, //是否点模态对话框外面就可以关闭
	            opacity: 0.6, //接近1，不透明
	        });//使用模态对话框，必须有这句
	        
			$("#org").jOrgChart({
				chartElement : '#chart',
				dragAndDrop : false
			//设置是否可拖动  
			});
		})
	</script>
</body>
</html>