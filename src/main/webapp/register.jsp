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
        <div class="layui-container">
            <form class="layui-form" action="register" method="post" name="register_form">
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学号</label>
                    <div class="layui-input-block">
                        <input type="text" required lay-verify="required" placeholder="请输入学号" autocomplete="off"
                               class="layui-input"
                               name="RegisterNo">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-block">
                        <input type="password" required lay-verify="required" placeholder="请输入学号" autocomplete="off"
                               class="layui-input" name="Password">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="true" title="男">
                        <input type="radio" name="sex" value="false" title="女" checked>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-block">
                        <select name="grade" lay-filter="grade">
                            <option value="0">一班</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色</label>
                    <div class="layui-input-block">
                        <select name="role" lay-filter="role">
                            <option value="0">学生</option>
                            <option value="1">教师</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
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