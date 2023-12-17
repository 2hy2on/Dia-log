<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Filtering</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="<c:url value='/css/diary/reviewFiltering.css' />" type="text/css">

</head>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
	
</script>
<script>
	$(document).ready(function() {

		//document ready와 동시에 follwerList 가져오기
		$.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
			type : "GET",
			url : "<c:url value='/friend/list/follower'/>",
			cache : false,
			dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
			success : printFollowers,
			error : function(jqXHR, textStatus, errorThrown) {
				var message = jqXHR.getResponseHeader("Status");
				if ((message == null) || (message.length <= 0)) {
					alert("Error! Request status is " + jqXHR.status);
				} else {
					alert(message);
				}
			}
		});
	});
</script>
<body>
	<header>
		<%@ include file="../Navibar.jsp"%>
	</header>
	<div>
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
			fill="currentColor" class="bi bi-film" viewBox="0 0 16 16"
			style="margin-top: 20px;">
			<path
				d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zm-2 3v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z" />
		</svg>
	</div>
	<div class="gallery">
		<div class="container text-center">
			<div class="row">
				<div class="col">
					<article class="card" data-bs-toggle="modal"
						data-bs-target="#exampleModal">
						<figure>
							<img src="./img/movie17.jpg" alt="movie">
							<figcaption>
								<p class="h6">마지막 카드</p>
							</figcaption>
						</figure>
					</article>
				</div>
			</div>
		</div>
	</div>

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
							<label for="message-text" class="col-form-label">Review
								Score :</label>
							<div class="star-rating space-x-4 mx-auto">
								<input type="radio" id="5-stars" name="rating" value="5"
									v-model="ratings" /> <label for="5-stars" class="star pr-4">★</label>
								<input type="radio" id="4-stars" name="rating" value="4"
									v-model="ratings" /> <label for="4-stars" class="star">★</label>
								<input type="radio" id="3-stars" name="rating" value="3"
									v-model="ratings" /> <label for="3-stars" class="star">★</label>
								<input type="radio" id="2-stars" name="rating" value="2"
									v-model="ratings" /> <label for="2-stars" class="star">★</label>
								<input type="radio" id="1-star" name="rating" value="1"
									v-model="ratings" /> <label for="1-star" class="star">★</label>
							</div>
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
					<button type="button" class="btn btn-danger">삭제하기</button>
					<button type="button" class="btn btn-primary">저장하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
<div class="social-icons"
	style="position: absolute; top: 0; left: 0; padding: 10px; top: 190px; left: 10px;">
	<a class="social-icon" href="#!"><i class="bi bi-film"></i></a> <a
		class="social-icon" href="#!"><i class="bi bi-music-note-beamed"></i></a>
	<a class="social-icon" href="#!"><i class="bi bi-book"></i></a>
</div>

</html>