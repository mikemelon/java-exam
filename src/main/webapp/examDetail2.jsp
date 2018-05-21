<%@ page language="java" contentType="text/html; charset=UTF-8"  
	import="com.opensymphony.xwork2.ActionContext,
			java.util.Map,
			java.util.HashMap,
			java.util.List,
			java.util.ArrayList,
			cn.lynu.lyq.java_exam.common.QuestionType"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java考试系统--试卷详细</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<link type="text/css" rel="stylesheet" href="css/nouislider.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
    body {
        font-family: Roboto, "Microsoft YaHei";
        font-size: large;
    }
    .mytable{
    	width: 80%;
    	margin: 0 auto;
    }
    #examtime{
    	position:fixed;
    	bottom: 10px;
    	right: 10px;
    	background-color: #ee1111;
    	color: #eeee11;
    }
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div class="container">
	<form class="col s12" name="form1" method="post" action="examsummary">
	<s:hidden name="remainingTime"></s:hidden>
	<div id="examtime">
		<div id="timeNotice"></div>
		<a id="toggleTimeBtn" class="waves-effect waves-light btn-small" onclick="toggleShowTime()">关闭时间提示</a>
	</div>
	<s:if test="choiceList!=null && choiceList.size()>0"><h4>选择题</h4></s:if>
	<table class="mytable">
	<s:iterator value="choiceList" status="st" var="item">
		<tr>
			<td width="40px" valign="top">
				<span class="blue-text text-lighten-2" style="display:inline-block;width:30px;margin-top:1px;">
				<s:property value="#st.index+1"/>.</span>
			</td>
			<td><s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@showContentWithImage(content)" escapeHtml="false"/></td>
			<td>
				<span id="choice_answer_q<s:property value="#st.index+1"/>" class="blue-text text-darken-2">
					<s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineChoiceAnswer(#st.index)"/>
				</span>
			</td>
		</tr>
		<tr style="border-bottom: 1px solid #eee;"><td colspan="3">
			<table>
				<tr>
					<td width="60px">
						<input  type="<s:if test="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)">checkbox</s:if><s:else>radio</s:else>" 
								name="choice_q<s:property value="#st.index+1"/>" 
								id="q<s:property value="#st.index+1"/>_choiceA" 
								class="with-gap" value="A" <s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineChecked(#st.index,'A')"/>
								onclick="changeChoice(<s:property value='#st.index+1'/>,<s:property value='@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)'/>)" />
						<label for="q<s:property value="#st.index+1"/>_choiceA">A.</label>
					</td>
					<td><s:property value="choiceA"/></td>
				</tr>
				<tr>
					<td width="60px">
						<input 	type="<s:if test="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)">checkbox</s:if><s:else>radio</s:else>" 
							   	name="choice_q<s:property value="#st.index+1"/>" 
							   	id="q<s:property value="#st.index+1"/>_choiceB" 
							   	class="with-gap" value="B" <s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineChecked(#st.index,'B')"/> 
							   	onclick="changeChoice(<s:property value="#st.index+1"/>,<s:property value='@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)'/>)" />
						<label for="q<s:property value="#st.index+1"/>_choiceB">B.</label>
					</td>
					<td><s:property value="choiceB"/></td>
				</tr>
				<tr>
					<td width="60px">
						<input 	type="<s:if test="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)">checkbox</s:if><s:else>radio</s:else>" 
								name="choice_q<s:property value="#st.index+1"/>" 
								id="q<s:property value="#st.index+1"/>_choiceC" 
								class="with-gap" value="C"  <s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineChecked(#st.index,'C')"/>
								onclick="changeChoice(<s:property value="#st.index+1"/>,<s:property value='@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)'/>)" />
						<label for="q<s:property value="#st.index+1"/>_choiceC">C.</label>
					</td>
					<td><s:property value="choiceC"/></td>
				</tr>
				<tr>
					<td width="60px">
						<input 	type="<s:if test="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)">checkbox</s:if><s:else>radio</s:else>" 
								name="choice_q<s:property value="#st.index+1"/>" 
								id="q<s:property value="#st.index+1"/>_choiceD" 
								class="with-gap" value="D"  <s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineChecked(#st.index,'D')"/>
								onclick="changeChoice(<s:property value="#st.index+1"/>,<s:property value='@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@isMultipleChoice1(answer)'/>)" />
						<label for="q<s:property value="#st.index+1"/>_choiceD">D.</label>
					</td>
					<td><s:property value="choiceD"/></td>
				</tr>
			</table>
		</td></tr>
	</s:iterator>
	</table>
	
	<s:if test="blankFillingList!=null && blankFillingList.size()>0"><h4>填空题</h4></s:if>
	<table class="mytable">
	<s:iterator value="blankFillingList" status="st" var="item">
		<tr>
			<td>
				<span class="blue-text text-lighten-2" style="display:inline-block;width:30px;"><s:property value="#st.index+1"/>.</span>
				<span><s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@replaceBlank(content,#st.index+1)" escapeHtml="false"/>
				</span>
			</td>
		</tr>
	</s:iterator>
	</table>
	
	<s:if test="judgeList!=null && judgeList.size()>0"><h4>判断题</h4></s:if>
	<table class="mytable">
	<s:iterator value="judgeList" status="st" var="item">
		<tr>
			<td>
				<span class="blue-text text-lighten-2" style="display:inline-block;width:30px;">
				<s:property value="#st.index+1"/>.</span>
				<span><s:property value="content"/></span>
			</td>
			<td width="150">
				<span class="switch">
                    <label>
                       	 错误
                        <input name="check_judge_q<s:property value="#st.index+1"/>" 
                        id="check_judge_q<s:property value="#st.index+1"/>" type="checkbox"  
                        onchange="changeSwitch(<s:property value="#st.index+1"/>)">
                        <span class="lever"></span>
                                                                       正确
                    </label>
                </span>
			</td>
			<td width="50">
				<span class="teal-text text-lighten-1" id="judge_answer_q<s:property value="#st.index+1"/>">
		        	未答
		        </span>
			</td>
		</tr>
		<input type="hidden" name="judge_q<s:property value="#st.index+1"/>" 
			id="judge_q<s:property value="#st.index+1"/>" 
			value="<s:property value="@cn.lynu.lyq.java_exam.actions.ExamDetailShowAction2@determineJudgeChecked(#st.index)"/>" />
	</s:iterator>
	</table>
	<div class="row">
		<div class="divider" style="height: 10px;background-color: #fff;"></div>
		<div class="col s12">
			<button class="teal darken-4 waves-effect waves-teal btn-flat" type="submit">
				<span class="yellow-text text-lighten-1">交卷
        		<i class="material-icons right">send</i></span>
    		</button>
   		</div>
  	</div>
	</form>
	</div>
	<script type="text/javascript" src="js/nouislider.min.js"></script>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script type="text/javascript">
	
	<s:iterator value="judgeList" status="st" var="item">
	setSwitch(<s:property value="#st.index+1"/>);
	</s:iterator>
	
		function changeChoice(idx,isMultiple){
			if(isMultiple){
				var answerStr ='';
				for(var i=0;i<document.form1["choice_q"+idx].length; i++){
					if(document.form1["choice_q"+idx][i].checked){
						answerStr += document.form1["choice_q"+idx][i].value+",";
					}
				}
				console.log(answerStr);
				if(answerStr.length>0){
					answerStr = answerStr.substr(0,answerStr.length-1);
				}
				$("#choice_answer_q"+idx).text(answerStr);
			}else{
				$("#choice_answer_q"+idx).text(document.form1["choice_q"+idx].value);
			}
		}
		
		function changeSwitch(idx){
			if($("#check_judge_q"+idx).prop("checked")==true){
				$("#judge_q"+idx).val("T");
				$("#judge_answer_q"+idx).html("<i class='fas fa-check fa-2x'></i>");
			}else{
				$("#judge_q"+idx).val("F");
				$("#judge_answer_q"+idx).html("<i class='fas fa-times fa-2x'></i>");
			}
		}
		
		function setSwitch(idx){
			if($("#judge_q"+idx).val()=="T"){
				$("#judge_answer_q"+idx).html("<i class='material-icons right small'>done</i>");
				$("#check_judge_q"+idx).prop("checked",true);
			}else if($("#judge_q"+idx).val()=="F"){
				$("#judge_answer_q"+idx).html("<i class='right'><img src='images/wrong.png' width='20'/></i>");
				$("#check_judge_q"+idx).prop("checked",false);
			}
		}
		
		function toggleShowTime(){
			$("#timeNotice").toggle();
			if ( $("#timeNotice").is(":hidden") ){
				$("#toggleTimeBtn").text("显示时间提示");
			}else{
				$("#toggleTimeBtn").text("关闭时间提示");
			}
		}
		
		var seconds = form1.remainingTime.value;//初始值
		function updateTime(){
			var s = seconds % 60; // 秒
			var m = (seconds - s) / 60 % 60; // 分钟
			var h =  ((seconds - s) / 60 - m ) / 60 % 24; // 小时
			$("#timeNotice").html( "离考试结束还有：" + h + "小时" + m + "分钟" + s + "秒");
			seconds--;
		}
		
		setInterval(updateTime, 1000);
	</script>
	<%@ include file="include/footer.jsp" %>
</body>
</html>