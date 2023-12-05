<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.*"%>

<%--
<jsp:useBean id="follow" class="model.dao.FollowDAO" scope="page" />
<jsp:setProperty name="follow" property="ID" />
<jsp:setProperty name="follow" property="password" />
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Following</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel=stylesheet href="<c:url value='/css/friend/following.css' />" type="text/css">

</head>

<body>
	<header>
		<h1>FOLLOW</h1>
		<div class="underline"></div>
		<button data-bs-target="#exampleModal" data-bs-toggle="modal"
			class="btn btn-primary">Follow Request</button>
	</header>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<div class="container">
		<ul>
			<li class="active" onclick="setActive(this)">팔로우</li>
			<li onclick="setActive(this)">팔로잉</li>
		</ul>
		<div class="bar"></div>

		<form class="d-flex" role="search">
			<input class="form-control me-2" type="search" placeholder="내 친구 검색"
				aria-label="Search">
			<button class="btn btn-outline-success" type="submit">Search</button>
		</form>
	</div>

	<ul class="list-group">
		<li
			class="list-group-item d-flex justify-content-between align-items-center">
			<a class="social-icon" href="#!"></a> person name <span
			class="badge bg-primary rounded-pill">followed</span>
		</li>
		<li
			class="list-group-item d-flex justify-content-between align-items-center">
			<a class="social-icon" href="#!"></a> person name <span
			class="badge bg-primary rounded-pill">followed</span>
		</li>
		<li
			class="list-group-item d-flex justify-content-between align-items-center">
			<a class="social-icon" href="#!"></a> person name <span
			class="badge bg-primary rounded-pill">followed</span>
		</li>
		<li
			class="list-group-item d-flex justify-content-between align-items-center">
			<a class="social-icon" href="#!"></a> person name <span
			class="badge bg-primary rounded-pill">followed</span>
		</li>
		<li
			class="list-group-item d-flex justify-content-between align-items-center">
			<a class="social-icon" href="#!"></a> person name <span
			class="badge bg-primary rounded-pill">followed</span>
		</li>
	</ul>

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">팔로우 요청</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<ul class="request-group">
								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<a class="social-icon" href="#!"></a> person name <span
									class="badge bg-primary rounded-pill">팔로우 수락</span>
								</li>
								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<a class="social-icon" href="#!"></a> person name <span
									class="badge bg-primary rounded-pill">팔로우 수락</span>
								</li>
								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<a class="social-icon" href="#!"></a> person name <span
									class="badge bg-primary rounded-pill">팔로우 수락</span>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger">요청 삭제</button>
					<button type="button" class="btn btn-primary">요청 수락</button>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/js/friend/following.js' />"></script>
</body>

</html>