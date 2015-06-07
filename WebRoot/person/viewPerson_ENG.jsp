<%@page import="javax.security.auth.login.AccountException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.kuroko.faq.bean.Person"%>
<%@ page import="cn.kuroko.faq.bean.ExpertType"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>
<%@ page import="cn.kuroko.faq.constant.Constant"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../top_ENG.jsp"></jsp:include>
<script type="text/javascript" src="/FAQ/person/ajax_person.js"></script>
<script type="text/javascript">
	function removePerson(id) {
		ajax_removePerson(id);
	}

	function checkPerson(id) {
		ajax_checkPerson(id);
	}
	
	function changeType(id) {
		var type1 = document.getElementById("type1").value;
		var type2 = document.getElementById("type2").value;
		var type3 = document.getElementById("type3").value;
		ajax_changeType(id, type1, type2, type3);
	}
	
	function getType(a) {
		if (a == 'type2') {
			var o = document.getElementById('type1');
			var t = o.options[o.selectedIndex].value;
			getType2(t);
		} else if (a == 'type3') {
			var o = document.getElementById('type2');
			var t = o.options[o.selectedIndex].value;
			getType3(t);
		}
	}
</script>

<div id="mainContentPos2">
	<div id="mainContent2">
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w">User's detail information</strong> &nbsp; <span
						style="color:red;font-weight:bold;">${message} </span>
					</th>
				</tr>
			</table>
		</div>

		<div class="t t2">
			<table width="100%">
				<tr class="bg2">
					<td style="width:120px; " class="tdbg1">Name:</td>
					<td>${person.name }</td>
				</tr>
				<tr class="bg4">
					<td style="width:120px; " class="tdbg1">Sex:</td>
					<td>		<c:if test="${personInfo.isMale() }">Male</c:if>
								<c:if test="${personInfo.isFemale() }">Female</c:if>
					</td>
				</tr>
				<tr class="bg2">
					<td style="width:120px; " class="tdbg1">Email:</td>
					<td>${person.email }</td>
				</tr>
				<tr class="bg4">
					<td style="width:120px; " class="tdbg1">Register Time:</td>
					<td><fmt:formatDate value="${person.dateCreated }" type="both" />
					</td>
				</tr>
				<tr class="bg2">
					<td style="width:120px; " class="tdbg1">Last login time:</td>
					<td><fmt:formatDate value="${person.dateLastActived }"
							type="both" />
					</td>
				</tr>

			<%
				Person person = (Person) request.getAttribute("person");
				
			%>
			<tr class="bg4">
					<td style="width:120px; " class="tdbg1">Expert Type:</td>
					<td>
					<% 
					List<ExpertType> lstType = (List<ExpertType>) request.getAttribute("etlst");
					if (null != lstType) {
											for (ExpertType et : lstType) {
												out.print(et.getType().getName());
												out.print("\n");
											}
										}
										 %>
					</td>
				</tr>

			</table>
			
			<c:if test="${personInfo.isAdmin() }">
				<c:if test="${person.isExpert() }">
					<table class="inputTableStyle" id="d-type">
						<html:hidden property="action" value="changeType" />
						<tr>
							<td>Change Expert Type</td>
						</tr>
						<tr>
							<td><select name="type1" id="type1"
								onchange="getType('type2');">
									<option value="" >Please Select</option>
									<c:forEach var="type" items="${qtype1}">
										<option value="${type.name}" 
										 
											>
										 ${type.name}</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td><select name="type2" id="type2"
								onchange="getType('type3');">
									<option value="" />
							</select>
							</td>
						</tr>
						<tr>
							<td><select name="type3" id="type3">
									<option value="" />
							</select>
							</td>
						</tr>
						<tr>
							<td><input type="button" value="Submit"
								onclick="changeType(<%out.print(person.getId());%>)" />
							</td>
						</tr>
					</table>
				</c:if>
				<%
					if ((person.getPower() & Constant.POWER_NEED_CHECK) == Constant.POWER_NEED_CHECK) {
				%>
				<span>用户状态：待审核</span>
				<input type="button" value="审核通过"
					onclick="checkPerson(<%=person.getId()%>)" />
				<%
					}
				%>
				<input type="button" value="删除该用户"
					onclick="removePerson(<%=person.getId()%>)" />
			</c:if>
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>