<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Java考试系统</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/layui.css">
    <link type="text/css" rel="stylesheet" href="css/material_icons.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body {
            font-family: Roboto, "Microsoft YaHei";
            font-size: large;
        }
    </style>
</head>
<body>
<%@ include file="include/header.jsp" %>
<div class="layui-container">

    <form class="layui-form" action="updateCourse" method="post" name="register_form">
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="hidden" name="courseId" value="<s:property value="courseId"/>">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入名称"
                       autocomplete="off"
                       class="layui-input" value="<s:property value="name"/>">

            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">确定</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<%@ include file="include/footer.jsp" %>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="layui.js"></script>

<s:if test="hasActionMessages()">
    <script type="text/javascript">
        toast2();

        function toast2() {
            var msgStr = '<s:property value="actionMessages[0]"/>';
            var $toastContent = $('<span class="teal-text lighten-5"><h4>' + msgStr + '</h4></span>');
            Materialize.toast($toastContent, 4000, 'rounded');
        }
    </script>
</s:if>

<script>
    layui.use('form')

</script>

</body>
</html>