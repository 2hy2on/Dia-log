<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="model.dto.contents.Contents"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dia-log</title>
<style>
@font-face {
	font-family: 'LINESeedKR-Bd';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2')
		format('woff2');
	font-weight: 700;
	font-style: normal;
}

* {
	font-family: 'LINESeedKR-Bd';
	text-decoration: none;
}
</style>
</head>
<body>
	<div>
		<%@ include file="../Navibar.jsp"%>
		<%@ include file="Search.jsp"%>
		<%-- <%
		if (!((String) request.getAttribute("from")).equals("search")) {
		%> --%>
		<%@ include file="ContentsHallOfFame.jsp"%>
		<%-- <%
		}
		%> --%>
		<%@ include file="ContentsList.jsp"%>
	</div>
</body>
</html>