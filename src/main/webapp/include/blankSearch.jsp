<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="main">
	<div style="margin-top: 20px;"><!-- delete class for dialog -->
		<!-- change name & action for dialog , change each control's name (add 'Blank')-->
		<form name="blanksearchform" method="post" action="blanksearchlist1">
			<div class="row">
                   <div class="input-field col l3 m4 s12">
                       <i class="material-icons prefix small">info_outline</i>
                       <input type="text" placeholder="输入题干包含的内容" id="contentSearch" name="contentSearchBlank" 
                       value="<s:property value="contentSearch"/>" 
                       class="validate" style="font-size:large">
                       <label for="contentSearch">题干内容</label>
                   </div>
                   <div class="input-field col l3 m4 s12">
                       <i class="tiny material-icons prefix ">toc</i>
                       <input type="text" placeholder="输入答案中包含的内容" id="answerSearch" name="answerSearchBlank" 
                       value="<s:property value="answerSearch"/>" 
                       class="validate" style="font-size:large">
                       <label for="answerSearch">答案内容</label>
                   </div>
                   <div class="input-field col l3 m4 s12">
                       <i class="material-icons prefix small">description</i>
                       <input type="text" placeholder="输入知识点" id="knowledgeSearch" name="knowledgeSearchBlank" 
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
                   <div class="input-field col l3 m4 s12 vertical-align"><!-- change to ajaxSearch for dialog  -->
					<button class="red darken-4 waves-effect waves-teal btn-flat" type="button" onclick="ajaxSearchBlank()">
						<span class="yellow-text text-lighten-1">搜索
		        		<i class="material-icons right">search</i></span>
		    		</button>
		        </div>
               </div>
		</form>
		<form name="blankselectform"  method="post"><!-- add form for dialog -->
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
					<th>课程</th>
				</tr>
			</thead>
			<tbody id="blankListBody">
			<s:iterator value="questionList" status="st" var="item">
				<tr
					style="background-color:<s:if test="#st.odd">#efefef</s:if><s:else>#ffffff</s:else>">
					<td><s:property value="#st.index+1" /></td>
					<td><s:property value="name"/></td>
					<td><a class="namelink"
						href="<s:url action="blankdetail"><s:param name="qid" value="%{id}"></s:param></s:url>"><s:property
								value="content" /></a></td>
					<td>
						<s:property value="answer" />
						<s:if test="answer2 != null"><br><s:property value="answer2" /></s:if>
						<s:if test="answer3 != null"><br><s:property value="answer3" /></s:if>
						<s:if test="answer4 != null"><br><s:property value="answer4" /></s:if>
						<s:if test="answer5 != null"><br><s:property value="answer5" /></s:if>
						<s:if test="answer6 != null"><br><s:property value="answer6" /></s:if>
						<s:if test="answer7 != null"><br><s:property value="answer7" /></s:if>
						<s:if test="answer8 != null"><br><s:property value="answer8" /></s:if>
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
		form2.action="searchblank";
		form2.submit();
	}
	function ajaxSearchBlank(){
		$.post("blanksearchlist1",
				{contentSearch:document.forms['blanksearchform'].contentSearchBlank.value,
				answerSearch:document.forms['blanksearchform'].answerSearchBlank.value,
				knowledgeSearch:document.forms['blanksearchform'].knowledgeSearchBlank.value},
				function(data,status){
					var showHtml="";
					for(var i=0; i<data.length; i++){
						showHtml+="<tr style='background:"+(i%2==0?"#efefef":"#ffffff")+";'><td><input type='checkbox' class='with-gap' name='blankSelect' id='blankSelect"
						+data[i].id+"' value='"+data[i].id+"'><label for='blankSelect"+data[i].id+"'>"
						+data[i].id+"</label></td>";
						showHtml+="<td>"+(!data[i].name?'':data[i].name)+"</td>";
						showHtml+="<td>"+(!data[i].content?'':data[i].content)+"</td>";
						showHtml+="<td>"+(!data[i].answer?'':data[i].answer)+"</td>";
						showHtml+="<td>"+(!data[i].knowledgePoint?'':data[i].knowledgePoint)+"</td>";
						showHtml+="<td>"+(!data[i].course?'':data[i].course.name)+"</td></tr>";
					}
					$('#blankListBody').html(showHtml);
				});
	}
</script>		