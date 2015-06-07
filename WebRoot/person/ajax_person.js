function loadXMLDoc() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

function ajax_addAdmin(name) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var str = xmlhttp.responseText;
			alert(str);
		}
	};
	xmlhttp.open("GET", "/FAQ/person.do?action=addAdmin&id=" + name + "&t="
			+ new Date(), true);
	xmlhttp.send();
}

function ajax_removeAdmin(name) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var str = xmlhttp.responseText;
			alert(str);
		}
	};
	xmlhttp.open("GET", "/FAQ/person.do?action=removeAdmin&id=" + name + "&t="
			+ new Date(), true);
	xmlhttp.send();
}

function ajax_removePerson(id) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var str = xmlhttp.responseText;
			alert(str);
		}
	};
	xmlhttp.open("GET", "/FAQ/person.do?action=remove&id=" + id + "&t="
			+ new Date(), true);
	xmlhttp.send();
}

function ajax_checkPerson(id) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var str = xmlhttp.responseText;
			alert(str);
		}
	};
	xmlhttp.open("GET", "/FAQ/person.do?action=check&id=" + id + "&t="
			+ new Date(), true);
	xmlhttp.send();
}

function ajax_changeType(id, type1, type2, type3) {
	var xmlhttp = loadXMLDoc();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	};
	xmlhttp.open("GET", "/FAQ/person.do?action=changeType&type1=" + type1
			+ "&type2=" + type2 + "&type3=" + type3 + "&id=" + id + "&t="
			+ new Date(), true);
	xmlhttp.send();
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