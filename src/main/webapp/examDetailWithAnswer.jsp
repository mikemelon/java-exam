<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--试卷详细（含答案）</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	font-family: Roboto, "Microsoft YaHei";
}

#main {
	
}

h2 {
	display: block;
	margin: 0 auto;
	text-align: center;
}

.mytable {
	border-collapse: collapse;
	border-top: 2px solid #ddd;
	border-bottom: 2px solid #ddd;
	margin: 20px auto;
	width: 80%;
}

.mytable tr {
	width: 80%;
}

.mytable td {
	padding: 5px 10px;
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<div id="main">
		<h5 style="text-align:center;" class="light-green-text text-darken-1">
			<s:property value="student.name"/>的"<s:property value="exam.name"/>"考试(exam_id=<s:property value="exam.id"/>)的答卷情况如下：
		</h5>
		<hr>
		<h5 style="text-align:center; ">
			选择题(共<s:property value="%{examQuestionAnswerMap.CHOICE.size()}" />题，每题
			<s:property value="examStrategy.choicePerScore" />
			分)
		</h5>
		<table class="mytable">

			<s:iterator value="choiceList" status="st" var="item">
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1" />.</td>
					<td style="text-align: left;"><s:property
							value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@showContentWithImage(content)"
							escapeHtml="false" /></td>
				</tr>
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td colspan="2">
						<table>
							<tr>
								<td>A.</td>
								<td><s:property value="choiceA" /></td>
							</tr>
							<tr>
								<td>B.</td>
								<td><s:property value="choiceB" /></td>
							</tr>
							<tr>
								<td>C.</td>
								<td><s:property value="choiceC" /></td>
							</tr>
							<tr>
								<td>D.</td>
								<td><s:property value="choiceD" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>;<s:if test="%{examQuestionAnswerMap.CHOICE[#st.index]!=examAnswerMap.CHOICE[#st.index]}">color:red;</s:if>">
					<td colspan="2">你的回答是：<s:property
							value="%{examQuestionAnswerMap.CHOICE[#st.index]}" /> , 正确答案是：<s:property
							value="%{examAnswerMap.CHOICE[#st.index]}" /></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 20px;"></td>
				</tr>
			</s:iterator>
		</table>

		<h5 style="text-align:center; ">
			填空题(共<s:property value="%{examQuestionAnswerMap.BLANK_FILLING.size()}" />题，每空
			<s:property value="examStrategy.blankPerScore" />
			分)
		</h5>
		<table class="mytable">
			<s:iterator value="blankFillingList" status="st" var="item">
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1" />.</td>
					<td style="text-align: left;"><s:property value="content" /></td>
				</tr>
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>;<s:if test="%{examQuestionAnswerMap.BLANK_FILLING[#st.index][0]!=examAnswerMap.BLANK_FILLING[#st.index][0]}">color:red;</s:if>">
					<td colspan="2">你的回答是：<s:property
							value="%{examQuestionAnswerMap.BLANK_FILLING[#st.index][0]}" /> ,
						正确答案是：<s:property
							value="%{examAnswerMap.BLANK_FILLING[#st.index][0]}" /></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 20px"></td>
				</tr>
			</s:iterator>
		</table>

		<h5 style="text-align:center; ">
			判断题(共<s:property value="%{examQuestionAnswerMap.JUDGE.size()}" />题，每题
			<s:property value="examStrategy.judgePerScore" />
			分)
		</h5>
		<table class="mytable">
			<s:iterator value="judgeList" status="st" var="item">
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1" />.</td>
					<td style="text-align: left;"><s:property value="content" /></td>
				</tr>
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>;<s:if test="%{examQuestionAnswerMap.JUDGE[#st.index]!=examAnswerMap.JUDGE[#st.index]}">color:red;</s:if>">
					<td colspan="2">你的回答是：<s:property
							value="%{examQuestionAnswerMap.JUDGE[#st.index]}" /> , 正确答案是：<s:property
							value="%{examAnswerMap.JUDGE[#st.index]}" /></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 20px"></td>
				</tr>
			</s:iterator>
		</table>
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