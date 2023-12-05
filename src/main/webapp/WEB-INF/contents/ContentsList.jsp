<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Filtering</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
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
	font-family: 'LINESeedKR-Bd', sans-serif;
	justify-content: center;
	align-items: center;
	min-height: 5vh;
}

.gallery {
	background-color: var(- -background);
	gap: 16px;
	margin-top: 30px;
}

.card {
	display: inline-flex;
	width: 180px;
	height: 180px;
	background-color: var(- -background);
	border-radius: 10px;
	transition: 1000ms all;
	transform-origin: center left;
	/*     box-shadow: 0 5px 12px rgba(0, 0, 0, 0.5); */
	outline: 1px solid var(- -background);
	overflow: hidden;
}

.card img {
	max-width: 100%;
	/* 이미지가 카드 안에 꽉 차도록 크기를 조정 */
	max-height: 100%;
	object-fit: cover;
	border-radius: 4px;
}

.card:hover {
	cursor: pointer;
	transform: scale(1.15);
}

.card:hover figcaption {
	font-size: 0.6rem;
	position: absolute;
	height: 80px;
	width: 160px;
	display: flex;
	align-items: end;
	background: linear-gradient(to top, rgba(0, 0, 0, 0.9) 0%,
		rgba(0, 0, 0, 0) 100%);
	color: white;
	left: 0px;
	bottom: 0px;
	padding-left: 12px;
	padding-bottom: 10px;
}

.card:hover ~.card {
	font-weight: bold;
	cursor: pointer;
	transform: translateX(22px);
}

.col {
	margin-bottom: 30px;
}

header {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	background-color: #163172;
	color: white;
	text-align: left;
	padding: 10px 20px;
}

header h1 {
	margin: 0;
	padding: 0;
	padding-left: 6%;
}

.underline {
	border-bottom: 2px solid white;
	/* 밑줄 굵기 조절 */
	width: 30%;
	/* 밑줄의 너비 조절 */
	margin-top: 10px;
	/* 밑줄의 위 여백 조절 */
	margin-bottom: 20px;
	/* 밑줄의 아래 여백 조절 */
}

/* 여기서부터 아이콘 */
.social-icons .social-icon {
	display: inline-flex;
	margin: 0;
	padding: 0;
	padding-left: 6%;
	height: 3.5rem;
	width: 3.5rem;
	background-color: cornflowerblue;
	color: #fff;
	border-radius: 100%;
	font-size: 1.5rem;
	margin-right: 1.5rem;
	align-items: center;
}

.social-icons .social-icon:last-child {
	margin-right: 0;
}

.social-icons .social-icon:hover {
	background-color: #D6E4F0;
}

.dev-icons {
	font-size: 3rem;
	align-items: center;
}

.star-rating {
	display: flex;
	flex-direction: row-reverse;
	font-size: 2rem;
	line-height: 2rem;
	justify-content: flex-end;
	padding: 0.2em 0;
	margin-left: 0;
	/* Adjust the left margin as needed */
}

.star-rating input {
	display: none;
}

.star-rating label {
	-webkit-text-fill-color: transparent;
	/* Will override color (regardless of order) */
	-webkit-text-stroke-width: 2.3px;
	-webkit-text-stroke-color: #2b2a29;
	cursor: pointer;
}

.star-rating :checked ~label {
	-webkit-text-fill-color: #FDD835;
}

.star-rating label:hover, .star-rating label:hover ~label {
	-webkit-text-fill-color: #FFF59D;
}
</style>
</head>
<body>
	<% 
	List<Contents> contentList = (List<Contents>)request.getAttribute("contentList");
	
	if (contentList != null && !contentList.isEmpty()) {
		Iterator<Contents> iterator = contentList.iterator();
	%>
	<div class="gallery">
		<div class="container text-center">
			<div class="row">
				<%
				while (iterator.hasNext()) {
					Contents content = iterator.next();
				%>
				<div class="col">
					<article class="card" data-bs-toggle="modal"
						data-bs-target="#exampleModal">
						<figure>
							<img src="<%= content.getContentImg() %>"
								alt=<%= content.getGenre() %>>
							<figcaption>
								<p class="h6"><%= content.getTitle() %></p>
							</figcaption>
						</figure>
					</article>
				</div>
				<% } %>
			</div>
		</div>
	</div>
	<% } %>

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">date :</label>
							<input type="date">
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">my
								review:</label> <input type="text" class="form-control"
								id="recipient-name">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">others:</label>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="alert('담기 완료')">담기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>