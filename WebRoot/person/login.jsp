<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top.jsp" flush="true"></jsp:include>
<div id="mainContentPos2">
	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
	<div style=" background-color: #D7F1FF;text-align: center;">
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w">用户登录</strong> &nbsp; <br />
						<span
						style="color:red;font-weight:bold;font-style: italic;font-size: 15">${message}
					</span></th>
				</tr>
			</table>
		</div>
		<html:form action="/person">
			<html:hidden property="action" value="login" />
			<div class="t t2">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr class="tr3">
						<td class="tdbg1">账号:</td>
						<td bgcolor="#D7F1FF"><html:text property="person.account" />
						</td>
					</tr>
					<tr class="tr3">
						<td class="tdbg1">密码:</td>
						<td bgcolor="#D7F1FF"><html:password property="person.password" />
						</td>
					</tr>
					<tr class="tr3" >
						<td colspan="2" style="text-align: center;"><html:submit styleClass="bigBtn" value="登录" />
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</div>
	<div class="mainContentFrame">
		<img src="./images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>
</div>
<jsp:include page="../bottom.jsp" flush="true"></jsp:include>