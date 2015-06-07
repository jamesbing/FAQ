/*
 * -----------------------------------------------------------
 *
 * 文件名:changePage.js
 *
 * 说明:本js用于一览画面的翻页操作
 * 
 * 创建时间：2010-01-25 Zhao Xiaoshuo
 * 
 *-------------------------------------------------------------
 */
//**********************************
//翻页动作
//参数:formName 表单名
//参数:actionName Action名
//参数:beanName 页面信息保存bean
//参数:pageNumber 翻页页码
//**********************************
function changePage(formName, actionName, beanName, pageNumber) {
	var form = document.getElementById(formName);
	if (pageNumber < 1) {
		return;
	}
	// 判断form是否有此属性
	if (form.elements[beanName + ".nowPage"]) {
		form.elements[beanName + ".nowPage"].value = pageNumber;
	}
	form.setAttribute("action", actionName+".do");
	form.submit();
}

//**********************************
//向页面打印出分页导航栏
//参数:form 分页导航栏所在表单对象
//参数:page 当前所在页码
//参数:pageCount 总页数
//参数:totalCount 总记录数
//**********************************
function showPaginationNavigator(formName, actionName, beanName, page,
		pageCount, totalCount) {
	var form = document.getElementById(formName);
	var doc = "";
	if (typeof totalCount != "undefined") {
		doc = "<div style='pagesNum'>\u5171" + totalCount + "\u6761&nbsp;&nbsp;";
	}
	doc = doc + getPaginationNavigator(form, actionName,beanName, page, pageCount);
	if (pageCount > 0 && totalCount > 0) {
		document.write(doc);
	}
}

//**********************************
//分页导航栏
//参数:form 分页导航栏所在表单对象
//参数:actionName 翻页Action名
//参数:beanName 翻页信息保存bean
//参数:page 当前所在页码
//参数:pageCount 总页数
//**********************************
function getPaginationNavigator(form, actionName, beanName, page, pageCount) {
	var doc = "\u7b2c" + page + "/" + pageCount + "\u9875</div>";

	doc = doc + "<div class='controlRightPos'>";
	if (parseInt(page, 10) > 1) {
		doc = doc + "<a href='javascript:changePage(\"" + form.name + "\",\""
				+ actionName + "\",\"" + beanName
				+ "\", 1)'>\u9996\u9875</a>";
		doc = doc + "<a href='javascript:changePage(\"" + form.name + "\",\""
				+ actionName + "\",\"" + beanName + "\", " + (page - 1)
				+ ")'>\u4e0a\u4e00\u9875</a>";
	} else {
		doc = doc + "<div class='controlnone'>\u9996\u9875</div>";
		doc = doc + "<div class='controlnone'>\u4e0a\u4e00\u9875</div>";
	}
	var show_start_page = page - page % 5;// 起始页
	if (page % 5 == 0) {
		show_start_page -= 5;
	}
	for ( var i = show_start_page + 1; i <= show_start_page + 5; i++) {
		if (i > parseInt(pageCount, 10)) {
			break;
		}
		if (page % 5 != i % 5) {
			doc = doc + "<a href='javascript:changePage(\"" + form.name + "\",\""
					+ actionName + "\",\"" + beanName + "\", " + i + ")'>" + i
					+ "</a>";
		} else {
			doc = doc + "<div class='pageNow'>" + i + "</div>";
		}
	}
	
	if (parseInt(page, 10) < parseInt(pageCount, 10)) {
		doc = doc + "<a href='javascript:changePage(\"" + form.name + "\",\""
				+ actionName + "\",\"" + beanName + "\", " + (parseInt(page, 10) + 1)
				+ ")'>\u4e0b\u4e00\u9875</a>";
		doc = doc + "<a href='javascript:changePage(\"" + form.name + "\",\""
				+ actionName + "\",\"" + beanName + "\", " + pageCount
				+ ")'>\u672b\u9875</a>";
	} else {
		doc = doc + "<div class='controlnone'>\u4e0b\u4e00\u9875</div>";
		doc = doc + "<div class='controlnone'>\u672b\u9875</div>";
	}
	doc = doc + "</div>";
	doc = doc + "<input type='Hidden' name='" + beanName + ".nowPage'  value='"
			+ page + "'>";
	return doc;
}
