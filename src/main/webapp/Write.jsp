<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
  * {margin: 0; padding: 0; box-sizing: border-box;}
body{
    display: flex;
    justify-content:center;
    align-items:center;
    height: 50vh;
    background-size: cover;
}
</style>
<link rel="stylesheet" href="style4.css">
</head>
<body>
<div class = "container">
<div class = "row">
<form method = "post" action = "writeAction.jsp">
<table class = "table table-striped" style = "text-align:center; border: 1px solid #dddddd">
<thead>
<tr>
<th colspan = "2" style="background-color: #eeeeee; textalign: center;">게시판 글쓰기</th>
</tr>
</thead>
<tbody>
<tr>
<td><input type = "text" class = "form-control" placeholder = "글 제목" name = "boardTitle" maxlength="50"></td>
</tr>
<tr>
<td><textarea  class = "form-control" placeholder = "글 내용" name = "boardContent" maxlength="2048" style = "height: 350px;"></textarea></td>
</tr>
</tbody>
</table>
<input type = "submit" value = "글쓰기">
</form>
</div>
</div>
</body>
</html>