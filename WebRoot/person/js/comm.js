/*
 * -----------------------------------------------------------
 *
 * 文件名:comm.js
 *
 * 说明:本js用于画面的共同项目
 * 
 * 创建时间：2010-01-25 Zhao Xiaoshuo
 * 
 *-------------------------------------------------------------
 */

//**********************************
//启动时间表示
//**********************************
function getNowTime() {	
	var today = new Date();
	var nowTime = '当前时间：';
	nowTime += today.getFullYear() + '年';
	nowTime += checkTime(today.getMonth() + 1) + '月';
	nowTime += checkTime(today.getDate()) + '日';

//	var nowTime = commDateLab1;
//	nowTime += today.getFullYear() + commDateLab2;
//	nowTime += checkTime(today.getMonth() + 1) + commDateLab3;
//	nowTime += checkTime(today.getDate()) + commDateLab4;
	nowTime += '&nbsp;';

	var weekday = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
//	var weekday = new Array(commDateWeek7, commDateWeek1, commDateWeek2, commDateWeek3, commDateWeek4, commDateWeek5, commDateWeek6);
	nowTime += weekday[today.getDay()];
	nowTime += '&nbsp;';

	nowTime += checkTime(today.getHours()) + ':';
	nowTime += checkTime(today.getMinutes()) + ':';
	nowTime += checkTime(today.getSeconds());
	if (commDateLab1 != "") {
		$("#time").html(nowTime);
	}

	setTimeout('getNowTime()', 1000);
}

function initBuildingInfo() {
	var doc = "<div id='buildingInfo' style='display: none'>";
	doc = doc +	"	<div id='buildingInfoMsg' style='margin-top: 35px; width: 290px;'>";
	doc = doc +	"此功能还在建设中，尚不能使用。";
	doc = doc +	"</div>";
	doc = doc +	"<div style='margin-top: 20px;'>";
	doc = doc +	"<input type='button' onclick='closeBuildingInfo();' value='关闭' class='bigBtn' />";
	doc = doc +	"</div>";
	doc = doc +	"</div>";
	return doc;
}

//**********************************
//format时间格式
//**********************************
function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}

function showBuildingInfo() {
	
	var tmp =  initBuildingInfo();
	$("body").append(tmp);
	
	$("#buildingInfo").dialog('open');
	$("#buildingInfo").dialog( {
	bgiframe : true,
	resizable : false,
	width : 320,
	modal : true,
	overlay : {
		backgroundColor : '#000',
		opacity : 0.5
	}
	});
	$("#buildingInfo").parent().find("a").remove();
}

function closeBuildingInfo() {
	$("#buildingInfo").dialog('close');
	$("#buildingInfo").remove();
}

//**********************************
//退出系统
//**********************************
function exitSystem() {
	if (window.opener == null) {
		window.location.href = "closeSysAction.do";
	} else {
		self.close();
	}
//	window.parent = null;
//	document.write("");
//	window.opener=self;
//	window.close();
}

//**********************************
//处理过程中弹出窗口
//**********************************
function showCommDialog01(msgString) {
	
	var htmlStr = "<div id='commDialog01' style='display: none'>";
	htmlStr = htmlStr +"<div id='commDialog01Msg' style='margin-top: 35px; width: 290px;'>";
	htmlStr = htmlStr +"</div>";
	htmlStr = htmlStr +"<div style='margin-top: 20px;'>";
	htmlStr = htmlStr +"</div>";
	htmlStr = htmlStr +"</div>";
	$("body").append(htmlStr);
	
	$("#commDialog01Msg").text(msgString);
	
	$("#commDialog01").dialog('open');
	$("#commDialog01").dialog( {
		bgiframe : true,
		resizable : false,
		width : 320,
		modal : true,
		overlay : {
			backgroundColor : '#000',
			opacity : 0.5
		}
	});
	$("#commDialog01").parent().find("a").remove();
}

function closeCommDialog01() {
	$("#commDialog01").dialog('close');
	$("#commDialog01").remove();
}


