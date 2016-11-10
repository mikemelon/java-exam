<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--考试列表</title>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	font-family: Roboto, "Microsoft YaHei";
}

.mytable {
	border-collapse: collapse;
	border-bottom: 2px solid #ddd;
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
		<div class="container" style="height: 350px;">
			<table class="mytable">
				<thead>
					<tr>
						<th>序号</th>
						<th>考试名称</th>
						<th>说明</th>
						<th>题型及分数详情</th>
					</tr>
				</thead>
				<s:iterator value="studentExamList" status="st" var="item">
					<tr
						style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
						<td><s:property value="#st.index+1" /></td>
						<td><a class="namelink"
							href="<s:url action="examdetail2"><s:param name="exam_id" value="%{exam.id}"></s:param></s:url>"><s:property
									value="exam.name" /></a></td>
						<td><s:property value="exam.detail" /></td>
						<td>
							包含选择题<font color="#b71c1c"><s:property value="#attr['EXAM_QUESTION_'+exam.id].CHOICE_LIST.size" /></font>题，
							每题<font color="blue"><s:property value="examStrategy.choicePerScore"/></font>分，<br>
							填空题<font color="#b71c1c"><s:property value="#attr['EXAM_QUESTION_'+exam.id].BLANK_LIST.size" /></font>题
							（共<font color="#b71c1c"><s:property value="#attr['EXAM_QUESTION_'+exam.id].BLANK_CNT" /></font>空），
							每空<font color="blue"><s:property value="examStrategy.blankPerScore"/></font>分，<br>
							判断题<font color="#b71c1c"><s:property value="#attr['EXAM_QUESTION_'+exam.id].JUDGE_LIST.size" /></font>题，
							每题<font color="blue"><s:property value="examStrategy.judgePerScore"/></font>分<br>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<%@ include file="include/footer.jsp" %>
	
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