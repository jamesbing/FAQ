<%@ page import="cn.kuroko.faq.util.PersonInfo"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>
<%@ page import="cn.kuroko.faq.bean.Person"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript" src="/FAQ/person/ajax_person.js"></script>
<script type="text/javascript">
	addCurrentTagClass('T0101231');
</script>
<script type="text/javascript">
	function addAdmin() {
		var str = document.getElementById('txtAddAdmin').value;
		ajax_addAdmin(str);
	}

	function removeAdmin() {
		var str = document.getElementById('txtAddAdmin').value;
		ajax_removeAdmin(str);
	}

	function viewPerson() {
		var o = document.getElementById('txtViewPerson');
		window.location.href = '/FAQ/person.do?action=view&pName=' + o.value;
	}
</script>

<div id="mainContentPos2">
	<div id="mainContent2">


		<div class="mainContentFrame">
			<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
		</div>
		<div id="breadroadbox">当前位置：用户管理</div>
		<c:if test="${personInfo.isAdmin() }">
			<table width=850>
				<%
					List<Person> lstPerson = (List<Person>) request
								.getAttribute("lstPerson");
				%>
				<tr class='bg4'>
					<td class="tdbg1">查看用户：</td>
					<td><input type="text" id="txtViewPerson" /> <input
						type="button" class="bigBtn" value="查看" onclick="viewPerson();" />
					</td>
				</tr>
				<tr class='bg2'>
					<td class="tdbg1">添加/删除管理员：</td>
					<td><input type="text" id="txtAddAdmin" /> <input
						type="button" class="bigBtn" value="添加" onclick="addAdmin();" />
						<input type="button" class="bigBtn" value="删除"
						onClick="removeAdmin();" />
					</td>
				</tr>
				<tr class='bg4'>
					<td class="tdbg1">待审核专家：</td>
					<td>
						<%
							if (null != lstPerson && 0 < lstPerson.size()) {
									for (Person person : lstPerson) {
										out.print("<span><a href='/FAQ/person.do?action=view&id="
												+ person.getId()
												+ "'>"
												+ person.getName()
												+ "</a></span>&nbsp ");
									}
								} else {
						%> 没有待审核的专家... <%
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