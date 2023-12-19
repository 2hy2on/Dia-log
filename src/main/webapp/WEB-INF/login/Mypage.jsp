<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.dao.user.UserDAO"%>
<%@ page import="model.dto.user.User"%>
<%
String ID = (String) session.getAttribute("ID");
UserDAO userDAO = new UserDAO();
User user = userDAO.getUserInfo(ID);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.wrap {
	width: 750px;
	margin: 50px auto;
	padding: 50px;
	box-sizing: border-box;
	background: #fff;
	text-align: center;
}

.wrap h1 {
	padding-bottom: 12px;
	border-bottom: 3px solid #D0E8F2;
	font-size: 32px;
	letter-spacing: -2px;
	color: #456268;
	margin-bottom: 50px;
}

.wrap form {
	margin-top: 30px;
}

.wrap input[type="text"], .wrap input[type="password"], * {
	margin: 0;
	padding: 0;
}

body {
	display: flex;
	flex-direction: column;
	height: 100vh;
	background-size: cover;
}

.wrap {
	width: 750px;
	padding: 50px;
	box-sizing: border-box;
	background: #fff;
	text-align: center;
}

.wrap h1 {
	padding-bottom: 12px;
	border-bottom: 3px solid #D0E8F2;
	font-size: 32px;
	letter-spacing: -2px;
	color: #456268;
	margin-bottom: 50px;
}

.wrap form {
	margin-top: 30px;
}

.wrap input[type="text"], .wrap input[type="password"], .wrap input[type="tel"],
	.wrap input[type="email"] {
	padding: 10px;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 15px;
}

.wrap input[type="submit"] {
	width: 100%;
	height: 50px;
	background: #79A3B1;
	color: #fff;
	font-size: 20px;
	border: none;
	border-radius: 25px;
	cursor: pointer;
}

.wrap input[type="button"] {
	padding: 6px 10px;
	border: none;
	border-radius: 25px;
	background: #79A3B1;
	font-weight: 600;
	color: #fff;
	cursor: pointer;
}

.wrap dt, .wrap dd {
	display: block;
	font-size: 14px;
	margin-bottom: 10px;
}

.wrap dl {
	margin-top: 10px;
}

.wrap dt {
	width: 100%;
}

div {
	width: 500px;
	float: left;
	border: 1px solid;
}
</style>
</head>
<body>
	<%@ include file="../Navibar.jsp"%>
	<div class="wrap">
		<h1>내 정보 수정</h1>
		<form action="<%=request.getContextPath()%>/mypage/update"
			method="post">
			<dl>
				<dt>이름</dt>
				<dd>
					<input type="text" value="<%=user.getUserName()%>"
						name="Username"> <input type="hidden" name="action"
						value="updateName"> <input type="submit" value="이름 수정">
				</dd>
			</dl>
		</form>
		<form action="<%=request.getContextPath()%>/mypage/update"
			method="post">
			<dl>
				<dt>이메일</dt>
				<dd>
					<input type="email" value="<%=user.getEmail()%>" name="Email">
					<input type="hidden" name="action" value="updateEmail"> <input
						type="submit" value="이메일 수정">
				</dd>
			</dl>
		</form>
		<form action="<%=request.getContextPath()%>/mypage/update"
			method="post">
			<input type="hidden" name="action" value="delete"> <input
				type="submit" value="회원탈퇴">
		</form>
		<dl>
			<form action="<%=request.getContextPath()%>/mypage/update"
				method="post">
				<input type="hidden" name="action" value="logout"> <input
					type="submit" value="로그아웃">
			</form>
		</dl>
	</div>
</body>
</html>