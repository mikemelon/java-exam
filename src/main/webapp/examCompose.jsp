<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java考试系统--固定抽题组卷</title>
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
	td[id*="ScoreText"] a{
		display:none;
	}
	td[id*="ScoreText"]:hover a{
		display:inline-block;
	}
	td[id^="strategyName"] input,td[id^="strategyName"] a{
		display:none;
	}
	td[id^="strategyName"]:hover input,td[id^="strategyName"]:hover a{
		display:inline-block;
	}
	td[id^="strategyName"]:hover > span{
		display:none;
	}
</style>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<form name="form1" method="post">
	<input type="hidden" name="select_student_for" value="exam_compose">
	<div id="main">
		<div class="container" style="min-height:370px;">
			<table class="mytable">
				<thead>
					<tr>
						<th>序号</th>
						<th>考试名称</th>
						<th>说明</th>
						<th>考题分数分配</th>
					</tr>
				</thead>
				<s:iterator value="examList" status="st" var="item">
					<tr
						style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
						<td>
							<input type="radio" class="with-gap" name="examSelect" id="examSelect<s:property value="id" />" 
							value="<s:property value="id" />" <s:if test="id==examSelect">checked</s:if>  
							onclick=" getStrategyList(<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+id].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+id].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+id].JUDGE_LIST.size" />);">
							<label for="examSelect<s:property value="id" />" ><s:property value="#st.index+1" /></label>
						</td>
						<td><a class="namelink"
							href="<s:url action="examdetail"><s:param name="exam_id" value="%{id}"></s:param></s:url>"><s:property
									value="name" /></a></td>
						<td><s:property value="detail" /></td>
						<td>
							包含选择题
							<s:property value="#attr['EXAM_QUESTION_'+id].CHOICE_LIST.size" />道,
							填空题
							<s:property value="#attr['EXAM_QUESTION_'+id].BLANK_LIST.size" />道(共含
							<s:property value="#attr['EXAM_QUESTION_'+id].BLANK_CNT" />个空),
							判断题
							<s:property value="#attr['EXAM_QUESTION_'+id].JUDGE_LIST.size" />道。
							</td>
					</tr>
				</s:iterator>
			</table>
			<button class="blue darken-4 waves-effect waves-teal btn-flat" 
			type="button" name="action" style="margin-top:20px;"
			onclick="form1.action='createexam';form1.submit();">
				<span class="yellow-text text-lighten-1">创建新的考试
        		<i class="material-icons right">loop</i></span>
    		</button>
			<table id="strategyTable" class="mytable" <s:if test="strategyList==null && strategySelect==0">style="display:none"</s:if>>
				<thead>
					<tr>
						<th>序号</th>
						<th>分数策略名</th>
						<th>每道选择题分数</th>
						<th>填空题每个空分数</th>
						<th>每道判断题分数</th>
						<th>计算总分</th>
					</tr>
				</thead>
				<tbody id="strategyTableBody">
				<s:iterator value="strategyList" status="st" var="item">
					<tr
						style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
						<td>
							<input type='radio' class='with-gap' name='strategySelect' id='strategySelect<s:property value="id"/>'
							 value='<s:property value="id"/>' <s:if test="strategySelect==id">checked</s:if> >
							 <label for='strategySelect<s:property value="id"/>'><s:property value="#st.index+1" /></label>
						</td>
						<td id='strategyName<s:property value="id"/>'>
							<span><s:property value="name" /></span>
							<input type='text' style='width:100px;' name='strategyName<s:property value="id"/>' 
							value='<s:property value="name" />'>
							<a href=# class='btn btn-small' onclick='changeStrategyName(<s:property value="id"/>)'>更新</a>
						</td>
						<td id='choiceScoreText<s:property value="id"/>'>
							<s:property value="choicePerScore"/>
							<a href=# onclick='changeChoicePerScore(true, <s:property value="choicePerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeChoicePerScore(false,<s:property value="choicePerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id='blankScoreText<s:property value="id"/>'>
							<s:property value="blankPerScore"/>
							<a href=# onclick='changeBlankPerScore(true, <s:property value="blankPerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeBlankPerScore(false,<s:property value="blankPerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id='judgeScoreText<s:property value="id"/>'>
							<s:property value="judgePerScore"/>
							<a href=# onclick='changeJudgePerScore(true, <s:property value="judgePerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/plus1.png' width='20'></a>
							<a href=# onclick='changeJudgePerScore(false,<s:property value="judgePerScore"/>,<s:property value="id"/>,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT" />,
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size" />);'><img src='images/minus1.png' width='20'></a>
						</td>
						<td id='totalScoreText<s:property value="id"/>'>
							<s:property value="#attr['EXAM_QUESTION_'+examSelect].CHOICE_LIST.size*choicePerScore
							+#attr['EXAM_QUESTION_'+examSelect].BLANK_CNT*blankPerScore
							+#attr['EXAM_QUESTION_'+examSelect].JUDGE_LIST.size*judgePerScore"/>
							<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy(<s:property value="id"/>)'>删除</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			
			<button class="green darken-4 waves-effect waves-teal btn-flat" 
			type="button" style="margin-top:20px;<s:if test='strategyList==null && strategySelect==0'>display:none;</s:if>" id="createStrategyButton"
			onclick="createStrategy(form1.examSelect.value);">
				<span class="yellow-text text-lighten-1">创建新的分数分配策略
        		<i class="material-icons right">loop</i></span>
    		</button>
    		
    		<button class="purple darken-4 waves-effect waves-teal btn-flat" 
			type="button" style="margin-top:20px;<s:if test='strategyList==null && strategySelect==0'>display:none;</s:if>" id="selectStudentButton"
			onclick="form1.action='studentselect';form1.submit();">
				<span class="yellow-text text-lighten-1">指定参加考试的学生
        		<i class="material-icons right">loop</i></span>
    		</button>
    		
    		<div id="students" style="margin-top:20px;">
    			<s:iterator value="studentList" status="st" var="item">
    				<div class="chip" style="margin:2px;">
				        <img src="images/soccer4.jpg" alt="大黄鸭">
				        <s:property value="name"/>(学号:<s:property value="registerNo"/>)
				    </div>
    			</s:iterator>
    		</div>
    		
    		<button class="red darken-4 waves-effect waves-teal btn-flat" 
			type="button" style="margin-top:20px;<s:if test='studentList==null || studentList.size==0'>display:none;</s:if>" id="selectStudentButton"
			onclick="form1.action='createstudentexam';form1.submit();">
				<span class="yellow-text text-lighten-1">为这些学生创建考试
        		<i class="material-icons right">loop</i></span>
    		</button>
		</div>
	</div>
	</form>
	<%@ include file="include/footer.jsp" %>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	
	<script>
		function createStrategy(examId){
			$.post("createexamstrategy",
					{examSelect:form1.examSelect.value},
					function(data,status){
						getStrategyList(form1.examSelect.value,data.CHOICE_CNT,data.BLANK_CNT,data.JUDGE_CNT);
					});
		}
		function deleteStrategy(strategyId){
			$.post("deleteexamstrategy",
					{examSelect:form1.examSelect.value, strategyId:strategyId},
					function(data,status){
						getStrategyList(form1.examSelect.value,data.CHOICE_CNT,data.BLANK_CNT,data.JUDGE_CNT);
					});			
		}
		function getStrategyList(examId,choiceCnt,blankCnt,judgeCnt){
			$.post("liststrategy",
					{examSelect:form1.examSelect.value},
					function(data,status){
						var showHtml="";
						for(var i=0; i<data.length; i++){
							showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td><input type='radio' class='with-gap' name='strategySelect' id='strategySelect"
										+data[i].id+"' value='"+data[i].id+"'><label for='strategySelect"+data[i].id+"'>"
										+(i+1)+"</label></td>";
							showHtml+="<td id='strategyName"+data[i].id+"'><span>"+data[i].name
										+"</span><input type='text' style='width:100px;' name='strategyName"
										+data[i].id+"' value='"+data[i].name
										+"'><a href=# class='btn btn-small' onclick='changeStrategyName("+data[i].id+");'>更新</a></td>";
							showHtml+="<td id='choiceScoreText"+data[i].id+"'>"
										+data[i].choicePerScore
										+"<a href=# "+" onclick='changeChoicePerScore(true,"
										+data[i].choicePerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/plus1.png' width='20'></a>"
										+"<a href=# "+" onclick='changeChoicePerScore(false,"
										+data[i].choicePerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/minus1.png' width='20'></a></td>"
							showHtml+="<td id='blankScoreText"+data[i].id+"'>"
										+data[i].blankPerScore
										+"<a href=# "+" onclick='changeBlankPerScore(true,"
										+data[i].blankPerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/plus1.png' width='20'></a>"
										+"<a href=# "+" onclick='changeBlankPerScore(false,"
										+data[i].blankPerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/minus1.png' width='20'></a></td>"
							showHtml+="<td id='judgeScoreText"+data[i].id+"'>"
										+data[i].judgePerScore
										+"<a href=# "+" onclick='changeJudgePerScore(true,"
										+data[i].judgePerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/plus1.png' width='20'></a>"
										+"<a href=# "+" onclick='changeJudgePerScore(false,"
										+data[i].judgePerScore+","+data[i].id+","+choiceCnt+","+blankCnt+","+judgeCnt
										+");'><img src='images/minus1.png' width='20'></a></td>"
							showHtml+="<td id='totalScoreText" +data[i].id +"'>"
										+(data[i].choicePerScore*choiceCnt+data[i].blankPerScore*blankCnt+data[i].judgePerScore*judgeCnt)
										+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("
										+data[i].id+")'>删除</a></td></tr>";
						}
						$('#strategyTableBody').html(showHtml);
						$('#strategyTable').show();
						$('#createStrategyButton').show();
						$('#selectStudentButton').show();
					});
		}
		function changeStrategyName(strategyId){
			$.post("updatestrategyname",
					{strategyName:form1['strategyName'+strategyId].value,strategyId:strategyId},
					function(data,status){
						$("#strategyName"+strategyId).html("<span>"+data.name+"</span><input type='text' style='width:100px;' name='strategyName"+data.id+"' value='"+data.name+"'><a href=# class='btn btn-small' onclick='changeStrategyName("+data.id+");'>更新</a>");
					}
			);
		}
		function changeChoicePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updatechoicescore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#choiceScoreText"+strategyId).html(data.choicePerScore+"<a href=# "+"' onclick='changeChoicePerScore(true,"
								+data.choicePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeChoicePerScore(false,"
								+data.choicePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html( (data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt)
								+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
		}
		function changeBlankPerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updateblankscore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#blankScoreText"+strategyId).html(data.blankPerScore+"<a href=# "+"' onclick='changeBlankPerScore(true,"
								+data.blankPerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeBlankPerScore(false,"
								+data.blankPerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html( (data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt) 
								+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
		}
		function changeJudgePerScore(plusOrMinus,value,strategyId,choiceCnt,blankCnt,judgeCnt){
			if(plusOrMinus) value++; else value--;
			$.post("updatejudgescore",
					{score:value,strategyId:strategyId},
					function(data,status){
						$("#judgeScoreText"+strategyId).html(data.judgePerScore+"<a href=# "+"' onclick='changeJudgePerScore(true,"
								+data.judgePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/plus1.png' width='20'></a>"
								+"<a href=# "+"' onclick='changeJudgePerScore(false,"
								+data.judgePerScore+","+strategyId+","+choiceCnt+","+blankCnt+","+judgeCnt
								+");'><img src='images/minus1.png' width='20'></a>");
						$("#totalScoreText"+strategyId).html((data.choicePerScore*choiceCnt+data.blankPerScore*blankCnt+data.judgePerScore*judgeCnt)
											+"<a href=# class='btn btn-small' style='margin-left:5px;' onclick='deleteStrategy("+data.id+")'>删除</a>");
					}
			);
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
	    });
	   	
	</script>
	
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