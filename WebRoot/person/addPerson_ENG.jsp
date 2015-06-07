<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top_ENG.jsp" flush="true"></jsp:include>

<script type="text/javascript">
	function viewType() {
		var o = document.getElementById('chkExpert');
		var d = document.getElementById('d-type');
		if (o.value = 'true') {
			d.style.display = "";
		} else {
			d.style.display = "none";
		}
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
<script type="text/javascript" src="/FAQ/person/ajax_person.js"></script>

<div id="mainContentPos2">
	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
	<div style=" background-color: #D7F1FF;text-align: center;">
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w">User Registration</strong> &nbsp; <span
						style="color:red;font-weight:bold;">${message} </span></th>
				</tr>
			</table>
		</div>
		<html:form action="/person" method="post">
			<html:hidden property="action" value="add_ENG" />
			<html:hidden property="person.power" />
			<div class="t t2">
				<table width="60%" cellspacing="0" cellpadding="10">
					<tr class="tr3">
						<td style="width:120px;">Account/User Name:</td>
						<td><html:text property="person.account"></html:text>
						</td>
					</tr>
					<tr class="tr3">
						<td style="width:120px;">Password:</td>
						<td><html:password property="person.password"></html:password>
						</td>
					</tr>

					<tr class="tr3">
						<td style="width:120px;">Confirm your password:</td>
						<td><html:password property="password"></html:password>
						</td>
					</tr>
					<tr class="tr3">
						<td style="width:120px;">Name:</td>
						<td><html:text property="person.name"></html:text>
						</td>
					</tr>
					<tr class="tr3">
						<td style="width:120px;">Sex:</td>
						<td><html:select property="person.sex">
								<html:option value="男">Male</html:option>
								<html:option value="女">Female</html:option>
							</html:select>
						</td>
					</tr>
					<tr class="tr3">
						<td style="width:120px;">Email:</td>
						<td><html:text property="person.email"></html:text>
						</td>
					</tr>
					<tr class="tr3">
						<td><input type="checkbox" name="isExpert" id="chkExpert"
							onclick="viewType()" /> I am EXPERT</td>
						<td><table class="inputTableStyle" id="d-type"
								style="display:none;">
								<tr>
									<td>Please select a proper type, choose a First Class, and then choose others.</td>
								</tr>
								<tr>
									<td><select name="type1" id="type1"
										onchange="getType('type2');">
											<option value="" />
											<c:forEach var="type" items="${qtype1}">
												<option value="${type.name}">${type.name}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><select name="type2" id="type2"
										onchange="getType('type3');">
											<option value="" />
									</select></td>
								</tr>
								<tr>
									<td><select name="type3" id="type3">
											<option value="" />
									</select></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr3">
						<td colspan="2" style="text-align: center;" ><html:submit styleClass="bigBtn" value="Register"></html:submit>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</div>
	</div>
	<div class="mainContentFrame">
		<img src="./images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>

<jsp:include page="../bottom.jsp" flush="true"></jsp:include>