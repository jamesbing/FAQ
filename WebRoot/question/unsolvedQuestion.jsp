<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101233');
</script>
<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="/images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">

	<div id="breadroadbox">当前位置：未解答问题</div>

	<div class="t" style="margin-bottom:0px; border-bottom:0">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<th class="h"><strong class="fl w"></strong> &nbsp; <span
					style="color:red;font-weight:bold;">${message} </span>
				</th>
			</tr>
		</table>
	</div>
	<div id="ContentShowBox">
		<table width=850>
		
		
			<%
					List<Question> lstQuestion = (List<Question>) request
							.getAttribute("lstQuestion");
					if (null != lstQuestion && 0 < lstQuestion.size()) {
				%>
			<tr class='bg2'>
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
									+ "'>"
									+ lstQuestion.get(i).getTitle()
									+ "</a></td><td>"
									+ lstQuestion.get(i).getQtype1() + "</td></tr>");
						}
					} else {
				%><td>没有未解答的问题...</td>
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