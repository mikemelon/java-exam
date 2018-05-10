<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--用户更改密码</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	font-family: Roboto, "Microsoft YaHei";
}

.mytable {
	border-collapse: collapse;
	border-bottom: 2px solid #ddd;
}

.mytable td {
	padding: 5px 10px;
}

.namelink a {
	text-decoration: none;
}

.namelink a:LINK {
	color: #12f;
}

.namelink a:VISITED {
	color: #a2e;
}

.namelink a:HOVER {
	color: #17f;
}
</style>
<s:head/>
</head>
<body>
	<header>
	    <div class="navbar-fixed">
	        <nav class="lime darken-1">
	            <div class="container">
	                <div class="nav-wrapper">
	                    <a href="main.jsp" class="brand-logo">Java教学网站</a>
	                    <ul id="nav-mobile4" class="right hide-on-med-and-down">
	                        <li>
								<div class="chip">
									<a href="#" class="dropdown-button" data-activates="user_dropdown" data-induration="3000"
       								data-beloworigin="true" data-hover="true">
							        <img src="images/soccer4.jpg" alt="大黄鸭">
							        	<s:property value="#session.USER_INFO.name"/>
							        </a>
							    </div>
							    <ul class="dropdown-content yellow lighten-5" id="user_dropdown">
							        <li><a href="#" class="indigo-text text-darken-2">修改密码</a></li>
							        <li><a href="#"class="indigo-text text-darken-2" >修改信息</a></li>
							        <li class="divider"></li>
							        <li><a href="#" class="indigo-text text-darken-2" 
							        onclick="loginform.action='logout';loginform.submit();">退出登陆</a></li>
							    </ul>
						    </li>
	                        <li><a href="#">签到</a></li>
	                        <li class="active"><a href="#">考试</a></li>
	                        <li><a href="#">作业</a></li>
	                        <li><a href="#">微课</a></li>
	                    </ul>
	                </div>
	            </div>
	        </nav>
	    </div>

	</header>
	
	<div id="main">
		<div class="container" style="height: 350px;">
			<div class="divider"  style="height:40px;background:#fff"></div>
			<form name="form1" method="post" action="changepassword">
            	<div class="row">
                    <div class="input-field col s12" style="margin: 0 auto;">
                        <i class="material-icons prefix">vpn_key</i>
                        <input type="text" placeholder="输入当前用户的旧密码" id="oldPassword" name="oldPassword" 
                        class="validate" style="font-size:large" value="<s:property value="oldPassword"/>" >
                        <label for="oldPassword">旧密码</label>
                        <s:fielderror fieldName="oldPassword"></s:fielderror>
                    </div>
                    <div class="input-field col s12" style="margin: 0 auto;">
                        <i class="material-icons prefix">vpn_key</i>
                        <input type="text" placeholder="请输入新密码" id="newPassword" name="newPassword" 
                        class="validate" style="font-size:large" value="<s:property value="newPassword"/>" >
                        <label for="newPassword">新密码</label>
                        <s:fielderror fieldName="newPassword"></s:fielderror>
                    </div>
                    <div class="input-field col s12" style="margin: 0 auto;">
                        <i class="material-icons prefix">vpn_key</i>
                        <input type="text" placeholder="请再输入一遍新密码，必须与上次相同" id="newPassword2" 
                        name="newPassword2" class="validate" style="font-size:large" value="<s:property value="newPassword2"/>" >
                        <label for="newPassword2">再输入一遍新密码</label>
                        <s:fielderror fieldName="newPassword2"></s:fielderror>
                    </div>
					<div class="divider" style="height: 10px; background-color: #fff;"></div>
					<div class="col s12">
						<button class="teal darken-4 waves-effect waves-teal btn-flat"
							type="submit">
							<span class="yellow-text text-lighten-1">确定 <i
								class="material-icons right">send</i></span>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<footer class="page-footer lime darken-1">
	    <div class="container">
	        <div class="row">
	            <div class="col l6 s12">
	                <h5 class="white-text">关于这个Java网站</h5>
	                <p class="grey-text text-lighten-4">希望能帮助你掌握好Java，也希望你为这个站点的成长能贡献力量。</p>
	            </div>
	            <div class="col l4 offset-l2 s12">
	                <h5 class="white-text">其他Java学习站点</h5>
	                <ul>
	                    <li><a class="grey-text text-lighten-3" href="#!">百度知道</a></li>
	                    <li><a class="grey-text text-lighten-3" href="#!">SegmentFault</a></li>
	                    <li><a class="grey-text text-lighten-3" href="#!">StackOverflow</a></li>
	                    <li><a class="grey-text text-lighten-3" href="#!">ItEye</a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="footer-copyright">
	        <div class="container">
	            © 2016 洛阳师范学院电子商务学院    &nbsp;作者: lyq
	            <a class="grey-text text-lighten-4 right" href="mailto:mikemelon@sina.com">跟我联系</a>
	        </div>
	    </div>
	</footer>
	<form name="loginform" method="post"></form>
	
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
</body>
</html>