<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--题目分析饼状图</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="css/material_icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	font-family: Roboto, "Microsoft YaHei";
}

.my-opacity {
	opacity: 0.6;
}

.my-opacity:hover {
	opacity: 1;
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<div id="main">
		<div class="container" style="padding: 10px;">
			<div class="row">
				<div class="col s12">
					<ul class="tabs tabs-fixed-width">
						<li class="tab"><a href="#choiceTab">选择题</a></li>
						<li class="tab"><a href="#blankTab">填空题</a></li>
						<li class="tab"><a href="#judgeTab">判断题</a></li>
					</ul>
				</div>
				<div id="choiceTab" class="col s12">
					<!-- 选择题 选项卡 -->
					<form name="choicesearchform" method="post" action="choicesearchlist1">
					<input type="hidden" name="answerSearch2">
						<div class="row">
			                   <div class="input-field col l3 m4 s12">
			                       <i class="material-icons prefix small">info_outline</i>
			                       <input type="text" placeholder="输入题干包含的内容" id="contentSearch" name="contentSearchChoice" 
			                       value="<s:property value="contentSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="contentSearch">题干内容</label>
			                   </div>
			                   <div class="input-field col l3 m4 s12">
			                       <i class="tiny material-icons prefix ">toc</i>
			                       <input type="text" placeholder="输入选项包含的内容" id="choiceSearch" name="choiceSearchChoice" 
			                       value="<s:property value="choiceSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="choiceSearch">选项内容</label>
			                   </div>
			                   <div class="input-field col l2 m4 s12">
			                       <i class="material-icons prefix small">spellcheck</i>
			                       <select multiple name="answerSearchChoice" id="answerSearchChoice">
							       		<option value="" disabled selected>选择</option>
							       		<option value="A" <s:if test="answerSearch.contains(\"A\")">selected</s:if>>A</option>
							       		<option value="B" <s:if test="answerSearch.contains(\"B\")">selected</s:if>>B</option>
							       		<option value="C" <s:if test="answerSearch.contains(\"C\")">selected</s:if>>C</option>
							       		<option value="D" <s:if test="answerSearch.contains(\"D\")">selected</s:if>>D</option>
							       </select>
			                       <label for="answerSearch">答案</label>
			                   </div>
			                   <div class="input-field col l2 m4 s12">
			                       <i class="material-icons prefix small">description</i>
			                       <input type="text" placeholder="输入知识点" id="knowledgeSearch" name="knowledgeSearchChoice" 
			                       value="<s:property value="knowledgeSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="password">知识点</label>
			                   </div>
			                   <div class="input-field col l2 m4 s12 vertical-align"><!-- change to ajaxSearch for dialog  -->
								<button class="red darken-4 waves-effect waves-teal btn-flat" type="button" onclick="ajaxSearchChoice()">
									<span class="yellow-text text-lighten-1">搜索
					        		<i class="material-icons right">search</i></span>
					    		</button>
					        </div>
			               </div>
					</form>
					<table class="mytable">
						<thead>
							<tr>
								<th width="50px">序号</th>
								<th width="50px">名称</th>
								<th>题干</th>
								<!-- <th>选项</th> -->
								<th width="80px">答案</th>
								<th>知识点</th>
							</tr>
						</thead>
						<tbody id="choiceListBody">
						<s:iterator value="#session.QUESTION_STATS_CHOICELIST" status="st"
							var="item">
							<tr
								style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
								<td><s:property value="#st.index+1" />.</td>
								<td style="text-align: left;"><s:property value="content" /></td>
							</tr>
							<!-- tr style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
							<td colspan="2">
								<table>
									<tr><td>A. </td><td><s:property value="choiceA"/></td></tr>
									<tr><td>B. </td><td><s:property value="choiceB"/></td></tr>
									<tr><td>C. </td><td><s:property value="choiceC"/></td></tr>
									<tr><td>D. </td><td><s:property value="choiceD"/></td></tr>
								</table>
							</td>
						</tr-->
							<tr>
								<td colspan="2" style="height: 20px;"></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</div>
				<div id="blankTab" class="col s12">
					<!-- 填空题 选项卡 -->
					<form name="blanksearchform" method="post" action="blanksearchlist1">
						<div class="row">
			                   <div class="input-field col l3 m4 s12">
			                       <i class="material-icons prefix small">info_outline</i>
			                       <input type="text" placeholder="输入题干包含的内容" id="contentSearchBlank" name="contentSearchBlank" 
			                       value="<s:property value="contentSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="contentSearch">题干内容</label>
			                   </div>
			                   <div class="input-field col l3 m4 s12">
			                       <i class="tiny material-icons prefix ">toc</i>
			                       <input type="text" placeholder="输入答案中包含的内容" id="answerSearchBlank" name="answerSearchBlank" 
			                       value="<s:property value="answerSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="answerSearch">答案内容</label>
			                   </div>
			                   <div class="input-field col l3 m4 s12">
			                       <i class="material-icons prefix small">description</i>
			                       <input type="text" placeholder="输入知识点" id="knowledgeSearchBlank" name="knowledgeSearchBlank" 
			                       value="<s:property value="knowledgeSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="password">知识点</label>
			                   </div>
			                   <div class="input-field col l3 m4 s12 vertical-align"><!-- change to ajaxSearch for dialog  -->
								<button class="red darken-4 waves-effect waves-teal btn-flat" type="button" onclick="ajaxSearchBlank()">
									<span class="yellow-text text-lighten-1">搜索
					        		<i class="material-icons right">search</i></span>
					    		</button>
					        </div>
			               </div>
					</form>
					<table class="mytable">
						<thead>
							<tr>
								<th width="50px">序号</th>
								<th width="50px">名称</th>
								<th>题干</th>
								<th width="80px">答案</th>
								<th>知识点</th>
							</tr>
						</thead>
						<tbody id="blankListBody">
						<s:iterator value="#session.QUESTION_STATS_BLANKLIST" status="st"
							var="item">
							<tr
								style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
								<td><s:property value="#st.index+1" />.</td>
								<td style="text-align: left;"><s:property value="content" /></td>
							</tr>
							<tr>
								<td colspan="2" style="height: 20px"></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</div>
				<div id="judgeTab" class="col s12">
					<!-- 判断题 选项卡 -->
					<form name="judgesearchform" method="post" action="judgesearchlist1">
					<input type="hidden" name="answerSearch2">
						<div class="row">
			                   <div class="input-field col l3 m4 s12">
			                       <i class="material-icons prefix small">info_outline</i>
			                       <input type="text" placeholder="输入题干包含的内容" id="contentSearchJudge" name="contentSearchJudge" 
			                       value="<s:property value="contentSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="contentSearch">题干内容</label>
			                   </div>
			                   <div class="col l3 m4 s12  valign-wrapper" style="height:60px;">
			                   	<span class="valign" style="display:inline-block;margin:0 20px;"><label>答案:</label></span>
			                       <!-- i class="tiny material-icons prefix ">toc</i-->
			                       <input type="radio" class="with-gap valign" id="answerSearchT" name="answerSearchJudge" value="T">
			                       <label for="answerSearchT"><i class='material-icons small'>done</i></label>
			                       <input type="radio" class="with-gap valign"  id="answerSearchF" name="answerSearchJudge" value="F">
			                       <label for="answerSearchF"><i><img src='images/wrong.png' width='20'/></i></label>
			                   </div>
			                   <div class="input-field col l3 m4 s12">
			                       <i class="material-icons prefix small">description</i>
			                       <input type="text" placeholder="输入知识点" id="knowledgeSearch" name="knowledgeSearchJudge" 
			                       value="<s:property value="knowledgeSearch"/>" 
			                       class="validate" style="font-size:large">
			                       <label for="password">知识点</label>
			                   </div>
			                   <div class="input-field col l3 m4 s12 vertical-align">
								<button class="red darken-4 waves-effect waves-teal btn-flat" type="button" onclick="ajaxSearchJudge()">
									<span class="yellow-text text-lighten-1">搜索
					        		<i class="material-icons right">search</i></span>
					    		</button>
					        </div>
			               </div>
					</form>
					<table class="mytable">
						<thead>
							<tr>
								<th width="50px">序号</th>
								<th width="50px">名称</th>
								<th>题干</th>
								<th width="80px">答案</th>
								<th>知识点</th>
							</tr>
						</thead>
						<tbody id="judgeListBody">
						<s:iterator value="#session.QUESTION_STATS_JUDGELIST" status="st"
							var="item">
							<tr
								style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
								<td><s:property value="#st.index+1" />.</td>
								<td style="text-align: left;"><s:property value="content" /></td>
							</tr>
							<tr>
								<td colspan="2" style="height: 20px"></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>

	<%@ include file="include/footer.jsp"%>
	
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
    
    <!-- 饼图对话框 -->
	<div id="chartdialog" class="modal" style="height: 750px; width:950px;">
        <div class="modal-content" style="text-align:center;padding 0 auto;">
            <img id="statsImg" src="charts/questionpiechart.action">
        </div>
    </div>
    
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	<script>
		$(document).ready(function() {
			$('select').material_select();
			$('.modal-trigger').leanModal({
				dismissible : true, //是否点模态对话框外面就可以关闭
				opacity : 0.6, //接近1，不透明
			});//使用模态对话框，必须有这句

			$(".button-collapse").sideNav({
				menuWidth : 200, // Default is 240
				edge : 'left', // Choose the horizontal origin
				closeOnClick : true
			// Closes side-nav on <a> clicks, useful for Angular/Meteor
			});

			$(".collapsible").collapsible({
				accordion : true
			});
		});
		
		function ajaxSearchChoice(){
		var answerArray=$('#answerSearchChoice').val()+"";
		var data1=	{"contentSearch":document.forms['choicesearchform'].contentSearchChoice.value,
				"choiceSearch":document.forms['choicesearchform'].choiceSearchChoice.value,
				"answerSearch":answerArray,
				"answerSearch2":answerArray,
				"knowledgeSearch":document.forms['choicesearchform'].knowledgeSearchChoice.value};
		$.post("choicesearchlist1",
				data1,
				function(data,status){
					var showHtml="";
					for(var i=0; i<data.length; i++){
						showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td>"+data[i].id+"</td>";
						showHtml+="<td>"+(!data[i].name?'':data[i].name)+"</td>";
						showHtml+="<td><a href='#chartdialog' class='modal-trigger' onclick='changeStatsChart(0,"+data[i].id+")'>"+(!data[i].content?'':data[i].content)+"</a></td>";
						showHtml+="<td>"+(!data[i].answer?'':data[i].answer)+"</td>";
						showHtml+="<td>"+(!data[i].knowledgePoint?'':data[i].knowledgePoint)+"</td></tr>";
					}
					$('#choiceListBody').html(showHtml);
				},"json");
		}
		
		function ajaxSearchBlank(){
		$.post("blanksearchlist1",
				{"contentSearch":document.forms['blanksearchform'].contentSearchBlank.value,
				"answerSearch":document.forms['blanksearchform'].answerSearchBlank.value,
				"knowledgeSearch":document.forms['blanksearchform'].knowledgeSearchBlank.value},
				function(data,status){
					var showHtml="";
					for(var i=0; i<data.length; i++){
						showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td>"+data[i].id+"</td>";
						showHtml+="<td>"+(!data[i].name?'':data[i].name)+"</td>";
						showHtml+="<td><a href='#chartdialog' class='modal-trigger' onclick='changeStatsChart(1,"+data[i].id+")'>"+(!data[i].content?'':data[i].content)+"</a></td>";
						showHtml+="<td>"+(!data[i].answer?'':data[i].answer)+"</td>";
						showHtml+="<td>"+(!data[i].knowledgePoint?'':data[i].knowledgePoint)+"</td></tr>";
					}
					$('#blankListBody').html(showHtml);
				});
		}
		
		function ajaxSearchJudge(){
			$.post("judgesearchlist1",
					{contentSearch:document.forms['judgesearchform'].contentSearchJudge.value,
					answerSearch:document.forms['judgesearchform'].answerSearchJudge.value,
					knowledgeSearch:document.forms['judgesearchform'].knowledgeSearchJudge.value},
					function(data,status){
						var showHtml="";
						for(var i=0; i<data.length; i++){
							showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td>"+data[i].id+"</td>";
							showHtml+="<td>"+(!data[i].name?'':data[i].name)+"</td>";
							showHtml+="<td><a href='#chartdialog' class='modal-trigger' onclick='changeStatsChart(2,"+data[i].id+")'>"+(!data[i].content?'':data[i].content)+"</a></td>";
							showHtml+="<td>"+(!data[i].answer?'':data[i].answer)+"</td>";
							showHtml+="<td>"+(!data[i].knowledgePoint?'':data[i].knowledgePoint)+"</td></tr>";
						}
						$('#judgeListBody').html(showHtml);
					});
		}
		
		function changeStatsChart(type, bq_id){ //type=0,1,2分别代表选择、填空、判断， bq_id是对应的题库表中的id
			$("#statsImg").attr("src","charts/questionpiechart?type="+type
					+"&bq_id="+bq_id);
			//console.log($("#statsImg").attr("src"));
			$('#chartdialog').openModal();
		}
	</script>
</body>
</html>