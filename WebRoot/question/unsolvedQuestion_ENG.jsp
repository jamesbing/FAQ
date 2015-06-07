<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../top_ENG.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101233');
</script>
<script type="text/javascript">
	function viewPerson() {
		var o = document.getElementById('prefixID');
		window.location.href = '/FAQ/question.do?action=setPrefix&preString=' + o.value;
	}
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
		<div id="breadroadbox">Unsolved Questions</div>
	
		<div id="ContentShowBox">
		
			<table width=850>
				<%
					List<Question> lstQuestion = (List<Question>) request
							.getAttribute("lstQuestion");
					if (null != lstQuestion && 0 < lstQuestion.size()) {
				%>
				<tr class='bg2'>
					<td style="font-size: 15;">Question Title</td>
					<td style="font-size: 15;">Question Type</td>
				</tr>
				
				<%
					for (int i = 0; i < lstQuestion.size(); i++) {
							if (0 == i % 2)
								out.println("<tr class='bg4'>");
							else
								out.println("<tr class='bg2'>");
							out.println("<td><a href='/FAQ/question.do?action=initQuestion_ENG&id="
									+ lstQuestion.get(i).getId()
									+ "'>"
									+ lstQuestion.get(i).getTitle()
									+ "</a></td><td>"
									+ lstQuestion.get(i).getQtype1() + "</td></tr>");
						}
					} else {
				%>No eligible question...
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