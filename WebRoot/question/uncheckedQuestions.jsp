<%@ page import="cn.kuroko.faq.util.PersonInfo"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>
<%@ page import="cn.kuroko.faq.bean.Person"%>
<%@ page import="cn.kuroko.faq.bean.Reply"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript" src="/FAQ/person/ajax_person.js"></script>
<script type="text/javascript">
	addCurrentTagClass('T0101228');
</script>


<div id="mainContentPos2">
	<div id="mainContent2">


		<div class="mainContentFrame">
			<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
		</div>
		<div id="breadroadbox">当前位置：待审核问题（点击可查看详细信息，并且对该问题进行操作）</div>
		<c:if test="${personInfo.isAdmin() }">
			<table width=850>
				<%
					List<Question> lstQuestions = (List<Question>) request
							.getAttribute("lstUnCheckedQuestions");
					if (null != lstQuestions && 0 < lstQuestions.size()) {
				%>
				<tr class='bg2'>
					<td style="font-size: 15;">问题题目</td>
					<td style="font-size: 15;">问题内容</td>
					<td style="font-size: 15;">提问日期</td>
					<td style="font-size: 15;">问题作者</td>
					<td style="font-size: 15;">审&nbsp&nbsp&nbsp核&nbsp&nbsp&nbsp操&nbsp&nbsp&nbsp作</td>
					
				</tr>
				<%
				for(int i = 0; i < lstQuestions.size(); i++){
					if(0==i%2)
						out.println("<tr class='bg4'>");
					else
						out.println("<tr class='bg2'>");
					out.println("<td><a href='/FAQ/question.do?action=initQuestion&id="
									+ lstQuestions.get(i).getId()
									+ "'>"+lstQuestions.get(i).getTitle()
					 				+"</a></td><td>"+"<a href='/FAQ/question.do?action=initQuestion&id="
									+ lstQuestions.get(i).getId()
									+ "'>"
									+ lstQuestions.get(i).getContent() 
									+"</td>"
									+"<td>"
									+lstQuestions.get(i).getDateCreated()
									+"</td>"
									+"<td>"
									+lstQuestions.get(i).getAuthor().getName()
									+"</td>"
									+"<td>"
									);
											
					%>
				<input type="button" class="bigBtn" value="通过"
					onclick="window.location.href='/FAQ/question.do?action=permitWithoutLeap&id=<%out.print(lstQuestions.get(i).getId());%>'" />

				<input type="button" class="bigBtn" value="删除"
					onclick="window.location.href='/FAQ/question.do?action=deleteWithoutLeap&id=<%out.print(lstQuestions.get(i).getId());%>'" />
				</td>
				</tr>

				<%
					}
				%>
					<%
						} else {
					%> 没有待审核的问题... <%
							}
						%>
					
			</table>
		</c:if>
		<c:if test="${!personInfo.isAdmin() }">您没有权限这么做</c:if>
	</div>
	<div class="mainContentFrame">
			<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
		</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>