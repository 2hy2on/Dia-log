<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/navi.css' />"
	type="text/css">
</head>
<body>
	<nav id="nav3" class="navi">
		<a id="title" href="<c:url value='/contents/list'/>">Dia-log</a>
		<ul class="navi">
			<li><a href="<c:url value='/contents/list'/>">홈</a></li>
			<li><a href="<c:url value='/diary'/>">다이어리</a>
				<ul>
					<li><a href="<c:url value='/diary/filter'/>">리뷰</a></li>
					<li><a href="<c:url value='/friend'/>">친구</a></li>
					<li><a href="#">통계</a></li>
				</ul></li>
			<li><a href="<c:url value='/board'/>">OTT 게시판</a></li>
      <li><a href="<c:url value='/mypage'/>">마이페이지 </a>
				<ul>
					<li><a href="logoutAction.jsp">로그아웃</a></li>
				</ul></li>
		</ul>
	</nav>
</body>
</html>
