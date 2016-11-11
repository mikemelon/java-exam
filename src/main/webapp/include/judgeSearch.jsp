<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="main">
	<div style="margin-top: 20px;"><!-- delete class for dialog -->
		<!-- change name & action for dialog , change each control's name (add 'Judge')-->
		<form name="judgesearchform" method="post" action="judgesearchlist1">
		<input type="hidden" name="answerSearch2">
			<div class="row">
                   <div class="input-field col l3 m4 s12">
                       <i class="material-icons prefix small">info_outline</i>
                       <input type="text" placeholder="输入题干包含的内容" id="contentSearch" name="contentSearchJudge" 
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
		<form name="judgeselectform" method="post"><!-- add form for dialog -->
		<input type="hidden" name="examName" value="">
		<input type="hidden" name="examDetail" value="">
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
			<s:iterator value="questionList" status="st" var="item">
				<tr	style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="name"/></td>
					<td><a class="namelink"
						href="<s:url action="blankdetail"><s:param name="qid" value="%{id}"></s:param></s:url>"><s:property
								value="content" /></a></td>
					<td>
						<s:if test="answer==\"T\""><i class='material-icons small'>done</i></s:if>
						<s:elseif test="answer==\"F\""><i><img src='images/wrong.png' width='20'/></i></s:elseif>
					</td>
					<td><s:property value="knowledgePoint" /></td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		</form>
		
		<!-- 分页控件 -->
		<form name="form2" method="post">
		<input type="hidden" name="pageIndex" value="<s:property value="pageIndex"/>" /> 
		<s:if test="totalPage gt 1">
		<ul class="pagination">
			<li <s:if test="pageIndex lt 1">class="disabled"</s:if><s:else>class="waves-effect"</s:else>>
				<a href="#!" <s:if test="pageIndex gt 0">onclick="selectPage(<s:property value="pageIndex"/>)"</s:if>>
				<i class="material-icons">chevron_left</i></a>
			</li>
			<s:iterator var="item" begin="1" end="totalPage">
			  <s:if test="pageIndex==top-1"><li class="active"><a href="#!"><s:property/></a></li></s:if>
			  <s:else><li class="waves-effect"><a href="#!" onclick="selectPage(<s:property/>)"><s:property/></a></li></s:else>
			</s:iterator>
			<li <s:if test="pageIndex gt (totalPage-2)">class="disabled"</s:if><s:else>class="waves-effect"</s:else>>
				<a href="#!" <s:if test="pageIndex lt (totalPage-1)">onclick="selectPage(<s:property value="pageIndex+2"/>)"</s:if>>
				<i class="material-icons">chevron_right</i></a>
			</li>
		</ul>
		</s:if>
		</form>
	</div>
</div>

<script>
	function selectPage(page){
		form2.pageIndex.value=page-1;
		form2.action="searchjudge";
		form2.submit();
	}
	function ajaxSearchJudge(){
		$.post("judgesearchlist1",
				{contentSearch:document.forms['judgesearchform'].contentSearchJudge.value,
				answerSearch:document.forms['judgesearchform'].answerSearchJudge.value,
				knowledgeSearch:document.forms['judgesearchform'].knowledgeSearchJudge.value},
				function(data,status){
					var showHtml="";
					for(var i=0; i<data.length; i++){
						showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td><input type='checkbox' class='with-gap' name='judgeSelect' id='judgeSelect"
						+data[i].id+"' value='"+data[i].id+"'><label for='judgeSelect"+data[i].id+"'>"
						+data[i].id+"</label></td>";
						showHtml+="<td>"+(!data[i].name?'':data[i].name)+"</td>";
						showHtml+="<td>"+(!data[i].content?'':data[i].content)+"</td>";
						showHtml+="<td>"+(!data[i].answer?'':data[i].answer)+"</td>";
						showHtml+="<td>"+(!data[i].knowledgePoint?'':data[i].knowledgePoint)+"</td></tr>";
					}
					$('#judgeListBody').html(showHtml);
				});
	}
</script>		