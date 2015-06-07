<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top.jsp" flush="true" />

<script type="text/javascript" src="/FAQ/question/ajax_question.js"></script>
<script type="text/javascript">
	addCurrentTagClass('T0101226');
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

<!-- 导入jQuery库文件 -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js">
      </script>
    <!-- 执行上传文件操作的函数 -->
<script type="text/javascript">
    function fileChange(target) {
     var fileSize = 0;         
     if (isIE && !target.files) {     
       var filePath = target.value;     
       var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
       var file = fileSystem.GetFile (filePath);     
       fileSize = file.Size;    
     } else {    
      fileSize = target.files[0].size;     
      }   
      var size = fileSize / 1024;    
      if(size>2000){  
       alert("附件不能大于2M");
       target.value="";
       return
      }
    } 
      </script>
      

<div id="mainContentPos2">

	<div class="mainContentFrame">
		<img src="images/imgFrame01.gif" width="970" height="12" border="0" />
	</div>

	<div id="mainContent2">
		<!-- 主体内容，开始 -->
		<div id="breadroadbox">当前位置：表单咨询</div>

		<div class="t" style="margin-bottom:0px; border-bottom:0">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h"><strong class="fl w"></strong> &nbsp; <span
						style="color:red;font-weight:bold;">${message} </span>
					</th>
				</tr>
			</table>
		</div>
		<div id="ContentShowBox">
			<div class="noticeBox">
				<img src="images/iconAlarm.gif" width="32" height="26" border="0"
					style="float: left; margin-right: 5px;" />注意：表格中加星号<img
					src="images/redstar.gif" width="12" height="12" border="0" />的内容为必填项。<br />
				请清晰表达您的问题，并希望您在描述问题时提供尽可能多的已知信息。本系统只提供咨询服务，不提供其他服务。
			</div>

			<html:form action="/question" method="post" enctype="multipart/form-data">
				<table width=850>
					<html:hidden property="action" value="add" />
					<tr class="bg4">
						<td width="150">用户名：</td>
						<td>
							<table>
								<tr>
									<td width=390><input type="text"
										name="cnug07DataDto.userMail" size="25" maxlength="50"
										value="${personInfo.account == null ? '请先登录' : personInfo.account}"
										id="userMail" class="inputBoxWidth" disabled="disabled"
										style="height:22px;font-size: 14px !important;font-family:宋体;" />
									</td>
									<td width=16><img src="images/redstar.gif" width="12"
										height="12" border="0" />
									</td>
									<td><span id="cnug07_err" class="redWord"></span>
									</td>
								</tr>
							</table> <!-- //////////////////////////////////////////// --></td>
					</tr>

					<tr class="bg2">
						<td>问题标题：</td>
						<td>
							<!-- //////////////////////////////////////////// -->
							<table>
								<tr>
									<td width=390><html:text property="question.title"
											size="51" maxlength="100" value=""
											style="height:22px;font-size: 14px !important;font-family:宋体;"></html:text>
									</td>
									<td width=16><img src="images/redstar.gif" width="12"
										height="12" border="0" /></td>
									<td><span id="cnug07_err" class="redWord"></span></td>
								</tr>
							</table> <!-- //////////////////////////////////////////// --></td>
					</tr>
					<tr class="bg4">
						<td>问题描述：</td>
						<td>
							<table class="inputTableStyle">
								<tr>
									<td width=390><html:textarea property="question.content"
											cols="51" rows="5"></html:textarea>
									</td>
									<td width=16><img src="images/redstar.gif" width="12"
										height="12" border="0" /></td>
									<td><span id="cnug07_err" class="redWord"></span></td>
								</tr>
							</table> <!-- //////////////////////////////////////////// --></td>
					</tr>

					<tr class="bg2">
						<td>问题类别：</td>
						<td>
							<table class="inputTableStyle">
								<tr>
									<td>请选择一个合适的类，先在第一个框中选择一级大类，再选择第二、第三级分类</td>
								</tr>
								<tr>
									<td><select name="question.qtype1" id="type1"
										onchange="getType('type2');">
											<option value="" />
											<c:if test="${personInfo.isAdmin()}">
												<option value="意见建议">意见建议</option>
											</c:if>
											<c:forEach var="type" items="${qtype1}">
												<option value="${type.name}">${type.name}</option>
											</c:forEach>
									</select>
									</td>
								</tr>
								<tr>
									<td><select name="question.qtype2" id="type2"
										onchange="getType('type3');">
											<option value="" />
									</select>
									</td>
								</tr>
								<tr>
									<td><select name="question.qtype3" id="type3">
											<option value="" />
									</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr class="bg4">
						<td>是否公开本次咨询内容：</td>
						<td>

							<table class="inputTableStyle">
								<tr>
									<td width=390>
									<input type="radio" value="1" name="question.pub" checked="checked">公开</input>
									<input type="radio" value="0" name="question.pub" >不公开</input>
									</td>
									<td>选择“不公开”，您的咨询将以匿名方式公开</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="bg2">
						<td>备注：</td>
						<td width=390><html:textarea property="question.remarks"
											cols="51" rows="4"></html:textarea>
						<!-- <td><html:textarea property="question.remarks"></html:textarea>  -->
						</td>
					</tr>


					<tr class="bg4">
						<td>选择专家：</td>
						<td>
							<!-- //////////////////////////////////////////// -->
							<table>
								<tr>
									<c:if test="${null == expert }">
										<td width="390"><select name="question.expertId"
											id="expert" style="height:22px">
												<option value=""></option>
												<c:forEach items="${lstExpert}" var="_expert">
													<c:if test="${_expert.isOk()}">
														<option value="${_expert.id }">${_expert.name }</option>
													</c:if>
												</c:forEach>
										</select>
										</td>
										<td>您的问题将直接发送到您选择的专家手中;若不选择，将由系统决定发送到哪位专家手中。</td>
									</c:if>
									<c:if test="${null != expert }">
										<td width="390"><select name="question.expertId"
											id="expert" style="height:22px">
												<option value="${expert.id }">${expert.name }</option>
										</select>
										</td>
									</c:if>
								</tr>
							</table>
						</td>
					</tr>



					<tr class="bg2">
						<td>上传附件：</td>
						<td>
							<!-- //////////////////////////////////////////// -->
							<table>
								<tr>
									<input type = "file" name = file onchange="fileChange(this)">
									请您不要上传超过2M文件！</input>
								</tr>
							</table>
						</td>
					</tr>


					<tr class="bg3">
						<td>&nbsp;</td>
						<td><html:submit styleClass="bigBtn" value="确认并提交"></html:submit>
					</tr>

				</table>
			</html:form>

		</div>

		<div class="mainContentFrame">
			<img src="images/imgFrame02.gif" width="970" height="12" border="0" />
		</div>
	</div>



</div>

<jsp:include page="../bottom.jsp" flush="true" />
