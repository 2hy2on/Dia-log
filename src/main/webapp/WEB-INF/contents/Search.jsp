<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="../css/mainContents/search.css" rel="stylesheet"
	type="text/css">
<style>
select {
	padding: 0 20px;
	height: 30px;
	background-color: #FCF8EC;
	color: #000;
	right: 30px;
	margin-right: 20px;
	border: 1px solid #fff;
	border-radius: 30px;
	text-color: #000;
}

select:hover {
	border: 2px solid #000;
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
</body>
</html>