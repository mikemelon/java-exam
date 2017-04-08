<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--随机抽题组卷</title>
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
td[id*="ScoreText"] a{
	display:none;
}
td[id*="ScoreText"]:hover a{
	display:inline-block;
}
td[id^="strategyName"] input,td[id^="strategyName"] a{
	display:none;
}
td[id^="strategyName"]:hover input,td[id^="strategyName"]:hover a{
	display:inline-block;
}
td[id^="strategyName"]:hover > span{
	display:none;
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<form name="form1" method="post">
	<input type="hidden" name="select_student_for" value="exam_compose_random">
	<div id="main">
		<div class="container" style="min-height:370px;">
			  		
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
		function changeChoicePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updatechoicescore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#choiceScoreText"+strategyId).html(data.choicePerScore+"<a href=# "+"' onclick='changeChoicePerScore(true,"
								+data.choicePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeChoicePerScore(false,"
								+data.choicePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html( (data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt)
								+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
		}
		function changeBlankPerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updateblankscore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#blankScoreText"+strategyId).html(data.blankPerScore+"<a href=# "+"' onclick='changeBlankPerScore(true,"
								+data.blankPerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeBlankPerScore(false,"
								+data.blankPerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html( (data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt) 
								+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
		}
		function changeJudgePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updatejudgescore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#judgeScoreText"+strategyId).html(data.judgePerScore+"<a href=# "+"' onclick='changeJudgePerScore(true,"
								+data.judgePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeJudgePerScore(false,"
								+data.judgePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html((data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt)
											+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
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
	
	<s:debug></s:debug>
</body>
</html>