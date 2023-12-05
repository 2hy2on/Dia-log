<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link href="../../css/contents/search.css" rel="stylesheet"
	type="text/css"> -->
<style>
body {
	width: 100%;
	margin: 0;
}

.pull-right select {
	padding: 0 20px;
	height: 30px;
	background-color: #FCF8EC;
	color: #000;
	right: 30px;
	margin-top: 15px;
	margin-right: 15px;
	border: 1px solid #fff;
	border-radius: 30px;
	text-color: #000;
}

/* select:hover {
	border: 2px solid #000;
} */
.search {
	position: relative;
	width: 300px;
	margin-top: 20px;
}

input {
	width: 100%;
	border: 1px solid #bbb;
	border-radius: 15px;
	padding: 10px 12px;
	font-size: 14px;
	background-color: #FCF8EC;
	margin-bottom: 20px;
}

.search img {
	position: absolute;
	width: 17px;
	top: 10px;
	right: 12px;
	margin: 0;
}
</style>
</head>
<body>
	<div class="container" style="display: flex;">
		<div class="row" style="margin: auto;">
			<form method="post" name="search" action="#">
				<table class="pull-right">
					<tr>
						<td><select>
								<option value="0">선택</option>
								<option value="movie">영화</option>
								<option value="music">음악</option>
								<option value="book">책</option>
						</select></td>
						<td>
							<div class="search">
								<input type="text" placeholder="검색어 입력"> <img
									id="search-icon"
									src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<ul>
	<c:if test="${contentList}.length != 0">
		<!-- EL을 사용하여 가져온 데이터를 표시 -->
		<c:forEach var="content" items="${contentList}">
			<li>${content.title}- ${content.genre} - ${content.publishDate}</li>
		</c:forEach>
	</c:if>
	</ul>
</body>
</html>