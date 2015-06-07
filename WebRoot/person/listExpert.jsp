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

<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101230');
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
		<div id="breadroadbox">当前位置：专家档案</div>
		<div id="ContentShowBox">
			<div class="searchbox">
				<div style="margin-top:14px">
					<html:form action="/person" method="get" style="font-size: 80%;">
						<html:hidden property="action" value="searchExpert" />
							问题类别：<html:text property="expertType"></html:text>&nbsp; 
							<html:submit styleClass="btnstyle" value="查询"></html:submit>
					</html:form>
				</div>
			</div>
			<div class="tablePos">
				<table>
					<tr>
						<th>专家姓名</th>
						<th>问题类别</th>
						<!-- <th>所在单位</th>  -->
						<th>电子邮件</th>
						<th>电话</th>
						<th width=40>操作</th>
					</tr>
					<%
						List<Person> lstExpert = (List<Person>) request
								.getAttribute("lstExpert");
						HashMap<Person, List<ExpertType>> hmExpertType = (HashMap<Person, List<ExpertType>>) request
								.getAttribute("hmExpertType");
						if (null == lstExpert || 0 == lstExpert.size()) {
					%>
					<tr>
						<td>没有找到专家信息...</td>
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
							href="/FAQ/question.do?action=initAdd&expert=<%out.print(expert.getId());%>">咨询</a>
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