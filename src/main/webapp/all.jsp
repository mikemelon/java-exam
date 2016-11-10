<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试！</title>
</head>
<body>
	<h1>${message}
	TODO:
	5，用户注册页面，用户信息查看页面，编辑
	
	7，各题型的搜索页面上，点击题目，弹出模态窗口，显示题目详细信息。
	
	8，学生进入考试后，按照评分策略初步判分，，，，--》最终判分？
	
	9，解决题目中包含图片的问题
	1）能支持导入zip格式的各题型
	2）页面能正常显示各题目中的图
	
	10，抽题组卷部分：添加对Exam本身进行编辑action，主要是问题的增减。
	
	11，分页显示，可先在题型搜索页面实现(搞定)
	
	12，在试题分析中，topN排行，
	1）搜ExamQuestion，查看正确率最高的5个问题，用JFreeChart柱状图。
	2）搜StudentExamScore，查看平均分最高的5个用户，柱状图
	</h1>
	<s:debug></s:debug>
</body>
</html>