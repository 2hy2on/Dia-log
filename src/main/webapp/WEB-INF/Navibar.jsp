<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/navi.css" rel="stylesheet"
	type="text/css">
<style>
@font-face {
	font-family: 'LINESeedKR-Bd';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2')
		format('woff2');
	font-weight: 700;
	font-style: normal;
}

body {
	font-family: 'LINESeedKR-Bd';
	text-decoration: none;
}

* {
	padding: 0;
	margin: 0
}

ul, ol {
	list-style: none
}

a {
	text-decoration: none;
	color: #fff;
	font-size: 15px
}

nav {
	width: 80%;
	overflow: hidden;
	height: 80px;
	background-color: #79A3B1;
}

#nav3 {
	width: 100%;
	position: relative;
	text-align: center;
}

#nav3>a {
	line-height: 80px;
	display: block;
	font-size: 30px;
	font-weight: 900;
	position: absolute;
	left: 30px;
}

#nav3>ul {
	display: inline-block;
}

#nav3>ul li {
	float: left;
	line-height: 80px;
	padding: 0 10px;
}

#nav3>a:hover {
	border-radius: 4px;
	backgroud-color: #456268;
}
</style>
</head>
<body>
	<nav id="nav3">
		<a href="#">Dia-log</a>
		<ul>
			<li><a href="#">홈</a></li>
			<li><a href="#">다이어리</a>
				<ul>
					<li><a href="#">리뷰</a></li>
					<li><a href="#">친구</a></li>
					<li><a href="#">통계</a></li>
				</ul></li>
			<li><a href="#">OTT 게시판</a></li>
			<li><a href="#">마이페이지</a>
				<ul>
					<li><a href="#">내 정보 수정</a></li>
					<li><a href="#">Q & A</a></li>
				</ul>
			</li>
		</ul>
	</nav>
</body>
</html>