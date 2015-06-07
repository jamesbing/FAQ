<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>
<%@ page import="cn.kuroko.faq.bean.Reply"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>
<%@ page import="java.util.List"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../top_sub_ENG.jsp"></jsp:include>
<script type="text/javascript" src="/FAQ/question/ajax_question.js"></script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<script type="text/javascript">
		addCurrentTagClass('T0101232');
	</script>
	<div id="mainContent2">
		<div id="breadroadbox">Detail of Reply</div>

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
			<table width="850">
				<tr class="bg4">
					<td width="150" class="tdbg1">Expert：</td>
					<td>
						<table>
							<tr>
								<td width=390><%=request.getAttribute("erperName") %></td>
								<td><span id="cnug07_err" class="redWord"></span></td>
							</tr>
						</table></td>
				</tr>

				<tr class="bg2">
					<td class="tdbg1">Question Title：</td>
					<td>
						<table>
							<tr>
								<td width=390>${question.title }</td>
								<td><span id="cnug07_err" class="redWord"></span></td>
							</tr>
						</table></td>
				</tr>
				<tr class="bg4">
					<td class="tdbg1">Question's Description：</td>
					<td>
						<table class="inputTableStyle">
							<tr>
								<td width=390>${question.content }
								<td><span id="cnug07_err" class="redWord"></span></td>
							</tr>
						</table>
					</td>
				</tr>

				<tr class="bg2">
					<td class="tdbg1">Question Type：</td>
					<td>${question.qtype1 }&nbsp; ${question.qtype2 }&nbsp;
						${question.qtype3 }&nbsp;</td>
				</tr>
				<tr class="bg4">
					<td class="tdbg1">Question's Content：</td>
					<td><%=request.getAttribute("questionContent") %></td>
				</tr>
				
				<tr class="bg2">
					<td class="tdbg1">Reply：</td>
					
					<td><%=request.getAttribute("replyContent") %></td>
				</tr>

				</td>
				</tr>
				
				<tr>
					<c:if test="${personInfo.isAdmin()}">
						<c:choose>
							<c:when test="${!reply.ok }">
								<td><input type="button" class="bigBtn" value="审核通过该回复"
									onclick="window.location.href='/FAQ/reply.do?action=permit&id=<%out.print(request.getParameter("id"));%>'" />
								</td>
							</c:when>
							<c:otherwise>
								<td />
							</c:otherwise>
						</c:choose>
					
						<td><input type="button" class="bigBtn" value="删除该回复"
							onclick="window.location.href='/FAQ/reply.do?action=delete&id=<%out.print(request.getParameter("id"));%>'" />
						</td>
					</c:if>
				</tr>
			</table>
		</div>

		<div class="mainContentFrame">
			<img src="/FAQ/images/imgFrame02.gif" width="970" height="12"
				border="0" />
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>