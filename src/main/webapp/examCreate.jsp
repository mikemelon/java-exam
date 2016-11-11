<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java考试系统--抽题组卷--创建试卷</title>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
	#main{
		
	}
	h2{
		display: block;
		margin: 0 auto;
		text-align: center;
	}
	.mytable{
		border-collapse: collapse;
		border-top: 2px solid #ddd;
		border-bottom: 2px solid #ddd;
		margin: 20px auto;
		width: 100%;
	}
	.mytable tr{
		width: 80%;
	}
	.mytable td{
		padding: 5px 10px;
	}
	a{text-decoration: none;}
	a:LINK{color: #12f;}
	a:VISITED{color: #a2e;}
	a:HOVER{color: #17f;}
</style>
</head>
<body>
	<form name="form1" method="post">
	<div id="main">
		<div class="container">
			<div class="row">
				<div class="input-field col s12 center-align teal lighten-4">
	                <i class="material-icons prefix small">info_outline</i>
	                <input type="text" placeholder="输入本次考试的名称" id="examName" name="examName" 
	                value="<s:property value="#session.EXAM_CREATE_NAME"/>" 
	                class="validate" style="font-size:large">
	                <label for="examName">考试名称</label>
	            </div>
            </div>
            <div class="row">
	            <div class="input-field col s12 center-align teal lighten-4">
	                <i class="material-icons prefix small">info_outline</i>
	                <input type="text" placeholder="输入本次考试的一些描述信息" id="examDetail" name="examDetail" 
	                value="<s:property value="#session.EXAM_CREATE_DETAIL"/>" 
	                class="validate" style="font-size:large">
	                <label for="examDetail">考试描述</label>
	            </div>
            </div>
            <div class="row">
            	<a href="#searchchoicedialog" class="modal-trigger red darken-4 waves-effect waves-teal btn">
					<span class="yellow-text text-lighten-1">加入选择题
	        		<i class="material-icons right">search</i></span>
	    		</a>
            	<a href="#searchblankdialog" class="modal-trigger red darken-4 waves-effect waves-blue btn">
					<span class="yellow-text text-lighten-1">加入填空题
	        		<i class="material-icons right">search</i></span>
	    		</a>
            	<a href="#searchjudgedialog" class="modal-trigger red darken-4 waves-effect waves-green btn" >
					<span class="yellow-text text-lighten-1">加入判断题
	        		<i class="material-icons right">search</i></span>
	    		</a>
            </div>
            <div class="row">
            	<button class="red darken-4 waves-effect waves-teal btn-flat" 
            		type="button" onclick="form1.action='createexamquestions';form1.submit()">
					<span class="yellow-text text-lighten-1">确定创建考试
	        		<i class="material-icons right">search</i></span>
		    	</button>
            </div>
			<h2>选择题</h2>
			<table class="mytable">
			<s:iterator value="#session.EXAM_CREATE_CHOICELIST" status="st" var="item">
				<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1"/>. </td>
					<td style="text-align:left;"><s:property value="content"/></td>
				</tr>
				<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td colspan="2">
						<table>
							<tr><td>A. </td><td><s:property value="choiceA"/></td></tr>
							<tr><td>B. </td><td><s:property value="choiceB"/></td></tr>
							<tr><td>C. </td><td><s:property value="choiceC"/></td></tr>
							<tr><td>D. </td><td><s:property value="choiceD"/></td></tr>
						</table>
					</td>
				</tr>
				<tr><td colspan="2" style="height:20px;"></td></tr>
			</s:iterator>
			</table>
			
			<h2>填空题</h2>
			<table class="mytable">
			<s:iterator value="#session.EXAM_CREATE_BLANKLIST" status="st" var="item">
				<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1"/>. </td>
					<td style="text-align:left;"><s:property value="content"/></td>
				</tr>
				<tr><td colspan="2" style="height:20px"></td></tr>
			</s:iterator>
			</table>
			
			<h2>判断题</h2>
			<table class="mytable">
			<s:iterator value="#session.EXAM_CREATE_JUDGELIST" status="st" var="item">
				<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1"/>. </td>
					<td style="text-align:left;"><s:property value="content"/></td>
				</tr>
				<tr><td colspan="2" style="height:20px"></td></tr>
			</s:iterator>
			</table>
		</div>
	</div>
	</form>
	
	<!-- 选择题搜索对话框 -->
	<div id="searchchoicedialog" class="modal modal-fixed-footer" 
	style="width:90%; height:95%;">
        <div class="modal-content">
            <h4>从题库中加入选择题：</h4>
            <%@ include file="include/choiceSearch.jsp" %>
        </div>
        <div class="modal-footer">
            <a href="#" onclick="selectQuestionSubmit('choiceselectform');"
            class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
        </div>
    </div>
	<!--  填空题搜索对话框 -->
	<div id="searchblankdialog" class="modal modal-fixed-footer" 
	style="width:90%; height:95%;">
        <div class="modal-content">
            <h4>从题库中加入填空题：</h4>
            <%@ include file="include/blankSearch.jsp" %>
        </div>
        <div class="modal-footer">
            <a href="#" onclick="selectQuestionSubmit('blankselectform');"
            class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
        </div>
    </div>
	<!--  判断题搜索对话框 -->
	<div id="searchjudgedialog" class="modal modal-fixed-footer" 
	style="width:90%; height:95%;">
        <div class="modal-content">
            <h4>从题库中加入判断题：</h4>
            <%@ include file="include/judgeSearch.jsp" %>
        </div>
        <div class="modal-footer">
            <a href="#" onclick="selectQuestionSubmit('judgeselectform');"
            class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
        </div>
    </div>
    
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script>
	function selectQuestionSubmit(formName){
		document.forms[formName].action='createexam';
		document.forms[formName].examName.value=form1.examName.value;
		document.forms[formName].examDetail.value=form1.examDetail.value;            
		document.forms[formName].submit();
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
	<s:debug></s:debug>
</body>
</html>