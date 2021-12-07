<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java考试系统--数据导入</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
    body {
        font-family: Roboto, "Microsoft YaHei";
        font-size: large;
    }
    .mytable{
    	width: 90%;
    	margin: 0 auto;
    }
    .comment{
    	font-size: 16px;
    }
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div class="container">
	<form class="col s12" name="form1" method="post" action="importchoice" enctype="multipart/form-data">
	<h4>将指定格式文件导入对应题型的题库</h4>
	<hr>
	<table class="mytable">
		<tr>
			<td style="width: 210px;max-width:210px;"><span class="purple-text text-darken-2 comment">选择题：（<a href="other/选择题import.txt" download="选择题import.txt">导入格式示例</a>）</span></td>
			<td>
			    <div class="file-field input-field">
                    <div class="btn">
                        <span>上传文件</span>
                        <input type="file" name="choiceImportFile">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" size="30" name="choiceFilePath">
                    </div>
                </div>
			</td>
			<td>			
				<button class="teal darken-4 waves-effect waves-teal btn-flat" type="button"
				 onclick="importChoiceBegin()">
				<span class="yellow-text text-lighten-1">导入选择题
        		<i class="fas fa-arrow-right fa-lg right"></i></span>
    		</button>
    		</td>
		</tr>
		<tr>
			<td style="width: 210px;max-width:210px;"><span class="purple-text text-darken-2 comment">填空题：（<a href="other/填空题import.txt" download="填空题import.txt">导入格式示例</a>）</span></td>
			<td>
			    <div class="file-field input-field">
                    <div class="btn">
                        <span>上传文件</span>
                        <input type="file" name="blankImportFile">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" size="30" name="blankFilePath">
                    </div>
                </div>
			</td>
			<td>			
				<button class="teal darken-4 waves-effect waves-teal btn-flat" type="button"
				 onclick="importBlankBegin()">
				<span class="yellow-text text-lighten-1">导入填空题
        		<i class="fas fa-arrow-right fa-lg right"></i></span>
    		</button>
    		</td>
		</tr>
		<tr>
			<td style="width: 210px;max-width:210px;"><span class="purple-text text-darken-2 comment">判断题：（<a href="other/判断题import.txt" download="判断题import.txt">导入格式示例</a>）</span></td>
			<td>
			    <div class="file-field input-field">
                    <div class="btn">
                        <span>上传文件</span>
                        <input type="file" name="judgeImportFile">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" size="30"  name="judgeFilePath">
                    </div>
                </div>
			</td>
			<td>			
				<button class="teal darken-4 waves-effect waves-teal btn-flat" type="button"
				 onclick="importJudgeBegin()">
				<span class="yellow-text text-lighten-1">导入判断题
        		<i class="fas fa-arrow-right fa-lg right"></i></span>
    		</button>
    		</td>
		</tr>
	</table>
	</form>
	<h4>将指定格式文件导入学生信息</h4>
	<hr>
	<form class="col s12" name="form2" method="post" action="importstudent" enctype="multipart/form-data">
	<table class="mytable">
		<tr>
			<td style="width: 210px;max-width:210px;"><span class="purple-text text-darken-2 comment">学生信息文件：（<a href="other/学生import.txt" download="学生import.txt">导入格式示例</a>）</span></td>
			<td>
			    <div class="file-field input-field">
                    <div class="btn">
                        <span>上传文件</span>
                        <input type="file" name="studentImportFile">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" size="30"  name="studentFilePath">
                    </div>
                </div>
			</td>
			<td>			
				<button class="teal darken-4 waves-effect waves-teal btn-flat" type="button"
				 onclick="importStudentBegin()">
				<span class="yellow-text text-lighten-1">导入学生信息
        		<i class="fas fa-arrow-right fa-lg right"></i></span>
    		</button>
    		</td>
		</tr>
	</table>
	</form>
	</div>
	<%@ include file="include/footer.jsp" %>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	
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
	
	<script>
	function importChoiceBegin(){
		if(form1.choiceFilePath.value==''){
			alert('请先上传指定格式的选择题导入文件');
			form1.choiceImportFile.click();
			return;
		}
		form1.action='importchoice';
		form1.submit();
	}
	
	function importBlankBegin(){
		if(form1.blankFilePath.value==''){
			alert('请先上传指定格式的填空题导入文件');
			form1.blankImportFile.click();
			return;
		}
		form1.action='importblank';
		form1.submit();
	}
	
	function importJudgeBegin(){
		if(form1.judgeFilePath.value==''){
			alert('请先上传指定格式的判断题导入文件');
			form1.judgeImportFile.click();
			return;
		}
		form1.action='importjudge';
		form1.submit();
	}
	
	function importStudentBegin(){
		if(form2.studentFilePath.value==''){
			alert('请先上传指定格式的学生信息导入文件');
			form2.studentImportFile.click();
			return;
		}
		form2.action='importstudent';
		form2.submit();		
	}
	</script>
	
</body>
</html>