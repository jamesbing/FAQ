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
		addCurrentTagClass('T0101234');
		function chooseExpert() {
			var o = document.getElementById("expert");
			if (o.value == '')
				alert('请先选择专家');
			else {
				ajax_chooseExpert(
						o.value,
	<%Question q = (Question)request.getAttribute("question"); 
	out.print(q.getId());%>
		);
			}
		}
	</script>
	<div id="mainContent2">
		<div id="breadroadbox">Edit my reply</div>

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
					<td width="150" class="tdbg1">Questioner：</td>
					<td>
						<table>
							<tr>
								<td width=390>${question.author.name}</td>
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
					<td class="tdbg1">Type：</td>
					<td>${question.qtype1 }&nbsp; ${question.qtype2 }&nbsp;
						${question.qtype3 }&nbsp;</td>
				</tr>
				<tr class="bg4">
					<td class="tdbg1">Remark：</td>
					<td>${question.remarks }</td>
				</tr>
				</td>
				</tr>
				<c:if
					test='${question.qtype1 != "意见建议" && personInfo.isExpert() || (question.qtype1 == "意见建议" && null != personInfo && !question.ok)}'>
					<html:form action="/reply" method="post">
						<table>
							<html:hidden property="action" value="submitEdit_ENG" />
							<html:hidden property="expertId" value="${personInfo.id}" />
							<html:hidden property="questionId" value="${question.id}" />
							<html:hidden property="id" value="${reply.id}" />
							<tr class="bg4">
								<td class="tdbg1">Edit my reply：</td>
								<td><html:textarea property="content"
										style="width: 600px; height: 200px" /> <html:submit
										styleClass="bigBtn" value="Confirm&Submit"></html:submit></td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</html:form>
				</c:if>
				

		<div class="mainContentFrame">
			<img src="/FAQ/images/imgFrame02.gif" width="970" height="12"
				border="0" />
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>