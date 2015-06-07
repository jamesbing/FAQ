<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top.jsp" flush="true"></jsp:include>

<div class="contentwrap z">
	<c:forEach var="category" items="${lstCategory }">
		<div class="t">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h" colspan="6">
						
					</th>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>

<jsp:include page="../bottom.jsp" flush="true"></jsp:include>