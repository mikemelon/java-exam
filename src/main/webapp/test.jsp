<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
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