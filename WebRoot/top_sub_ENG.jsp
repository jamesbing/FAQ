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
<title>Detail of Reply</title>
<link href="/FAQ/css/commCss_01.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/jquery-ui-1.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/index4.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/commCss_02.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/commCss_04.css" rel="stylesheet" type="text/css">
<link href="/FAQ/css/validate.css" rel="stylesheet" type="text/css" />
<link href="/FAQ/css/ui_comm.css" rel="stylesheet" type="text/css" />
<!-- <link href="/FAQ/css/template_ja.css" rel="stylesheet" type="text/css" />  -->
<!--  -->
<link type="text/css" href="/FAQ/topcss/system.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/general.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/addons.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/layout.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/template.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/usertools.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/css3.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/mega.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/typo.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/layout_ja.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/template_ja.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/css3_ja.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/mega_ja.css" rel="stylesheet"></link>

<link type="text/css" href="/FAQ/topcss/main.css" rel="stylesheet"></link>
<!--  -->

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
						<c:when test="${personInfo == null }"> You need to <a
								href='<html:rewrite action="/person?action=initLogin_ENG"/>'>Login</a> | <a
								href='<html:rewrite action="/person?action=initAdd_ENG"/>'>Register</a>
						</c:when>
						<c:otherwise>
						Welcome, <a
								href='<html:rewrite action="/person?action=view_ENG" />&id=${personInfo.id}'>${personInfo.account}</a> | <a
								href='<html:rewrite action="/person?action=logout"/>'>Logout</a>
						</c:otherwise>
					</c:choose> 
					<a href='<html:rewrite action="/question?action=initList" />'>中文</a>
					</span>
			</div>

		</div>
	</div>
	<!-- ************************************************************************************ -->
	<!-- <div id="mainNavBox">
		
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
	 -->
	 
	 <div id="ja-mainnav" class="wrap">

    <div class="main">
        <div class="main-inner1 clearfix">
            <div id="ja-megamenu" class="ja-megamenu clearfix">
                <ul class="megamenu level0">
						<li class="mega">
							<a id="T0101224" class="mega" title="Notice"
							href="/FAQ/youknow.jsp"> <span class="menu-title">Notice
							</span> </a></li>

						<li class="mega">
							<a id="T0101225" class="mega" title="Chat Reference" href="/FAQ/sszx.jsp"> 
								<span class="menu-title"> Chat Reference </span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101226" class="mega" title="Form Reference" href="<html:rewrite action="/question?action=initAdd_ENG" />"> 
								<span class="menu-title"> Form Reference </span> 
							</a>
						</li>
						
						
						<li class="mega">
							<a id="T0101228" class="mega" title="Question Search" href="<html:rewrite action="/question?action=initList_ENG" />"> 
								<span class="menu-title">Question Search</span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101229" class="mega" title="Popular Questions" href="<html:rewrite action="/question?action=initPopular_ENG" />"> 
								<span class="Popular Questions"> Popular Questions </span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101230" class="mega" title="Experts' File" href="<html:rewrite action="/person?action=initExpert_ENG" />"> 
								<span class="menu-title"> Experts' File </span> 
							</a>
						</li>
<!--  因为默认外国用户没有管理员权限，因此不显示
						<c:if test="${personInfo.isAdmin() }">
							<li class="mega">
								<a id="T0101231" class="mega" title="用户管理" href="<html:rewrite action="/person?action=initCheck" />"> 
									<span class="menu-title"> 用户管理 </span> 
								</a>
							</li>
							<li class="mega">
								<a id="T0101232" class="mega" title="回复审核" href="<html:rewrite action="/reply?action=initUnCheckedReply" />"> 
									<span class="menu-title"> 回复审核</span> 
								</a>
							</li>
						</c:if>
-->
						<c:if test="${personInfo.isExpert() }">
							<li class="mega">
							<a id="T0101233" class="mega" title="Unsolved Questions" href="<html:rewrite action="/question?action=unsolvedQuestion" />"> 
							<span class="menu-title"> Unsolved Questions</span> </a></li>
							<li class="mega">
							<a id="T0101234" class="mega" title="Modify My Answers" href="<html:rewrite action="/reply?action=showMyAnswer" />"> 
							<span class="menu-title"> Modify My Answers</span> </a></li>
						</c:if>
						
			<!-- 同上			
						<c:if test="${personInfo.isAdmin() }">
							<li class="mega">
							<a id="T0101233" class="mega" title="问题设置/未解答问题" href="<html:rewrite action="/question?action=unsolvedAndManagement" />"> 
							<span class="menu-title"> 问题设置/未解答问题 </span> </a></li>
						</c:if>
-->
					</ul>
      	 	</div>
   	 	</div>
   	 </div>
   	 </div>