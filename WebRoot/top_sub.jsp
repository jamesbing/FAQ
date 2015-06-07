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
<title>问题回复</title>
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
						<c:when test="${personInfo == null }"> 您尚未 <a
								href='<html:rewrite action="/person?action=initLogin"/>'>登录</a> | <a
								href='<html:rewrite action="/person?action=initAdd"/>'>注册</a>
						</c:when>
						<c:otherwise>
						欢迎您, <a
								href='<html:rewrite action="/person?action=view" />&id=${personInfo.id}'>${personInfo.account}</a> | <a
								href='<html:rewrite action="/person?action=logout"/>'>注销</a>
						</c:otherwise>
					</c:choose> 
					<c:if test="${personInfo==null ||personInfo.isNotAdmin() }">
					<a href='<html:rewrite action="/question?action=initList_ENG" />'>English</a>
					</c:if>
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
							<a id="T0101224" class="mega" title="用户须知"
							href="/FAQ/youknow.jsp"> <span class="menu-title">用户须知
							</span> </a></li>

						<li class="mega">
							<a id="T0101225" class="mega" title="实时咨询" href="/FAQ/sszx.jsp"> 
								<span class="menu-title"> 实时咨询 </span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101226" class="mega" title="表单咨询" href="<html:rewrite action="/question?action=initAdd" />"> 
								<span class="menu-title"> 表单咨询 </span> 
							</a>
						</li>
						
						
						<li class="mega">
							<a id="T0101228" class="mega" title="问题检索" href="<html:rewrite action="/question?action=initList" />"> 
								<span class="menu-title">问题检索</span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101229" class="mega" title="常见问题" href="<html:rewrite action="/question?action=initPopular" />"> 
								<span class="menu-title"> 常见问题 </span> 
							</a>
						</li>
						
						<li class="mega">
							<a id="T0101230" class="mega" title="专家档案" href="<html:rewrite action="/person?action=initExpert" />"> 
								<span class="menu-title"> 专家档案 </span> 
							</a>
						</li>

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

						<c:if test="${personInfo.isExpert() }">
							<li class="mega">
							<a id="T0101233" class="mega" title="未解答问题" href="<html:rewrite action="/question?action=unsolvedQuestion" />"> 
							<span class="menu-title"> 未解答问题 </span> </a></li>
							<li class="mega">
							<a id="T0101234" class="mega" title="回复修改" href="<html:rewrite action="/reply?action=showMyAnswer" />"> 
							<span class="menu-title"> 回复修改</span> </a></li>
						</c:if>
						
						
						<c:if test="${personInfo.isAdmin() }">
							<li class="mega">
							<a id="T0101233" class="mega" title="未解答问题" href="<html:rewrite action="/question?action=unsolvedQuestion" />"> 
							<span class="menu-title">未解答问题 </span> </a></li>
					
					
							<li class="mega">
							<a id="T0101234" class="mega" title="设置" href="<html:rewrite action="/question?action=adminSet" />"> 
							<span class="menu-title"> 设置 </span> </a></li>
						</c:if>

					</ul>
      	 	</div>
   	 	</div>
   	 </div>
   	 </div>