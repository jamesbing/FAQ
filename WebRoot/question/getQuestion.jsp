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

<jsp:include page="../top.jsp"></jsp:include>
<script type="text/javascript" src="/FAQ/question/ajax_question.js"></script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<script type="text/javascript">
		addCurrentTagClass('T0101228');
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
		<div id="breadroadbox">当前位置：问题详细信息</div>

		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w"></strong> &nbsp; <span
						style="color:red;font-weight:bold;">${message} </span></th>
				</tr>
			</table>
		</div>
		<div id="ContentShowBox">
			<table width="850">
				<tr class="bg4">
					<td width="150" class="tdbg1">提问人：</td>
					<td>
						<table>
							<tr>
								<td width=390>${question.author.name}</td>
								<td><span id="cnug07_err" class="redWord"></span>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr class="bg2">
					<td class="tdbg1">问题标题：</td>
					<td>
						<table>
							<tr>
								<td width=390>${question.title }</td>
								<td><span id="cnug07_err" class="redWord"></span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="bg4">
					<td class="tdbg1">问题描述：</td>
					<td>
						<table class="inputTableStyle">
							<tr>
								<td width=390>${question.content }
								<td><span id="cnug07_err" class="redWord"></span>
								</td>
							</tr>
						</table></td>
				</tr>

				<tr class="bg2">
					<td class="tdbg1">问题类别：</td>
					<td>${question.qtype1 }&nbsp; ${question.qtype2 }&nbsp;
						${question.qtype3 }&nbsp;</td>
				</tr>
				<tr class="bg4">
					<td class="tdbg1">备注：</td>
					<td>${question.remarks }</td>
				</tr>

				<tr class="bg2">
					<td class="tdbg1">指定专家：</td>

					<td><%=request.getAttribute("expername")%></td>
				</tr>

				<c:if
					test='${personInfo.isAdmin() || (!personInfo.isAdmin() && !question.qtype1.equals("意见建议"))  || (question.qtype1.equals("意见建议") && question.ok == true  && personInfo.isExpert()) }'>
					<tr class="bg2">
						<td class="tdbg1">答复记录：</td>
						<td colspan="5" class="tdbg2">共有<%
							List<Reply> lstReply = (List<Reply>) request
										.getAttribute("lstReply");
								if (null == lstReply)
									out.print("0");
								else
									out.print(lstReply.size());
						%>条答复 <c:forEach items="${lstReply }" var="reply">
								<table class="writeback" cellspacing="1">
									<tbody>
										<tr class="itemShow2">
											<td rowspan="2" width="78px"><img
												src="/FAQ/images/writeback.gif" width="78px" height="76px"
												border="0"></td>

											<td width="450px">咨询员：${reply.author.name}
												(${reply.author.email})</td>

											<td width="200px">发表时间：<fmt:formatDate
													value="${reply.dateCreated}" type="both" /></td>
										</tr>
										<tr class="bg4">
											<td colspan="2" width="650px">${reply.content}</td>
										</tr>
										<tr class="bg4">
											<c:if test="${reply.filePath!=null}">
											<td>回复附件:</td>
												<td><a href="${reply.filePath}">本条回复有附件，点击下载或右键另存为后查看</a>
												</td>
											</c:if>
										</tr>
									</tbody>
								</table>
							</c:forEach>
				</c:if>
				</td>
				</tr>


				<tr class="bg4">
					<td class="tdbg1">问题附件：</td>
					<c:if test="${question.filePath==null}">
						<td>问题无附件</td>
					</c:if>
					<c:if test="${question.filePath!=null}">
						<td><a href="${question.filePath}">问题有附件，点击下载或右键另存为查看附件</a>
						</td>
					</c:if>
				</tr>
				<c:if
					test='${question.qtype1 != "意见建议" && personInfo.isExpert() || (question.qtype1 == "意见建议" && null != personInfo && !question.ok)}'>
					<html:form action="/reply" method="post" enctype="multipart/form-data">
						<table>
							<html:hidden property="action" value="addReply" />
							<html:hidden property="expertId" value="${personInfo.id}" />
							<html:hidden property="questionId" value="${question.id}" />
							<tr class="bg4">
								<td class="tdbg1">回答问题：</td>
								<td><html:textarea property="content"
										style="width: 600px; height: 200px" /></td>
							</tr>
							<tr class="bg2">
								<td class="tdbg1">在回复中添加附件：</td>
								<td>
									<!-- //////////////////////////////////////////// -->
									<table>
											<input type="file" name="file"> 请您不要上传超过2M文件！
											</input>
									</table>
									<html:submit styleClass="bigBtn" value="提交回复"></html:submit>
								</td>
								
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</html:form>
				</c:if>
				<tr>
					<c:if test="${personInfo.isAdmin()}">
						<c:choose>
							<c:when test="${!question.ok }">
								<td><input type="button" class="bigBtn" value="审核通过该问题"
									onclick="window.location.href='/FAQ/question.do?action=permit&id=<%out.print(request.getParameter("id"));%>'" />
								</td>
							</c:when>
							<c:otherwise>
								<td />
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${!question.popular}">
								<td><input type="button" class="bigBtn" value="将该问题设为常见"
									onclick="window.location.href='/FAQ/question.do?action=popular&id=<%out.print(request.getParameter("id"));%>'" />
								</td>
							</c:when>
							<c:otherwise>
								<td><input type="button" class="bigBtn" value="将该问题设为一般问题"
									onclick="window.location.href='/FAQ/question.do?action=repopular&id=<%out.print(request.getParameter("id"));%>'" />
								</td>
							</c:otherwise>
						</c:choose>
						<td><input type="button" class="bigBtn" value="删除该问题"
							onclick="window.location.href='/FAQ/question.do?action=delete&id=<%out.print(request.getParameter("id"));%>'" />
						</td>
					</c:if>
				</tr>
				<c:if test="${personInfo.isAdmin() }">
					<tr>
						<td><select id="expert">
								<option value="" />
								<c:forEach var="expert" items="${lstExpert}">
									<c:if test="${expert.isOk() }">
										<option value="${expert.id}">${expert.name}</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td><input type="button" value="指定专家"
							onclick="chooseExpert()" /></td>
					</tr>
				</c:if>
			</table>
		</div>

		<div class="mainContentFrame">
			<img src="/FAQ/images/imgFrame02.gif" width="970" height="12"
				border="0" />
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>