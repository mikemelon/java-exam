<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        form.on("submit(formDemo)", function (data) {
            $.post("register.jsp", data.field, function () {
                layer.open({
                    title: '注册成功'
                    , content: '点击确定跳转到登录'
                    , yes: function (index, layero) {
                        window.location = "/java_exam_war/main.jsp"
                    }
                });
            })

            return false
        })
    })

</script>

</body>
</html>