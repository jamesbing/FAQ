<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.kuroko.faq.util.PersonInfo"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../top_ENG.jsp" flush="true" />

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
       alert("Please make sure that the file size is less than 2MB");
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
		<div id="breadroadbox">Form Reference</div>

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
					style="float: left; margin-right: 5px;" />Notice：Columns marked by the red star<img
					src="images/redstar.gif" width="12" height="12" border="0" />are essential,please make sure they are properly filled.<br />
					Please express your question properly and clearly,and please give us as much as possible information about what you already know.This system can only offer you the consult service.Thank you.
			</div>

			<html:form action="/question" method="post" enctype="multipart/form-data">
				<table width=850>
					<html:hidden property="action" value="add_ENG" />
					<tr class="bg4">
						<td width="150">User Name：</td>
						<td>
							<table>
								<tr>
									<td width=390><input type="text"
										name="cnug07DataDto.userMail" size="25" maxlength="50"
										value="${personInfo.account == null ? 'Please longin' : personInfo.account}"
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
						<td>Question Title：</td>
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
						<td>Question Description：</td>
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
						<td>Question Type：</td>
						<td>
							<table class="inputTableStyle">
								<tr>
									<td>Please select a proper type, choose a First Class, and then choose others.</td>
								</tr>
								<tr>
									<td><select name="question.qtype1" id="type1"
										onchange="getType('type2');">
											<option value="" />
											<c:if test="${personInfo.isAdmin()}">
												<option value="意见建议">Suggestions</option>
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
						<td>Every one can see me:</td>
						<td>

							<table class="inputTableStyle">
								<tr>
									<td width=390>
									<input type="radio" value="1" name="question.pub" checked="checked">Yes</input>
									<input type="radio" value="0" name="question.pub" >No</input>
									</td>
									<td> If you choose "No", then your question will be an anonymous question.</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="bg2">
						<td>Remark：</td>
						<td width=390><html:textarea property="question.remarks"
											cols="51" rows="4"></html:textarea>
						<!-- <td><html:textarea property="question.remarks"></html:textarea>  -->
						</td>
					</tr>


					<tr class="bg4">
						<td>Select an expert：</td>
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
										<td>Your question will send to the expert you choose, and if you don't select any expert, the system will determine an expert to answer your question.</td>
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
						<td>Upload attachment：</td>
						<td>
							<!-- //////////////////////////////////////////// -->
							<table>
								<tr>
									<!--<input type = "file" name = file onchange="fileChange(this)">-->
																		Please make sure that the file size is less than 2MB
									<input type="file" name="file" style="display:none" onchange="fileChange(this)">
												<input type="button" value="Choose an attachment" onclick="file.click();myText.value=file.value">
												<input type="text" name="myText" size="100">
									
									
								</tr>
							</table>
						</td>
					</tr>


					<tr class="bg3">
						<td>&nbsp;</td>
						<td><html:submit styleClass="bigBtn" value="Affirm&Submit"></html:submit>
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
