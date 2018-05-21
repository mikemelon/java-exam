<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Java考试系统--考试列表</title>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
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
		<div class="container" style="min-height: 350px;">
			<h4 style="text-align:center;" class="teal-text text-lighten-3">待参加考试列表</h4>
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
						<td>
							<a class="namelink" href=# 
							onclick='beginExamConfirm("<s:url action="examdetail2"><s:param name="exam_id" value="%{exam.id}"></s:param><s:param name="exam_strategy_id" value="%{examStrategy.id}"></s:param></s:url>",
								encodeURIComponent("<s:property value="exam.name" />"),
								"<s:property value="%{getText('{0,date,yyyy-MM-dd HH:mm:ss}',{examStartTime})}"/>",
								<s:property value="exam.scheduledTime"/>)'>
							<s:property value="exam.name" /></a>
						</td>
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
			
			<h4 style="text-align:center;" class="teal-text text-lighten-3">已完成考试列表</h4>
			<table class="mytable">
				<thead>
					<tr>
						<th>序号</th>
						<th>考试名称</th>
						<th>完成用时</th>
						<th>考试类型</th>
						<th>成绩</th>
					</tr>
				</thead>
				<s:iterator value="studentFinishedExamList" status="st" var="item">
					<tr
							style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
						<td><s:property value="#st.index+1" /></td>
						<td><s:property value="exam.name" /></td>
						<td>
							<s:if test="timeUsed != 0">
								<s:if test="timeUsed/3600 != 0"><s:property value="timeUsed/3600"/>小时</s:if>
								<s:property value="(timeUsed-timeUsed/3600*60)/60" />分<s:property value="timeUsed%60" />秒
							</s:if>
							<s:else>用时未知</s:else>
						</td>
						<td><s:if test="exam.type==1">随机抽题组卷</s:if><s:else>固定组卷</s:else></td>
						<td>
							<a class="namelink"
							href="<s:url action="examdetailwithanswer"><s:param name="stu_id" value="%{student.id}"></s:param><s:param name="exam_id" value="%{exam.id}"></s:param><s:param name="exam_strategy_id" value="%{examStrategy.id}"></s:param></s:url>">
								<s:property value="score" />
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<%@ include file="include/footer.jsp" %>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	
	<script>
		function beginExamConfirm(examUrl,examName,examStartTime,scheduledTime){
			var scheduledTimeInMin = scheduledTime/60;
			if(examStartTime==""){
				if(window.confirm("“确定”后，["+
						decodeURIComponent(examName)
						+"]即将计时开始，本次考试时间为"+scheduledTimeInMin+"分钟")){
					window.location.href=examUrl;
				}
			}else{
				var startTime = new Date(Date.parse(examStartTime));
				if(window.confirm("["+
						decodeURIComponent(examName)+"]，该考试已经于"
						+startTime.toLocaleString()+"开始，“确定”后继续（总考试时间为"+scheduledTimeInMin+"分钟）")){
					window.location.href=examUrl;
				}
			}
		}
	
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