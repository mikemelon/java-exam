<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java考试系统--课程列表</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="css/fontawesome-all.min.css">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="css/material_icons.css">
    <link type="text/css" rel="stylesheet" href="css/layui.css">
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
</head>
<body>
<%@ include file="include/header.jsp" %>
<form action="delCourse" style="display: none" name="delUser" method="post">
    <input type="hidden" name="courseId">
</form>
<div id="main">
    <div class="container" style="margin-top: 20px;">
        <form name="form1" method="post" action="">
            <div class="row">
                <div class="input-field col l2 m3 s12">
                    <i class="far fa-file fa-lg prefix"></i>
                    <input type="text" placeholder="输入名称" id="CourseSearch" name="CourseSearch"
                           class="validate" style="font-size:large">
                    <label for="CourseSearch">名称</label>
                </div>
                <div class="input-field col l3 m3 s12 vertical-align">
                    <button class="red darken-4 waves-effect waves-teal btn-flat" type="submit">
							<span class="yellow-text text-lighten-1">搜索
			        		<i class="fas fa-search fa-lg right"></i></span>
                    </button>
                </div>
            </div>
        </form>
        <button class="layui-btn layui-btn-normal" onclick="window.location = 'createCourse.jsp'">新增</button>
        <table class="mytable">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名</th>
            </tr>
            </thead>

            <s:iterator value="courses" status="st" var="item">
                <tr
                        style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
                    <td><s:property value="#st.index+pageIndex*courses.size+1" /></td>
                    <td><s:property value="name"/></td>
                    <td>
                        <button class="layui-btn layui-btn-danger" onclick="deleteUser(this)" value=<s:property value="id"/>>删除</button>
                        <button class="layui-btn" onclick="update(this)" value=<s:property value="id"/>>修改</button>
                    </td>
                </tr>
            </s:iterator>
        </table>

        <!-- 分页控件 -->
        <form name="form2" method="post">
            <input type="hidden" name="pageIndex" value="<s:property value="pageIndex"/>" />
            <s:if test="totalPage gt 1">
                <ul class="pagination">
                    <li <s:if test="pageIndex lt 1">class="disabled"</s:if><s:else>class="waves-effect"</s:else>>
                        <a href="#!" <s:if test="pageIndex gt 0">onclick="selectPage(<s:property value="pageIndex"/>)"</s:if>>
                            <i class="fas fa-chevron-left fa-xs"></i></a>
                    </li>
                    <s:iterator var="item" begin="1" end="totalPage">
                        <s:if test="pageIndex==top-1"><li class="active"><a href="#!"><s:property/></a></li></s:if>
                        <s:else><li class="waves-effect"><a href="#!" onclick="selectPage(<s:property/>)"><s:property/></a></li></s:else>
                    </s:iterator>
                    <li <s:if test="pageIndex gt (totalPage-2)">class="disabled"</s:if><s:else>class="waves-effect"</s:else>>
                        <a href="#!" <s:if test="pageIndex lt (totalPage-1)">onclick="selectPage(<s:property value="pageIndex+2"/>)"</s:if>>
                            <i class="fas fa-chevron-right fa-xs"></i></a>
                    </li>
                </ul>
            </s:if>
        </form>
    </div>
</div>
<%@ include file="include/footer.jsp" %>

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<script>
    function selectPage(page){
        form2.pageIndex.value=page-1;
        form2.action="courseList";
        form2.submit();
    }

    function deleteUser(elem){
        $(elem).parent().parent().remove()
        delUser.courseId.value = elem.value;
        delUser.submit()
    }

    function update(elem){
        delUser.action = "updateCoursePage"
        delUser.courseId.value = elem.value
        delUser.submit()

    }
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