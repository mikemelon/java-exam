<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--随机抽题组卷</title>
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
	table caption{
		font-size: 20px;
		font-weight: lighter;
		color: #1010ef;
		
	}
	td[id*="PerScoreTd"] a{
		display:none;
	}
	td[id*="PerScoreTd"]:hover a{
		display:inline-block;
	}
	td[id*="QuestionNumTd"]  a{
		display:none;
	}
	td[id*="QuestionNumTd"]:hover a{
		display:inline-block;
	}
	
	caption[id="strategyName"] input{
		display:none;
	}
	caption[id="strategyName"]:hover input{
		display:inline-block;
	}
	caption[id="strategyName"]:hover > span{
		display:none;
	}
	
	div[id="examName"] input{
		display:none;
	}
	div[id="examName"]:hover input{
		display:inline-block;
	}
	div[id="examName"]:hover > span{
		display:none;
	}
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<form name="form1" method="post">
	<input type="hidden" name="select_student_for" value="exam_compose_random">
	<input type="hidden" name="choiceQuestionNum" value="<s:property value="choiceQuestionNum"/>">
	<input type="hidden" name="choicePerScore" value="<s:property value="choicePerScore"/>">
	<input type="hidden" name="blankQuestionNum" value="<s:property value="blankQuestionNum"/>">
	<input type="hidden" name="blankPerScore" value="<s:property value="blankPerScore"/>">
	<input type="hidden" name="judgeQuestionNum" value="<s:property value="judgeQuestionNum"/>">
	<input type="hidden" name="judgePerScore" value="<s:property value="judgePerScore"/>">
	<div id="main">
		<div class="container" style="min-height:370px;">
			<div id="examName" style="margin-top:20px;font-size:20px;">
				考试名称：
				<span id="examNameSp"><s:property value="examName" /></span>
				<input type='text' style='width:200px;' name='examName' 
							value='<s:property value="examName" />' onchange="changeExamNameSpan()">
			</div>
			<table style="margin-top:20px;">
				<caption id="strategyName"><span id="strategyNameSp"><s:property value="examStrategyName" /></span><input type='text' style='width:200px;' name='examStrategyName' 
							value='<s:property value="examStrategyName" />' onchange="changeStrategyNameSpan()"></caption>
				<thead>
					<tr>
						<th>选择题个数</th>
						<th>选择题每题分数</th>
						<th>填空题个数</th>
						<th>填空题每空分数</th>
						<th>判断题个数</th>
						<th>判断题每题分数</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="choiceQuestionNumTd">
							<span id="choiceQuestionNum"><s:property value="choiceQuestionNum"/></span>
							<a href=# onclick='changeChoiceQuestionNum(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeChoiceQuestionNum(false);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td  id="choicePerScoreTd">
							<span id="choicePerScore"><s:property value="choicePerScore"/></span>
							<a href=# onclick='changeChoicePerScore(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeChoicePerScore(false);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td  id="blankQuestionNumTd">
							<span id="blankQuestionNum"><s:property value="blankQuestionNum"/></span>
							<a href=# onclick='changeBlankQuestionNum(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeBlankQuestionNum(false);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id="blankPerScoreTd">
							<span id="blankPerScore"><s:property value="blankPerScore"/></span>
							<a href=# onclick='changeBlankPerScore(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeBlankPerScore(false);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id="judgeQuestionNumTd">
							<span id="judgeQuestionNum"><s:property value="judgeQuestionNum"/></span>
							<a href=# onclick='changeJudgeQuestionNum(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeJudgeQuestionNum(false);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id="judgePerScoreTd">
							<span id="judgePerScore"><s:property value="judgePerScore"/></span>
							<a href=# onclick='changeJudgePerScore(true);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeJudgePerScore(false);'><img src='images/minus1.png' width='20'></a>
						</td>
					</tr>
					<tr>
						<td colspan="6">总分：<span id="totalScoreText"></span></td>
					</tr>
				</tbody>
			</table>  		
    		<button class="purple darken-4 waves-effect waves-teal btn-flat" 
			type="button" style="margin-top:20px;" id="selectStudentButton"
			onclick="form1.action='studentselect';form1.submit();">
				<span class="yellow-text text-lighten-1">指定参加考试的学生
        		<i class="material-icons right">loop</i></span>
    		</button>
    		
    		<div id="students" style="margin-top:20px;">
    			<s:iterator value="studentList" status="st" var="item">
    				<div class="chip" style="margin:2px;">
				        <img src="images/soccer4.jpg" alt="大黄鸭">
				        <s:property value="name"/>(学号:<s:property value="registerNo"/>)
				    </div>
    			</s:iterator>
    		</div>
    		
    		<button class="red darken-4 waves-effect waves-teal btn-flat" 
			type="button" style="margin-top:20px;<s:if test='studentList==null || studentList.size==0'>display:none;</s:if>" id="selectStudentButton"
			onclick="form1.action='createstudentexamrandom';form1.submit();">
				<span class="yellow-text text-lighten-1">为这些学生随机抽题创建考试
        		<i class="material-icons right">loop</i></span>
    		</button>
		</div>
	</div>
	</form>
	<%@ include file="include/footer.jsp" %>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	
	<script>
		function changeChoiceQuestionNum(plusOrMinus){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			if(plusOrMinus) {
				choiceQuestionNum++; 
			}else {
				choiceQuestionNum--;
			}
			$("#choiceQuestionNum").text(choiceQuestionNum);
			form1.choiceQuestionNum.value = choiceQuestionNum;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);
		}
		
		function changeBlankQuestionNum(plusOrMinus){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			if(plusOrMinus) {
				blankQuestionNum++; 
			}else {
				blankQuestionNum--;
			}
			$("#blankQuestionNum").text(blankQuestionNum);
			form1.blankQuestionNum.value = blankQuestionNum;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);					
		}
		
		function changeJudgeQuestionNum(plusOrMinus){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			if(plusOrMinus) {
				judgeQuestionNum++; 
			}else {
				judgeQuestionNum--;
			}
			$("#judgeQuestionNum").text(judgeQuestionNum);
			form1.judgeQuestionNum.value = judgeQuestionNum;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);	
		}
	
		function changeChoicePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			if(plusOrMinus) {
				choicePerScore++; 
			}else {
				choicePerScore--;
			}
			$("#choicePerScore").text(choicePerScore);
			form1.choicePerScore.value = choicePerScore;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);
		}
		
		function changeBlankPerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			if(plusOrMinus) {
				blankPerScore++; 
			}else {
				blankPerScore--;
			}
			$("#blankPerScore").text(blankPerScore);
			form1.blankPerScore.value = blankPerScore;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);
		}
		
		function changeJudgePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			if(plusOrMinus) {
				judgePerScore++; 
			}else {
				judgePerScore--;
			}
			$("#judgePerScore").text(judgePerScore);
			form1.judgePerScore.value = judgePerScore;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);
		}
		
		function changeStrategyNameSpan(){
			$("#strategyNameSp").text(form1.examStrategyName.value);
		}
		
		function changeExamNameSpan(){
			$("#examNameSp").text(form1.examName.value);
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
	        
	        var choiceQuestionNum = $("#choiceQuestionNum").text()-0;
			var choicePerScore = $("#choicePerScore").text()-0;
			var blankQuestionNum = $("#blankQuestionNum").text()-0;
			var blankPerScore = $("#blankPerScore").text()-0;
			var judgeQuestionNum = $("#judgeQuestionNum").text()-0;
			var judgePerScore = $("#judgePerScore").text()-0;
			var totalScore = choiceQuestionNum*choicePerScore + blankQuestionNum*blankPerScore + judgeQuestionNum*judgePerScore;
			$("#totalScoreText").text(totalScore);
	    });
	   	
	</script>
	
	<s:if test="hasActionErrors()">
	<script type="text/javascript">
		toast1();
		function toast1(){
			var errorStr='<s:property value="actionErrors[0]"/>';
		    var $toastContent = $('<span class="red-text lighten-5"><h4>'+errorStr+'</h4></span>');
		    Materialize.toast($toastContent, 4000, 'rounded');
		}
	</script>
	</s:if>
	
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