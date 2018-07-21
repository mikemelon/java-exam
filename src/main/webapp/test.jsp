<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
<title>Insert title here</title>
</head>
<body>
	<i class="fas fa-file-export fa-lg light-green-text"></i>
	<form name="form1">
		<input type="text" name="answerSearch" id="answerSearch">
		<select name="answerSearch2" id="answerSearch2">
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="C">C</option>
		</select>
		<input type="button" onclick="ajaxSubmit()" value="OK">
	</form>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script>
		function ajaxSubmit(){
			$.post("json/simpletest.action",{answerSearch:form1.answerSearch.value, answerSearch2:$('#answerSearch2').val()},
					function(data,status){
						
			});
		}
	</script>
</body>
</html>