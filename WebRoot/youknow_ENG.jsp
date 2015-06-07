<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="top_ENG.jsp" flush="true" />

<script type="text/javascript">
	addCurrentTagClass('T0101224');
</script>

<div id="mainContentPos">
	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" border="0" height="16" width="970" />
	</div>
	<!-- 首页内容 -->
	<div id="mainContent">
		<div id="WelcomeWordBox">
			<div class="indexPicBox">
				<img src="images/topPic.gif" border="0" height="123" width="108" />
			</div>
			<div class="indexWordBox">
				<p>中国科学院数据应用环境咨询服务系统欢迎您！</p>
				<p>
					您可以通过下表中的任一方式提出问题；您的问题可能在系统中已有答案，因此建议您在提问之前先登录“<a
						href='<html:rewrite action="/question?action=initPopular_ENG"/>'>常见问题</a>”和“<a
						href='<html:rewrite action="/question?action=initList_ENG" />'>问题检索</a>”页面进行查询。通过“<a
						href='<html:rewrite action="/person?action=initExpert_ENG"/>'>专家档案</a>”，可以了解到每个咨询员的信息。
				</p>
				<p>

					请阅读《<a href="">用户须知</a>》，了解我们的咨询服务范围、服务时间等；阅读《<a href=""
						target="_blank">用户使用手册</a>》，了解如何使用该咨询服务系统为用户提供的各项功能。



				</p>
				<hr size="1" width="100%">
				<br />
				<table>
					<tbody>
						<tr>
							<td width="50"><a href=""> <img src="images/imgChat.gif"
									border="0" height="32" width="32"> </a></td>
							<td width="80"><a href="sszx_ENG.jsp">实时咨询</a></td>
							<td>采用在线对话方式与咨询员实时交流，获得对咨询问题的解答。</td>
						</tr>
						<tr>
							<td><a href=""><img src="images/imgClock.gif" border="0"
									height="32" width="32"> </a></td>
							<td><a href="<html:rewrite action="/question?action=initAdd_ENG" />">表单咨询</a></td>
							<td>
								将您的问题在线填写在问题描述表单中并提交，一般情况下咨询员将在三个工作日内作出答复，并将答复发送到您的电子邮箱。</td>
						</tr>
						<tr>
							<td><img src="images/imgPhone.gif" border="0" height="32"
								width="32"></td>
							<td>电话咨询</td>
							<td>可通过电话方式向咨询员提出问题并即时得到问题答案。可在“<a
								href='<html:rewrite action="/person?action=initExpert_ENG"/>'>专家档案</a>”栏目中查看到各个咨询员的电话号码及能解答问题的范围。
							</td>
						</tr>
						<tr>
							<td><img src="images/imgMail.gif" border="0" height="32"
								width="32"></td>
							<td>邮件咨询</td>
							<td>可通过E-mail向咨询员提出问题并得到问题答案。可在“<a
								href='<html:rewrite action="/person?action=initExpert_ENG"/>'>专家档案</a>”栏目中查看到各个咨询员的E-mail地址及能解答问题的范围。一般情况下，咨询员将在三个工作日内对问题给出答复。
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 首页内容 -->
	</div>
	<div class="mainContentFrame">
		<img src="images/imgFrame02.gif" border="0" height="12" width="970">
	</div>
</div>

<jsp:include page="bottom.jsp" flush="true" />
