<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<jsp:include page="/top.jsp"></jsp:include>

<div id="mainContentPos2">	
	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>

	<div id="mainContent">
	<br />
	<br />
	<br />
	<br />
	${message}
	<br />
	<br />
	<br />
	<br />
	</div>

	<div class="mainContentFrame">
		<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>	

<jsp:include page="/bottom.jsp"></jsp:include>
