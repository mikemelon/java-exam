<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<header>
    <div class="navbar-fixed">
        <nav class="light-green darken-1">
            <div class="container">
                <div class="nav-wrapper">
                    <a href="main.jsp" class="brand-logo">Java考试网站</a>
                    <ul id="nav-mobile4" class="right hide-on-med-and-down">

                        <s:if test="#session['USER_INFO']==null">
                            <li>
                            <li><a href="#register_elem" class="waves-effect waves-light btn modal-trigger">注册</a></li>


                            </li>
                            <li><a href="#logindialog" class="waves-effect waves-light btn modal-trigger">登录</a></li>
                        </s:if>
                        <s:else>
                            <li>
                                <div class="chip">
                                    <a href="#" class="dropdown-button" style="color:#d50000;"
                                       data-activates="user_dropdown" data-induration="3000"
                                       data-beloworigin="true" data-hover="true">
                                        <img src="images/soccer4.jpg" alt="大黄鸭">
                                        <s:property value="#session['USER_INFO'].name"/>
                                    </a>
                                </div>
                                <ul class="dropdown-content yellow lighten-5" id="user_dropdown">
                                    <li><a href="userChangePassword.jsp" class="indigo-text text-darken-2">修改密码</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#" class="indigo-text text-darken-2"
                                           onclick="loginform.action='logout';loginform.submit();">退出登陆</a></li>
                                </ul>
                            </li>
                            <script>
                                //登录成功后，开启与服务器的WebSocket连接，等待接收推送消息
                                var ws = new WebSocket("ws://" + window.location.host + "<%=request.getContextPath()%>/websocket/broadcast");
                                ws.onopen = function () {
                                    //console.log("websocket open");
                                }
                                ws.onmessage = function (evt) {
                                    //console.log("websocket getmessage:"+evt.data);
                                    var $toastContent = $('<span class="yellow-text lighten-5"><h4>' + evt.data + '</h4></span>');
                                    Materialize.toast($toastContent, 8000, 'rounded');
                                }
                            </script>
                        </s:else>
                        <li class="active"><a href="#">考试</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>

<!-- 用户登陆对话框 -->
<div id="logindialog" class="modal modal-fixed-footer" style="height: 360px;">
    <div class="modal-content">
        <h4>输入登录信息</h4>
        <form name="loginform" method="post" action="login" class="col s12" style="margin-top: 40px;">
            <div class="row">
                <div class="input-field col s11" style="margin: 0 auto;">
                    <!-- i class="material-icons prefix">account_circle</i-->
                    <i class="fas fa-user fa-3x prefix"></i>
                    <input type="text" placeholder="输入学号" id="registerNo" name="registerNo" class="validate"
                           style="font-size:large" autocomplete="off">
                    <label for="registerNo">学号</label>
                </div>
                <div class="input-field col s11">
                    <!-- i class="material-icons prefix">phone</i-->
                    <i class="fas fa-key fa-3x prefix"></i>
                    <input type="password" placeholder="输入密码" id="password" name="password" class="validate"
                           style="font-size:large" autocomplete="off">
                    <label for="password">密码</label>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" onclick="loginform.submit();"
           class="waves-effect waves-light btn btn-flat modal-action modal-close">确定</a>
    </div>

</div>
<div id="register_elem" class="modal modal-fixed-footer" style="height: 80%; width: 81%">
    <div class="modal-content">
        <h4>输入注册信息</h4>
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
    </div>
</div>