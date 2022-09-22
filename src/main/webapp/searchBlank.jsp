<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java考试系统--搜索填空题</title>
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
<div id="main">
    <div class="container" style="margin-top: 20px;">
        <form name="form1" method="post" action="blanksearchlist">
            <div class="row">
                <div class="input-field col l3 m3 s12">
                    <i class="far fa-comment fa-lg  prefix"></i>
                    <input type="text" placeholder="输入题干包含的内容" id="contentSearch" name="contentSearch"
                           value="<s:property value="contentSearch"/>"
                           class="validate" style="font-size:large">
                    <label for="contentSearch">题干内容</label>
                </div>
                <div class="input-field col l3 m3 s12">
                    <i class="far fa-question-circle fa-lg prefix"></i>
                    <input type="text" placeholder="输入答案中包含的内容" id="answerSearch" name="answerSearch"
                           value="<s:property value="answerSearch"/>"
                           class="validate" style="font-size:large">
                    <label for="answerSearch">答案内容</label>
                </div>
                <div class="input-field col l3 m3 s12">
                    <i class="far fa-file fa-lg prefix"></i>
                    <input type="text" placeholder="输入知识点" id="knowledgeSearch" name="knowledgeSearch"
                           value="<s:property value="knowledgeSearch"/>"
                           class="validate" style="font-size:large">
                    <label for="password">知识点</label>
                </div>
                <div class="input-field col l2 m3 s12">
                    <i class="far fa-file fa-lg prefix"></i>
                    <input type="text" placeholder="输入课程" id="CourseSearch" name="CourseSearch"
                           value="<s:property value="CourseSearch"/>"
                           class="validate" style="font-size:large">
                    <label for="CourseSearch">课程</label>
                </div>
                <div class="input-field col l3 m3 s12 vertical-align">
                    <button class="red darken-4 waves-effect waves-teal btn-flat" type="submit">
							<span class="yellow-text text-lighten-1">搜索
			        		<i class="fas fa-search fa-lg right"></i></span>
                    </button>
                </div>
            </div>

        <table class="mytable">
            <thead>
            <tr>
                <s:if test=" #session['USER_INFO'].role==1">
                    <th width="100px">
                        <input type="checkbox" name="selectAll" id="selectAll" onclick="selectAllQuestion()"><label
                            for="selectAll">序号</label>
                    </th>
                </s:if>
                <s:else>
                    <th width="50px">序号</th>
                </s:else>
                <th width="50px">名称</th>
                <th>题干</th>
                <th width="80px">答案</th>
                <th>知识点</th>
                <th>课程</th>

            </tr>
            </thead>
            <s:iterator value="questionList" status="st" var="item">
                <tr
                        style="background-color:
                        <s:if test="#st.odd">#efefef</s:if>
                            <s:else>#ffffff</s:else>">
                    <s:if test=" #session['USER_INFO'].role==1">
                        <td>
                            <input name="blankChecked" id="blankChecked<s:property value="id"/>" type="checkbox"
                                   value="<s:property value="id"/>"/>
                            <label for="blankChecked<s:property value="id"/>"><s:property value="#st.index+1"/></label>
                        </td>
                    </s:if>
                    <s:else>
                        <td><s:property value="#st.index+pageIndex*questionList.size+1"/></td>
                    </s:else>
                    <td><s:property value="name"/></td>
                    <td><a class="namelink"
                           href="<s:url action="blankdfillingdetail"><s:param name="qid" value="%{id}"></s:param></s:url>"><s:property
                            value="content"/></a></td>
                    <td>
                        <s:property value="answer"/>
                        <s:if test="answer2 != null"><br><s:property value="answer2"/></s:if>
                        <s:if test="answer3 != null"><br><s:property value="answer3"/></s:if>
                        <s:if test="answer4 != null"><br><s:property value="answer4"/></s:if>
                        <s:if test="answer5 != null"><br><s:property value="answer5"/></s:if>
                        <s:if test="answer6 != null"><br><s:property value="answer6"/></s:if>
                        <s:if test="answer7 != null"><br><s:property value="answer7"/></s:if>
                        <s:if test="answer8 != null"><br><s:property value="answer8"/></s:if>
                    </td>
                    <td><s:property value="knowledgePoint"/></td>
                    <td><s:property value="course.name"/></td>

                </tr>
            </s:iterator>
        </table>
        <s:if test=" #session['USER_INFO'].role==1">
            <button class="layui-btn layui-btn-danger" onclick="form1.action='deleteBlank';form1.submit();">删除中题目</button>
        </s:if>
        </form>
        <!-- 分页控件 -->
        <form name="form2" method="post">
            <input type="hidden" name="pageIndex" value="<s:property value="pageIndex"/>"/>
            <s:if test="totalPage gt 1">
                <ul class="pagination">
                    <li
                            <s:if test="pageIndex lt 1">class="disabled" </s:if>
                        <s:else>class="waves-effect"</s:else>>
                        <a href="#!"
                           <s:if test="pageIndex gt 0">onclick="selectPage(<s:property value="pageIndex"/>)"
                        </s:if>>
                            <i class="fas fa-chevron-left fa-xs"></i></a>
                    </li>
                    <s:iterator var="item" begin="1" end="totalPage">
                        <s:if test="pageIndex==top-1">
                            <li class="active"><a href="#!"><s:property/></a></li>
                        </s:if>
                        <s:else>
                            <li class="waves-effect"><a href="#!" onclick="selectPage(<s:property/>)"><s:property/></a>
                            </li>
                        </s:else>
                    </s:iterator>
                    <li
                            <s:if test="pageIndex gt (totalPage-2)">class="disabled" </s:if>
                        <s:else>class="waves-effect"</s:else>>
                        <a href="#!"
                           <s:if test="pageIndex lt (totalPage-1)">onclick="selectPage(<s:property
                                   value="pageIndex+2"/>)"
                        </s:if>>
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
    function selectPage(page) {
        form2.pageIndex.value = page - 1;
        form2.action = "searchblank";
        form2.submit();
    }
    function selectAllQuestion() {
        var theCheck = document.getElementById("selectAll");
        var allCheckBox = document.getElementsByName("blankChecked");
        for (var i = 0; i < allCheckBox.length; i++) {
            if (theCheck.checked == true) {
                allCheckBox[i].checked = true;
            } else {
                allCheckBox[i].checked = false;
            }
        }
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