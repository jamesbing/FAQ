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
	addCurrentTagClass('T0101234');
</script>


<div id="mainContentPos2">
	<div id="mainContent2">


		<div class="mainContentFrame">
			<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
		</div>
		<div id="breadroadbox">当前位置：修改我的回复</div>
		<c:if test="${personInfo.isExpert() }">
			<table width=850>
				<%
					List<Reply> lstReply = (List<Reply>) request
							.getAttribute("answerlist");
					if (null != lstReply && 0 < lstReply.size()) {
				%>
				<tr class='bg2'>
					<td style="font-size: 15;">问题题目</td>
					<td style="font-size: 15;">回复内容</td>
					<td style="font-size: 15;">操&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp作</td>

				</tr>
				<%
				for(int i = 0; i < lstReply.size(); i++){
					if(0==i%2)
						out.println("<tr class='bg4'>");
					else
						out.println("<tr class='bg2'>");
					out.println("<td>" +lstReply.get(i).getQuestion().getTitle()+
					 	"</a></td><td>"+"<a href='/FAQ/reply.do?action=edit&id="
									+ lstReply.get(i).getId()
									+ "'>"
									+ lstReply.get(i).getContent() 
									+"</td>");
				%>
				<td>
				<input type="button" class="bigBtn" value="修改"
					onclick="window.location.href='/FAQ/reply.do?action=edit&id=<%out.print(lstReply.get(i).getId());%>'" />
				<input type="button" class="bigBtn" value="删除"
					onclick="window.location.href='/FAQ/reply.do?action=deleteMyAnswer&id=<%out.print(lstReply.get(i).getId());%>'" />
				</td>
				</tr>
				<%
				}
				%>

				<%} else {
								%>
				当前没有可修改的回复...
				<%
							}
						%>

			</table>
		</c:if>
		<c:if test="${personInfo.isNotExpert() }">您没有权限这么做</c:if>
	</div>
	<div class="mainContentFrame">
		<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>