<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷详细</title>
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
		width: 80%;
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
	<div id="main">
	<h2>选择题</h2>
	<table class="mytable">
	<s:iterator value="choiceList" status="st" var="item">
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
	<s:iterator value="blankFillingList" status="st" var="item">
		<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
			<td><s:property value="#st.index+1"/>. </td>
			<td style="text-align:left;"><s:property value="content"/></td>
		</tr>
		<tr><td colspan="2" style="height:20px"></td></tr>
	</s:iterator>
	</table>
	
	<h2>判断题</h2>
	<table class="mytable">
	<s:iterator value="judgeList" status="st" var="item">
		<tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
			<td><s:property value="#st.index+1"/>. </td>
			<td style="text-align:left;"><s:property value="content"/></td>
		</tr>
		<tr><td colspan="2" style="height:20px"></td></tr>
	</s:iterator>
	</table>
	</div>
	<s:debug></s:debug>
</body>
</html>