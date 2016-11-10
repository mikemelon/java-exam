<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java考试系统--主页</title>
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
    </style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<div id="main">
	    <div class="container" style="padding: 20px;">
	    	<div class="divider" style="height:20px;background:#fff;"></div>
	        <div class="row">
		        <div class="col s12 m4">
		        	<a href="<s:url action="studentexamlist"/>">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity" >
			                    <img class="activator" src="images/exampaper2.jpg">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">试题列表<i class="material-icons right">more_vert</i></span>
			                    <p>列出所有已有的试题列表</p>
			                </div>
			            </div>
		            </a>
	        	</div>
	        	
		        <div class="col s12 m4">
		        	<a href="questionByKnowledge.jsp">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity">
			                    <img class="activator" src="images/categories2.jpg">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">章节练习<i class="material-icons right">more_vert</i></span>
			                    <p>按照章节知识点分类进行练习</p>
			                </div>
			            </div>
		            </a>
	        	</div>
	        	
	        	<div class="col s12 m4">
	        		<a href="questionTypes.jsp">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity">
			                    <img class="activator" src="images/questiontype2.jpg">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">题型练习<i class="material-icons right">more_vert</i></span>
			                    <p>按照选择题、填空题、判断题分别练习</p>
			                </div>
			            </div>
		            </a>
	        	</div>
	        	
		        <div class="col s12 m4">
		        	<a href="<s:url action="examcompose"/>">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity">
			                    <img class="activator" src="images/papergenerator2.jpg">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">抽题组卷<i class="material-icons right">more_vert</i></span>
			                    <p>从题库中根据策略抽题组卷</p>
			                </div>
			            </div>
		            </a>
	        	</div>

		        <div class="col s12 m4">
		        	<a href="importData.jsp">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity">
			                    <img class="activator" src="images/importexport2.jpg">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">导入导出<i class="material-icons right">more_vert</i></span>
			                    <p>用户、试题等数据的导入导出</p>
			                </div>
			            </div>
		            </a>
	        	</div>
	        	
		        <div class="col s12 m4">
		        	<a href="#">
			            <div class="card"><!--只要某个图片、按钮或链接有activator这个class，点击就显示card-reveal这个div-->
			                <div class="card-image waves-effect waves-block waves-light my-opacity">
			                    <img class="activator" src="images/datastats2.png">
			                </div>
			                <div class="card-content">
			                    <span class="card-title activator grey-text text-darken-4">试题分析<i class="material-icons right">more_vert</i></span>
			                    <p>按照题型、知识点等进行统计分析</p>
			                </div>
			            </div>
		            </a>
	        	</div>
	        	
	        </div>
	    </div>
	</div>
	
	<%@ include file="include/footer.jsp" %>
	
	<!-- 用户登陆对话框 -->
	<div id="logindialog" class="modal modal-fixed-footer" style="height: 360px;">
        <div class="modal-content">
            <h4>输入登录信息</h4>
            <form name="loginform1" method="post" action="login" class="col s12" style="margin-top: 40px;">
            	<div class="row">
                    <div class="input-field col s11" style="margin: 0 auto;">
                        <i class="material-icons prefix">account_circle</i>
                        <input type="text" placeholder="输入学号" id="registerNo" name="registerNo" class="validate" style="font-size:large">
                        <label for="registerNo">学号</label>
                    </div>
                    <div class="input-field col s11">
                        <i class="material-icons prefix">phone</i>
                        <input type="password" placeholder="输入密码" id="password" name="password" class="validate" style="font-size:large">
                        <label for="password">密码</label>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" onclick="loginform1.submit();"
            class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
        </div>
    </div>
    
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

<s:if test="hasActionErrors()">
<script type="text/javascript">
	toast1();
	function toast1(){
		var errorStr='<s:property value="actionErrors[0]"/>';
	    var $toastContent = $('<span class="red-text lighten-5"><h4>'+errorStr+'</h4></span>');
	    Materialize.toast($toastContent, 4000, 'rounded');
	}
</script>
</s:if>

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

<s:debug></s:debug>
</body>
</html>