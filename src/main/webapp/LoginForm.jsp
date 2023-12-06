<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form</title>
<style>
*{
    margin: 0px;
    padding:0;
}
body{
    background:#FCF8EC;
}
.wrap h1{
    font-size:32px;color:#456268;
    text-align: center;
    margin-bottom:50px;
}
.wrap{
    width:750px;
    margin:0 auto;
    padding:50px;
    box-sizing:border-box;
    background:#fff;

}
.wrap h1{
    padding-bottom:12px;
    border-bottom:3px solid #D0E8F2;
    font-size:32px;
    letter-spacing:-2px;
}
.wrap form {
    margin-top: 30px;
}
.wrap input[type="text"],
.wrap input[type="password"],
.wrap input[type="tel"],
.wrap input[type="email"]{
    padding:10px;
}
.wrap input[type="submit"]{
    width:100%; height:50px;
    background:#79A3B1;
    color :#fff;
    font-size:20px;
    border:none;
    border-radius:25px;
    margin-top:35px;
}
.wrap input[type="button"]{
    padding:6px 10px;
    border: none;
    border-radius:25px;
    background:#79A3B1;
    font-weight: 600;
    color:#fff;
    cursor:pointer;
}
.wrap dt,
.wrap dd {
    display: inline-block;
    font-size:14px;
    vertical-align:middle;
}
.wrap dl{
    margin-top:10px;
    font-size: 0;
}
.wrap dt {
    width: 20%;
}
.wrap dd{
    width: 80%;
}
</style>
<link rel="stylesheet" href="style2.css">
</head>
<body>
	<div class="wrap">
		<h1>회원가입</h1>
		<form method="post" action="FormAction.jsp">
			<dl>
				<dt>이름</dt>
				<dd>
					<input type="text" name="userName" placeholder="이름 입력">
				</dd>
			</dl>
			<dl>
				<dt>성별</dt>
				<dd>
					<input type="text" name="gender" placeholder="man/woman">
				</dd>
			</dl>
			<dl>
				<dt>아이디</dt>
				<dd>
					<input type="text" name="ID" placeholder="아이디 입력">
				</dd>
			</dl>
			<dl>
				<dt>비밀번호</dt>
				<dd>
					<input type="password" name="password" placeholder="비밀번호 입력">
				</dd>
			</dl>
			<dl>
				<dt>이메일</dt>
				<dd>
					<input type="email" name="email" placeholder="이메일 입력">
				</dd>
			</dl>
			<dl>
				<dt>선호하는 영화 취향</dt>
				<dd>
				<input type="text" name="Movie_interest" placeholder="romance/horror/action">
				</dd>
			</dl>
			<dl>
				<dt>선호하는 책 취향</dt>
				<dd>
					<input type="text" name="Book_interest" placeholder="cartoon/novel/poem">
				</dd>
			</dl>
			<dl>
				<dt>선호하는 음악 취향</dt>
				<dd>
					<input type="text" name="Music_interest" placeholder="pop/hiphop/ballad">
				</dd>
			</dl>
			<input type="submit" value="회원가입">
		</form>
	</div>
</body>
</html>