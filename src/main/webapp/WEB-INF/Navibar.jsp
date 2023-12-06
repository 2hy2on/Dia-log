<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link href="../css/navi.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<nav id="nav3">
		<a href="<c:url value='/contents/list'/>">Dia-log</a>
		<ul>
			<li><a href="<c:url value='/contents/list'/>">홈</a></li>
			<li><a href="<c:url value='/diary'/>">다이어리</a>
				<ul>
					<li><a href="#">리뷰</a></li>
					<li><a href="#">친구</a></li>
					<li><a href="#">통계</a></li>
				</ul></li>
			<li><a href="../board.jsp">OTT 게시판</a></li>
			<li><a href="../login.jsp">로그인 </a>
				<ul>
					<li><a href="Mypage.jsp">내 정보 수정</a></li>
					<li><a href="logoutAction.jsp">로그아웃</a></li>
				</ul></li>
		</ul>
	</nav>
</body>
</html>
