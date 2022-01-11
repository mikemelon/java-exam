<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Java考试系统--用户列表</title>
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
<form action="delStudent" style="display: none" name="delUser" method="post">
  <input type="hidden" name="studentId">
</form>
<div id="main">
  <div class="container" style="margin-top: 20px;">
    <table class="mytable">
      <thead>
      <tr>
        <th width="50px">序号</th>
        <th width="50px">姓名</th>
        <th>学号</th>
        <th width="80px">性别</th>
        <th>角色</th>
        <th>班级</th>
      </tr>
      </thead>
      <s:iterator value="studentList" status="st" var="item">
        <tr
                style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
          <td><s:property value="#st.index+pageIndex*studentList.size+1" /></td>
          <td><s:property value="name"/></td>
          <td><s:property value="registerNo"/></td>
          <td><s:if test="#item.gender">男</s:if><s:else>女</s:else></td>
          <td><s:if test="#item.role == 0">学生</s:if><s:else>教师</s:else></td>
          <td><s:property value="grade.name"/> </td>
          <td><button class="layui-btn layui-btn-danger" onclick="deleteUser(this)" value=<s:property value="id"/>>删除</button></td>
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
    form2.action="manageuser";
    form2.submit();
  }

  function deleteUser(elem){
    $(elem).parent().parent().remove()
    delUser.studentId.value = elem.value;
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