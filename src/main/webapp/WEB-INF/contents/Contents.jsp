<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dia-log</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
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

  			<%@ include file="ContentsList.jsp"%>
			<!-- 
			<div class="main-view">
	          <ul>
	            <li><img src="../../css/contents/image/fubao.jpg" width="100%" /></li>
	            <li><img src="../../css/contents/image/fubao.jpg" width="100%" /></li>
	            <li><img src="../../css/contents/image/fubao.jpg" width="100%" /></li>
	          </ul>
       		</div> 
       		-->
	</div>
</body>
</html>


