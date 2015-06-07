<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>

<jsp:include page="/top.jsp"></jsp:include>

<script type="text/javascript">
	addCurrentTagClass('T0101225');
</script>

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>

	<div id="mainContent">
		<!-- 主体内容，开始 -->
		<div id="breadroadbox">当前位置：实时咨询</div>

		<div id="ContentShowBox">
			<div class="noticeBox" align="left">
				<img src="images/iconAlarm.gif" width="32" height="26" border="0"
					/ style="float: left; margin-right: 5px;" /> 注意：表格中加星号 <img
					src="images/redstar.gif" width="12" height="12" border="0" />
				的内容为必填项。 <br />
				您现在的身份是访客，请您登录本系统。如果您还不是本系统的注册用户，请您进行注册，以方便我们更好的为您服务。如果您确定继续以访客的身份进行本次咨询，请填写以下信息。
			</div>

			<form id="cnug04_form" name="cnug04_form" action="" method="post">


				<input type="hidden" id="email" name="email" /> <input
					type="hidden" id="name" name="name" /> <input type="hidden"
					id="customId" name="customId" value="" />

				<table width=850>
					<tr class="bg2">
						<td width="150">电子邮箱：</td>
						<td>
							<table>
								<tr>
									<td width=390><input type="text" name="cnug04Dto.email"
										size="25" maxlength="50" value="" id="cnugEmail"
										class="inputBoxWidth"
										style="height:18px;font-size: 14px !important;font-family:宋体;" />
									</td>
									<td width=16><img src="images/redstar.gif" width="12"
										height="12" border="0" />
									</td>
									<td><span id="cnug04_err" class="redWord"></span></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr class="bg2">
						<td>姓名：</td>
						<td>
							<table>
								<tr>
									<td width=390><input type="text" name="cnug04Dto.name"
										size="25" maxlength="50" value="" id="cnugName"
										class="inputBoxWidth"
										style="height:18px;font-size: 14px !important;font-family:宋体;" />
									</td>
									<td width=16><img src="images/redstar.gif" width="12"
										height="12" border="0" />
									</td>
									<td><span id="cnug04_err" class="redWord"></span></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr class="bg3">
						<td>&nbsp;</td>
						<td><input id="submitId" type="button" class="bigBtn"
							value="确认并提交" onclick="submitRealconsult();"></input>&nbsp; <input
							id="resetId" type="button" class="bigBtn" value="重置"
							onclick="clearRealconsult();"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	</div>
	<div class="mainContentFrame">
	<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
	</div>
</div>

<jsp:include page="/bottom.jsp"></jsp:include>