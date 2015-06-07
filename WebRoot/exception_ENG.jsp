<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="top_ENG.jsp" flush="true"></jsp:include>

<div id="main">

	<div class="t" style="margin-bottom:0px; border-bottom:0">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<th class="h"><strong class="fl w">${message} </strong>
				</th>
			</tr>
		</table>
	</div>
	<div class="t t2">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr class="tr1">
				<th>${ exception.message }<input type="button" value="返回首页"
					class="bigBtn" onclick="window.location.href=''/FAQ/index.jsp" />
				</th>
			</tr>
		</table>
	</div>

</div>
<jsp:include page="/bottom.jsp" flush="true"></jsp:include>