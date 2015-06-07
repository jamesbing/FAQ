<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.bean.Question"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../top.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101234');
</script>
<script type="text/javascript">
	function viewPerson() {
		var o = document.getElementById('prefixID');
		window.location.href = '/FAQ/question.do?action=setPrefix&preString=' + o.value;

	}
	function viewTime() {
		var elementTxt = document.getElementById("autoTime").value;

		//应用parseInt函数，将字符串（string类型）提取数字
		if (parseInt(elementTxt) < 0 || parseInt(elementTxt) > 100) {
			alert("请输入一个0-100间的数字作为自动通过审核的时限，即某个回复在该段时间以后仍未被管理员操作，系统将自动通过本条回复。");
			return false;
		}
		window.location.href = '/FAQ/question.do?action=setAutoPermitReply&autoTime='
				+ elementTxt;
	}
	
	function viewMailAdr(){
		var o = document.getElementById('publicMailAdr');
		window.location.href = '/FAQ/question.do?action=setEmailAdr&mailAdr='
				+ o.value;
	}
	
	function viewMailPassword(){
		var o = document.getElementById('publicMailPsw');
		window.location.href = '/FAQ/question.do?action=setEmailPsw&mailPsw='
				+ o.value;
	}
	
	
	function viewMailHost(){
		var o = document.getElementById('publicMailHost');
		window.location.href = '/FAQ/question.do?action=setEmailHost&mailHost='
				+ o.value;
	}
	
	
	
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="./images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>
	<div id="mainContent2">
		<div id="breadroadbox">当前位置：管理员设置</div>
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class='bg2'>
					<td class="tdbg1">设置问题编号<br/>前缀：</td>
					<td><input type="text" id="prefixID" /> <input
						type="button" class="bigBtn" value="设置" onclick="viewPerson();" />
					</td>
				</tr>
			</table>
		</div>
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class='bg2'>
					<td class="tdbg1">设置通过审核<br/>时限：</td>
					<td><input type="text" id="autoTime" /> <input
						type="button" class="bigBtn" value="设置" onclick="viewTime();" />（小时）请输入0-100间的数字,设置为0表示不需要审核。从问题或者回复发出开始计时，如果到指定时间后管理员仍未作操作，则系统自动通过审核。
					</td>
				</tr>
			</table>
		</div>
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class='bg2'>
					<td class="tdbg1">设置公共邮箱<br/>服务器主机名：</td>
					<td><input type="text" id="publicMailHost" /> <input
						type="button" class="bigBtn" value="设置" onclick="viewMailHost();" />请设置提供公共邮箱服务的主机名，例如您使用的是126邮箱，则本栏应填写smtp.126.com，如果是系统自己的邮箱，则应该填写合适的邮件服务主机名。
					</td>
				</tr>
			</table>
		</div>
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class='bg2'>
					<td class="tdbg1">设置公共邮箱<br/>账号：</td>
					<td><input type="text" id="publicMailAdr" /> <input
						type="button" class="bigBtn" value="设置" onclick="viewMailAdr();" />请设置一个可用邮箱账号作为系统的官方邮箱账号，当有消息需要以系统身份自动发送给用户、专家、或者管理员时，由所设定的邮箱账号发送。该账号为系统唯一，任何管理员修改后生效。
					</td>
				</tr>
			</table>
		</div>
		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class='bg2'>
					<td class="tdbg1">设置公共邮箱<br/>密码：</td>
					<td><input type="text" id="publicMailPsw" /> <input
						type="button" class="bigBtn" value="设置" onclick="viewMailPassword();" />请设置系统官方邮箱账号对应的密码。请务必保证账号及密码正确、可用，以保证相关信息及时发送。
					</td>
				</tr>
			</table>
		</div>
		<div id="ContentShowBox">
			
		</div>

		<div class="mainContentFrame">
			<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
		</div>
	</div>
</div>

<jsp:include page="../bottom.jsp"></jsp:include>