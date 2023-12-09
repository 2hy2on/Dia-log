<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dia-log</title>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	height: 100vh;
	background-size: cover;
}

.login-form {
	position: absolute;
	top: 60%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 2;
}

.login-form h1 {
	font-size: 32px;
	color: #79A3B1;
	text-align: center;
	margin-bottom: 50px;
}

.input {
	width: 300px;
	position: relative;
	margin: auto;
	margin-bottom: 20px;
}

.input input {
	width: 100%;
	padding: 20px 10px 10px;
	font-size: 18px;
	color: #456268;
}

.input label {
	position: absolute;
	left: 10px;
	top: 15px;
	font-size: 18px;
	color: #456268;
}

.input input:focus+label, .input input:valid+label {
	top: 0;
	font-size: 13px;
}

.btn {
	margin-top: 30px;
}

.btn button {
	width: 100%;
	height: 50px;
	background: #456268;
	color: #fff;
	font-size: 20px;
	border: none;
	border-radius: 25px;
	padding-left: 100px;
	padding-right: 100px;
}

.login-form {
	text-decoration: none;
	list-style-type: none;
}

.login-title {
	color: #456268;
	margin-top: 50px;
	margin-bottom: 30px;
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="../Navibar.jsp"%>
	<section class="login-form">
	<h1 class="login-title">로그인</h4>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="input">
				<input type="text" name="ID" id="ID"> <label for="ID">ID</label>
			</div>
			<div class="input">
				<input type="password" name="password" id="password"> <label
					for="password">PASSWORD</label>
			</div>
			<li><a href="<%=request.getContextPath()%>/register-form">회원가입</a></li>
			<div class="btn">
				<button type="submit">LOGIN</button>
			</div>
		</form>
	</section>
</body>
</html>
