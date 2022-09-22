<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java考试系统--试题类型</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/material_icons.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body {
            font-family: Roboto, "Microsoft YaHei";
        }
        .my-opacity{
            opacity:0.6;
        }
        .my-opacity:hover{
            opacity:1;
        }
        a{
            color: #2e7d32;
        }
    </style>
</head>
<body>
<%@ include file="include/header.jsp" %>

<div id="main">
    <div class="container" style="padding: 20px;">
        <div class="divider" style="height: 20px; background: #fff;"></div>
        <div class="row">

            <div class="col s12 m4">
                <div class="card">
                    <div class="card-image waves-effect waves-block waves-light my-opacity">
                        <a href="<s:url action="searchchoice"/>"><img class="activator" src="images/choicequestion1.jpg"></a>
                    </div>
                    <div class="card-content">
                        <a href="<s:url action="searchchoice"/>">
							<span class="card-title activator light-green-text text-darken-4">选择题
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
                        <p><a href="<s:url action="searchchoice"/>">列出和管理选择题</a></p>
                    </div>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="card">
                    <div class="card-image waves-effect waves-block waves-light my-opacity">
                        <a href="<s:url action="searchblank"/>"><img class="activator" src="images/blankquestion1.jpg"></a>
                    </div>
                    <div class="card-content">
                        <a href="<s:url action="searchblank"/>">
							<span class="card-title activator light-green-text text-darken-4">填空题
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
                        <p><a href="<s:url action="searchblank"/>">列出和搜索填空题</a></p>
                    </div>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="card">
                    <div class="card-image waves-effect waves-block waves-light my-opacity">
                        <a href="<s:url action="searchjudge"/>"><img class="activator" src="images/judgequestion1.jpg"></a>
                    </div>
                    <div class="card-content">
                        <a href="<s:url action="searchjudge"/>">
							<span class="card-title activator light-green-text text-darken-4">判断题
							<i class="fas fa-sign-in-alt fa-lg right light-green-text"></i></span></a>
                        <p><a href="<s:url action="searchjudge"/>">列出和搜索判断题</a></p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="include/footer.jsp"%>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<script>
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
    })

</script>
</body>
</html>