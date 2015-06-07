<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Person"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="cn.kuroko.faq.bean.ExpertType"%>
<%@ page import="cn.kuroko.faq.constant.Constant"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top_ENG.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101230');
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
		<div id="breadroadbox">Experts' File</div>
		<div id="ContentShowBox">
			<div class="searchbox">
				<div style="margin-top:14px">
					<html:form action="/person" method="get" style="font-size: 80%;">
						<html:hidden property="action" value="searchExpert_ENG" />
							Question Type：<html:text property="expertType"></html:text>&nbsp; 
							<html:submit styleClass="btnstyle" value="Query"></html:submit>
					</html:form>
				</div>
			</div>
			<div class="tablePos">
				<table>
					<tr>
						<th>Expert Name</th>
						<th>Question Type</th>
						<!-- <th>所在单位</th>  -->
						<th>E-Mail</th>
						<th>TEL</th>
						<th width=40>Operate</th>
					</tr>
					<%
						List<Person> lstExpert = (List<Person>) request
								.getAttribute("lstExpert");
						HashMap<Person, List<ExpertType>> hmExpertType = (HashMap<Person, List<ExpertType>>) request
								.getAttribute("hmExpertType");
						if (null == lstExpert || 0 == lstExpert.size()) {
					%>
					<tr>
						<td>No eligible expert...</td>
					</tr>
					<%
						} else {
							boolean flag = true;
							for (int i = 0; i < lstExpert.size(); i++) {
								Person expert = lstExpert.get(i);
								if ((expert.getPower() & Constant.POWER_NEED_CHECK) == Constant.POWER_NEED_CHECK)
									continue;
								flag = !flag;
								if (flag) {
					%>
					<tr class="bg2">
						<%
							} else {
						%>
					
					<tr class="bg4">
						<%
							}
						%>
						<td>
							<%
								out.print(expert.getName());
							%>
						</td>
						<td class="tdLeftPos">
							<%
								List<ExpertType> lstET = hmExpertType.get(expert);
										//if (i != 0)
										//	out.print("<br />");
										if (null != lstET) {
											for (ExpertType et : lstET) {
												out.print(et.getType().getName());
												out.print("\n");
											}
										}
							%><br /></td>
						<!-- <td class="tdLeftPos">
							<%
							//	out.print(expert.getWorkspace() == null ? "" : lstExpert
							//					.get(i).getWorkspace());
							%>
						</td> -->
						<td class="tdLeftPos">
							<%
								out.print(expert.getEmail());
							%>
						</td>
						<td class="tdLeftPos">
						<%
							if (null == expert.getTel())
								out.print("");
							else
								out.print(expert.getTel());
							%>
						</td>
						<td><a
							href="/FAQ/question.do?action=initAdd_ENG&expert=<%out.print(expert.getId());%>">Consult</a>
						</td>
					</tr>
					<%
						}
						}
					%>
				</table>
			</div>
		</div>
	</div>
	<div class="mainContentFrame">
		<img src="./images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>