var commDateLab1 = "";
var commDateLab2 = "";
var commDateLab3 = "";
var commDateLab4 = "";
var commDateWeek7 = "";
var commDateWeek1 = "";
var commDateWeek2 = "";
var commDateWeek3 = "";
var commDateWeek4 = "";
var commDateWeek5 = "";
var commDateWeek6 = "";
var changeRoleMsg = "";
$(function() {
	
	window.history.forward(1);

	commDateLab1 = $("#dateLab1").val();
	commDateLab2 = $("#dateLab2").val();
	commDateLab3 = $("#dateLab3").val();
	commDateLab4 = $("#dateLab4").val();
	commDateWeek7 = $("#dateWeek7").val();
	commDateWeek1 = $("#dateWeek1").val();
	commDateWeek2 = $("#dateWeek2").val();
	commDateWeek3 = $("#dateWeek3").val();
	commDateWeek4 = $("#dateWeek4").val();
	commDateWeek5 = $("#dateWeek5").val();
	commDateWeek6 = $("#dateWeek6").val();
	changeRoleMsg = $("#changeRoleMsg").val();
		
//	if (eval($("#changeRoleFlg").val())) {
//		$("#userNameBox").find("span").append("<a id='roleIDChange' style='padding-top:5px;padding-left:4px'>" + changeRoleMsg + "</a>");
//		$("#roleIDChange").css({'cursor':'pointer'});
//		$("#roleIDChange").mouseover(function () {
//			var doc = "<div id='changeRoleInfo' style='top:"+(parseInt($("#roleIDChange").offset().top,10)+25)+"px; left:"+(parseInt($("#roleIDChange").offset().left,10)+17)+"px; position:absolute;  border-width:1px; border-color:#000000; border-style:solid; padding:1px 5px; font-size:12px; background-color:#FFFFCC;'>"+changeRoleMsg+"</div>";
//			$("body").append(doc);
//		});
//		$("#roleIDChange").mouseout(function () {
//			$("#changeRoleInfo").remove();
//		});
//		$("#roleIDChange").click(function (){
//			window.location.href="index601Action.do";
//		});
//		$("#userNameBox2").css({'cursor':'pointer'});
//		$("#userNameBox2").mouseover(function () {
//			var doc = "<div id='changeRoleInfo' style='top:"+(parseInt($("#userNameBox2").offset().top,10)+25)+"px; left:"+(parseInt($("#userNameBox2").offset().left,10)+17)+"px; position:absolute;  border-width:1px; border-color:#000000; border-style:solid; padding:1px 5px; font-size:12px; background-color:#FFFFCC;'>"+changeRoleMsg+"</div>";
//			$("body").append(doc);
//		});
//		$("#userNameBox2").mouseout(function () {
//			$("#changeRoleInfo").remove();
//		});
//		$("#userNameBox2").click(function (){
//			window.location.href="index601Action.do";
//		});
//	}
	
	$("#systemDoor").css({'cursor':'pointer'});
//	liuyangke add at 2010.12.10 10:58 
	$("#exit").click(function (){
		var roleName = $.trim($("#userNameBox").find("span").html());
		if (roleName.length <= 5) {
			var customId = "";
			var hiddenCustomId = $(":hidden[name='customId']");
			if (hiddenCustomId.html() != null) {
				customId = hiddenCustomId.val();
			}
			window.location.href = "index601Action.do?customId="+customId;
		} else  {
			exitSystem();
		}
	});	
//	liuyangke add at 2010.12.14 11:35 start
	$("#exitDoor").click(function(){
		exitSystem();		
	});
//	liuyangke add at 2010.12.14 11:35 end
});


//**********************************
//鼠标悬停效果
//参数1：标题ID
//**********************************
function addTitleClass(titleId) {
$("#"+titleId).addClass("titleOver");
};

//**********************************
//鼠标移出效果
//参数1：标题ID
//**********************************
function removeTitleClass(titleId) {
$("#"+titleId).removeClass("titleOver");
};


//**********************************
//显示咨询员的实时咨询页面
//**********************************
function showCNUG05Window() {
	
	var htmlStr = "<div id='cnug05Dialog' style='display: none'>";
	htmlStr = htmlStr +	"<div id='cnug05DialogMsg' style='margin-top: 35px; width: 290px;'>";
	htmlStr = htmlStr +	"</div>";
	htmlStr = htmlStr +	"<div style='margin-top: 20px;'>";
	htmlStr = htmlStr +	"<input type='button' onclick='closeCnug05Dialog();' value='确定' class='bigBtn' />";
	htmlStr = htmlStr +	"</div>";
	htmlStr = htmlStr +	"</div>";
	$("body").append(htmlStr);
	
	// 检查的当前用户是能进行实时咨询
	$.ajax({
		type:"post",
		url:"cnug0501Action.do",
		async: false,
		dataType:"json",
		success:function(data, textStatus)
				{
					var checkInfo = eval("("+data+")");
					if ("0" != checkInfo.errId) {
						$("#cnug05DialogMsg").text(checkInfo.errMessage);
						$("#cnug05Dialog").dialog('open');
						$("#cnug05Dialog").dialog( {
							bgiframe : true,
							resizable : false,
							width : 320,
							modal : true,
							overlay : {
								backgroundColor : '#000',
								opacity : 0.5
							},
							close:function(){ closeCnug05Dialog();}
						});
					} else {
						var retObj = window.open("cnug0502Action.do","_blank","toolbar=no,directories=no,menubar=no,scrollbars=yes,width=850px,height=650px"); 
					}
				},
		error: function(data, textStatus){
					// 不处理
				}
	});
};

function closeCnug05Dialog() {
	$("#cnug05Dialog").hide();
	$("#cnug05Dialog").remove();
}
//**********************************
//检查当前浏览器
//@author sun yu
//@return flg 结果:IE, OPERA, KHTML, MOZILLA, NETSCAPE
//**********************************
function detectBrowser(){
	
	var sUserAgent = navigator.userAgent;
	var fAppVersion = parseFloat(navigator.appVersion);

	var isOpera = sUserAgent.indexOf("Opera") > -1;
	if(isOpera){
		return "OPERA";
	}
	
	var isKHTML = sUserAgent.indexOf("KHTML") > -1 || sUserAgent.indexOf("Konqueror") > -1 || sUserAgent.indexOf("AppleWebKit") > -1;
	if(isKHTML){
		return "KHTML";
	}
	
	var isIE = sUserAgent.indexOf("compatible") > -1 && sUserAgent.indexOf("MSIE") > -1 && !isOpera;
	if(isIE){
		return "IE";
	}
	
	var isMoz = sUserAgent.indexOf("Gecko") > -1 && !isKHTML;
	if(isMoz){
		return "MOZILLA";
	}
	
	var isNS4 = !isIE && !isOpera && !isMoz && !isKHTMl && (sUserAgent.indexOf("Mozilla") == 0) && (navigator.appName == "Netscape") && (fAppVersion >= 4.0 && fAppVersion < 5.0);
	if(isNS4){
		return "NETSCAPE";
	}
}