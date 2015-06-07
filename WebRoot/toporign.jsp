<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache">
<title>${requestScope[requestScope['org.apache.struts.action.mapping.instance'].attribute].title
	}</title>
<link href="/FAQ/css/commCss_01.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/jquery-ui-1.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/index4.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/commCss_02.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/commCss_04.css" rel="stylesheet" type="text/css">
<link href="/FAQ/css/validate.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/ui_comm.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/template_ja.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/FAQ/js/jquery_002.js"></script>

<script type="text/javascript" src="/FAQ/js/jquery-ui.js"></script>

<script type="text/javascript" src="/FAQ/js/bgiframe.js"></script>

<script type="text/javascript" src="/FAQ/js/jquery.js"></script>

<script type="text/javascript" src="/FAQ/js/changePage.js"></script>

<script type="text/javascript" src="/FAQ/js/comm.js"></script>

</head>

<body onload="getNowTime()">

	<div id="topNav">
		<div id="logoWord">
			<div><img alt="no" src="./images/logo.gif" /></div>
		</div>

		<div id="topRightTop">
			<div id="topClockBox">
				<img src="/FAQ/images/iconClock.png" class="topimgpos" border="0"
					height="16" width="16" />
				<div id="time" class="topTextPos"></div>
			</div>
		</div>
		<div id="topRightBottom">

			<div id="systemDoor">
				<span class="topTextPos" style="padding-top:1px"> <c:choose>
						<c:when test="${personInfo == null }"> 您尚未 <a
								href='<html:rewrite action="/person?action=initLogin"/>'>登录</a> | <a
								href='<html:rewrite action="/person?action=initAdd"/>'>注册</a>
						</c:when>
						<c:otherwise>
						欢迎您, <a
								href='<html:rewrite action="/person?action=view" />&id=${personInfo.id}'>${personInfo.account}</a> | <a
								href='<html:rewrite action="/person?action=logout"/>'>注销</a>
						</c:otherwise>
					</c:choose> </span>
			</div>

		</div>
	</div>
	<!-- ************************************************************************************ -->
	<div id="mainNavBox">
	<!-- 
		<div id="navIconBox">
			<div class="iconPos">
				<a href="/FAQ/index.jsp" onmouseover="addTitleClass('T0101224')"
					onmouseout="removeTitleClass('T0101224')"> <img
					src="/FAQ/images/iconHome.gif" border="0" height="44" width="82" />
				</a>
			</div>

			<div class="iconPos">
				<a href="/FAQ/sszx.jsp" onmouseover="addTitleClass('T0101225')"
					onmouseout="removeTitleClass('T0101225')"> <img
					src="/FAQ/images/iconChat.gif" border="0" height="44" width="82" />
				</a>
			</div>

			<div class="iconPos">
				<a href='<html:rewrite action="/question?action=initAdd" />'
					onmouseover="addTitleClass('T0101226')"
					onmouseout="removeTitleClass('T0101226')"> <img
					src="/FAQ/images/iconClock.gif" border="0" height="44" width="82" />
				</a>
			</div>

			<div class="iconPos">
				<a href='<html:rewrite action="/question?action=initList" />'
					onmouseover="addTitleClass('T0101228')"
					onmouseout="removeTitleClass('T0101228')"> <img
					src="/FAQ/images/iconSearch.gif" border="0" height="44" width="82" />
				</a>
			</div>
			<div class="iconPos">
				<a href='<html:rewrite action="/question?action=initPopular"/>'
					onmouseover="addTitleClass('T0101229')"
					onmouseout="removeTitleClass('T0101229')"> <img
					src="/FAQ/images/iconFAQ.gif" border="0" height="44" width="82" />
				</a>
			</div>
			<div class="iconPos">
				<a href='<html:rewrite action="/person?action=initExpert"/>'
					onmouseover="addTitleClass('T0101230')"
					onmouseout="removeTitleClass('T0101230')"> <img
					src="/FAQ/images/iconExpert.gif" border="0" height="44" width="82" />
				</a>
			</div>
		</div> -->
		<div id="navWordBox">
			<a id="T0101224" href="/FAQ/youknow.jsp">用户须知</a> <a class=""
				id="T0101225" href="/FAQ/sszx.jsp">实时咨询</a> <a class=""
				id="T0101226"
				href='<html:rewrite action="/question?action=initAdd" />'>表单咨询</a> <a
				class="" id="T0101228"
				href='<html:rewrite action="/question?action=initList" />'>问题检索</a>
			<a class="" id="T0101229"
				href='<html:rewrite action="/question?action=initPopular"/>'>常见问题</a>
			<a class="" id="T0101230"
				href='<html:rewrite action="/person?action=initExpert"/>'>专家档案</a>
			<c:if test="${personInfo.isAdmin() }">
				<a class="" id="T0101231"
					href='<html:rewrite action="/person?action=initCheck"/>'>用户管理</a>
			</c:if>
		</div>
	</div>