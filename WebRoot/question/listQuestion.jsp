<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>
<%@ page import="cn.kuroko.faq.bean.Person"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101228');
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
		<div id="breadroadbox">当前位置：问题检索</div>
		<div id="breadroadbox">
		
		<c:if test="${personInfo.isAdmin() }">
					<tr>
						<td><input type="button" class="bigBtn" value="查看未审核问题"
									onclick="window.location.href='/FAQ/question.do?action=getUnCheckedQuestion'" />
						</td>
					</tr>
				</c:if>
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w"></strong> &nbsp; <span
						style="color:red;font-weight:bold;">${message} </span></th>
				</tr>
			</table>			
		</div>
		<div id="ContentShowBox">
			<span style="font-size: 13; font-style: italic;">提示:问题检索多个关键字请用空格隔开，支持模糊查询</span>
			<html:form action="/question" method="get">
				<table width="600" cellspacing="0" cellpadding="0">
					<html:hidden property="action" value="initList" />
					<tr>
						<td width="100">问题类别:</td>
						<td><input type="text" style="width: 600px" name="s_type"
							value='<%out.print(request.getParameter("s_type") != null ? request
						.getParameter("s_type") : "");%>' />
						</td>
					</tr>
					<tr>
						<td>问题题目:</td>
						<td><input type="text" style="width: 600px" name="s_title"
							value='<%out.print(request.getParameter("s_title") != null ? request
						.getParameter("s_title") : "");%>' />
						</td>
					</tr>
					<tr>
						<td>问题描述:</td>
						<td><input type="text" style="width: 600px" name="s_content"
							value='<%out.print(request.getParameter("s_content") != null ? request
						.getParameter("s_content") : "");%>' />
						</td>
					</tr>
					<tr>
						<c:if test="${personInfo != null }">
							<td><input type="checkbox" name="my">与我有关</input></td>
						</c:if>
						<td><html:submit styleClass="bigBtn">提交查询</html:submit>
						</td>
					</tr>
				</table>
			</html:form>
			<table width=850>
				<%
					List<Question> lstQuestion = (List<Question>) request
							.getAttribute("lstQuestion");
						
					List<Person> lstPrefix = (List<Person>) request
					.getAttribute("prefix");	
					if (null != lstQuestion && 0 < lstQuestion.size()) {
				%>
				<tr class='bg2'>
					<td style="font-size: 15;">问题编号</td>
					<td style="font-size: 15;">问题题目</td>
					<td style="font-size: 15;">问题类别</td>
				</tr>
				<%
					for (int i = 0; i < lstQuestion.size(); i++) {
							if (0 == i % 2)
								out.println("<tr class='bg4'>");
							else
								out.println("<tr class='bg2'>");
				
							out.println("<td><a href='/FAQ/question.do?action=initQuestion&id="
									+ lstQuestion.get(i).getId()
									+ "' target='_blank'>"
									+lstPrefix.get(0).getSetQID()+lstQuestion.get(i).getId()+"</td>"+
									"</a><td><a href='/FAQ/question.do?action=initQuestion&id="
									+ lstQuestion.get(i).getId()
									+ "' target='_blank'>"
									+ lstQuestion.get(i).getTitle()
									+ "</a></td><td>"
									+ lstQuestion.get(i).getQtype1() + "</td></tr>");
						}
					} else {
				%><tr>
					<td>没有符合条件的问题...</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<div class="mainContentFrame">
			<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>
