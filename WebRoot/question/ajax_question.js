function loadXMLDoc() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

function getType2(type1) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("type2").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET", "/FAQ/question.do?action=getQtype2&qtype1=" + type1,
			true);
	xmlhttp.send();
}

function getType3(type2) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("type3").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET", "/FAQ/question.do?action=getQtype3&qtype2=" + type2,
			true);
	xmlhttp.send();
}

function getExpert() {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("expert").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET", "/FAQ/question.do?action=getExpert", true);
	xmlhttp.send();
}

function ajax_chooseExpert(eid, qid) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert('成功');
		}
	};
	xmlhttp.open("GET", "/FAQ/question.do?action=chooseExpert&eid=" + eid
			+ "&qid=" + qid, true);
	xmlhttp.send();
}
