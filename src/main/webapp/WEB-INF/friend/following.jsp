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
<link rel=stylesheet href="<c:url value='/css/friend/following.css' />"
	type="text/css">

<script src="<c:url value='/js/diary/jquery-1.10.2.js' />"
	type="text/javascript"></script>
<script src="<c:url value='/js/diary/jquery-ui.custom.min.js' />"
	type="text/javascript"></script>
<script>
	$(document)
			.ready(
					function() {

						//document ready와 동시에 follwerList 가져오기
						$
								.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
									type : "GET",
									url : "<c:url value='/friend/list/follower'/>",
									cache : false,
									dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
									success : printFollowers,
									error : function(jqXHR, textStatus,
											errorThrown) {
										var message = jqXHR
												.getResponseHeader("Status");
										if ((message == null)
												|| (message.length <= 0)) {
											alert("Error! Request status is "
													+ jqXHR.status);
										} else {
											alert(message);
										}
									}
								});

						$("#followerBt")
								.click(
										function() {
											$
													.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
														type : "GET",
														url : "<c:url value='/friend/list/follower'/>",
														cache : false,
														dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
														success : printFollowers,
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("Error! Request status is "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$("#followeeBt")
								.click(
										function() {
											$
													.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
														type : "GET",
														url : "<c:url value='/friend/list/followee'/>",
														cache : false,
														dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
														success : printFollowees,
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("Error! Request status is "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$(document)
								.on(
										"click",
										"#deleteFollowerBt",
										function() {
											// 클릭한 버튼이 속한 li 엘리먼트를 찾음
											var liElement = $(this).closest(
													"li");

											// li 엘리먼트에서 팔로워의 이름을 추출
											var followerName = liElement
													.clone() // li 엘리먼트 복제
													.children() // 자식 엘리먼트 선택 (버튼 포함)
													.remove() // 자식 엘리먼트 제거 (버튼 제외)
													.end() // 제거한 자식 엘리먼트 제외하고 복제본 반환
													.text() // 텍스트 추출
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/delete/follower'/>",
														data : {
															followerName : followerName
														}, // 팔로워의 이름을 서버에 전달
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// 결과 처리
															if (result === true) {
																alert(followerName
																		+ "님을 삭제했습니다.");
															} else {
																alert("다시 시도해주세요. ");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// 오류 처리
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("에러! 요청 상태는 "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$(document)
								.on(
										"click",
										"#deleteFolloweeBt",
										function() {
											// 클릭한 버튼이 속한 li 엘리먼트를 찾음
											var liElement = $(this).closest(
													"li");

											// li 엘리먼트에서 팔로잉의 이름을 추출
											var followeeName = liElement
													.clone() // li 엘리먼트 복제
													.children() // 자식 엘리먼트 선택 (버튼 포함)
													.remove() // 자식 엘리먼트 제거 (버튼 제외)
													.end() // 제거한 자식 엘리먼트 제외하고 복제본 반환
													.text() // 텍스트 추출
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/delete/followee'/>",
														data : {
															followeeName : followeeName
														}, // 팔로잉의 이름을 서버에 전달
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// 결과 처리
															if (result === true) {
																alert(followeeName
																		+ "님을 팔로잉에서 삭제했습니다.");
															} else {
																alert("다시 시도해주세요.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// 오류 처리
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("에러! 요청 상태는 "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$(document)
								.on(
										"click",
										"#sendRequestBt",
										function() {
											// 클릭한 버튼이 속한 li 엘리먼트를 찾음
											var liElement = $(this).closest(
													"li");
											var friendName = liElement.find(
													'span:first').text().trim();
											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/request/send'/>",
														data : {
															friendName : friendName
														}, // 팔로잉의 이름을 서버에 전달
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// 결과 처리
															if (result === true) {
																alert(friendName
																		+ "님에게 신구 요청을 보냈습니다.");
															} else {
																alert("다시 시도해주세요.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// 오류 처리
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("에러! 요청 상태는 "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$(document)
								.on(
										"click",
										"#receiveFollowBt",
										function() {
											// 클릭한 버튼이 속한 li 엘리먼트를 찾음
											var liElement = $(this).closest(
													"li");
											var receivedFriendName = liElement
													.clone() // li 엘리먼트 복제
													.children() // 자식 엘리먼트 선택 (버튼 포함)
													.remove() // 자식 엘리먼트 제거 (버튼 제외)
													.end() // 제거한 자식 엘리먼트 제외하고 복제본 반환
													.text() // 텍스트 추출
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/request/receive'/>",
														data : {
															receivedFriendName : receivedFriendName
														}, // 팔로잉의 이름을 서버에 전달
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// 결과 처리
															if (result === true) {
																alert(receivedFriendName + "님의 팔로우를 수락했습니다.");
															} else {
																alert("다시 시도해주세요.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// 오류 처리
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("에러! 요청 상태는 "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$("#followRequest")
								.click(
										function() {
											$
													.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
														type : "GET",
														url : "<c:url value='/friend/request'/>",
														cache : false,
														dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
														success : printUnacceptedFriends,
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("Error! Request status is "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$("#searchFriend")
								.click(
										function() {
											$
													.ajax({ // 비동기적인 Ajax request 발생 및 결과 처리
														type : "GET",
														url : "<c:url value='/friend/list/recommend'/>",
														cache : false,
														dataType : "json", // 결과는 JSON 형식의 data (Console에 출력되는 log 참조)
														success : printRecommendList,
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("Error! Request status is "
																		+ jqXHR.status);
															} else {
																alert(message);
															}
														}
													});
										});

						$("#searchFriendModal button[type='button']")
								.click(
										function() {
											var searchTerm = $(
													"#searchFriendModal input[type='search']")
													.val();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/search'/>",
														data : {
															searchTerm : searchTerm
														},
														cache : false,
														dataType : "json",
														success : printSearchList,
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// 오류 처리
															console
																	.error(
																			"Error:",
																			errorThrown);
														}
													});
										});

					});

	function printUnacceptedFriends(unacceptedFriends) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#unacceptedFriendList").empty();

		// follow 데이터를 이용하여 목록 생성
		if (unacceptedFriends.length > 0) {
			for (var i = 0; i < unacceptedFriends.length; i++) {
				var friend = unacceptedFriends[i];
				$("#unacceptedFriendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followerName
										+ '<div class="d-flex">'
										+ '<button type="button" class="btn btn-custom-danger me-1 rounded-pill" id="deleteFollowerBt">요청 삭제</button>'
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="receiveFollowBt">요청 수락</button>'
										+ '</div>' + '</li>');
			}
		}
	}

	function printRecommendList(recommendList) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#recommendationList").empty();

		// follow 데이터를 이용하여 목록 생성
		if (recommendList.length > 0) {
			for (var i = 0; i < recommendList.length; i++) {
				var friend = recommendList[i];
				$("#recommendationList")
						.append(
								'<li class="list-group-item d-flex align-items-center" style="margin-bottom: 5px; border-bottom: 1px solid #dee2e6;">'
										+ '<div class="me-auto">'
										+ '<span>'
										+ friend.userName
										+ '</span>'
										+ '</div>'
										+ '<div class="text-center flex-grow-1">'
										+ '<span>'
										+ friend.book_interest
										+ ' '
										+ '</span>'
										+ '<span class="me-2">'
										+ friend.movie_interest
										+ ' '
										+ '</span>'
										+ '<span>'
										+ friend.music_interest
										+ '</span>'
										+ '</div>'
										+ '<button type="button" class="btn btn-primary btn-sm rounded-pill" id="sendRequestBt">친구 신청</button>'
										+ '</li>');
			}
		}
	}

	function printFollowers(followers) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#friendList").empty();

		// follow 데이터를 이용하여 목록 생성
		if (followers.length > 0) {
			for (var i = 0; i < followers.length; i++) {
				var friend = followers[i];
				$("#friendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followerName
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="deleteFollowerBt">팔로워 삭제</button>'
										+ '</li>');
			}
		}
	}

	function printFollowees(followees) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#friendList").empty();

		// follow 데이터를 이용하여 목록 생성
		if (followees.length > 0) {
			for (var i = 0; i < followees.length; i++) {
				var friend = followees[i];
				$("#friendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followeeName
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="deleteFolloweeBt">팔로잉 취소</button>'
										+ '</li>');
			}
		}
	}

	function printSearchList(searchFriends) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#searchList").empty();

		// follow 데이터를 이용하여 목록 생성
		if (searchFriends.length > 0) {
			for (var i = 0; i < searchFriends.length; i++) {
				var friend = searchFriends[i];
				$("#searchList")
						.append(
								'<li class="list-group-item d-flex align-items-center" style="margin-bottom: 5px;">'
										+ '<div class="me-auto">'
										+ '<span>'
										+ friend.userName
										+ '</span>'
										+ '</div>'
										+ '<div class="text-center flex-grow-1">'
										+ '<span>'
										+ friend.book_interest
										+ ' '
										+ '</span>'
										+ '<span class="me-2">'
										+ friend.movie_interest
										+ ' '
										+ '</span>'
										+ '<span>'
										+ friend.music_interest
										+ '</span>'
										+ '</div>'
										+ '<button type="button" class="btn btn-primary btn-primary rounded-pill" id="sendRequestBt">친구 신청</button>'
										+ '</li>');
			}
		} else {
			// 검색 결과가 없을 때 메시지를 출력
			$("#searchList")
					.append(
							'<li class="list-group-item text-center">검색 결과가 없습니다.</li>');
		}
	}
</script>
</head>

<body>
	<header>
		<h1>FOLLOW</h1>
		<div class="underline"></div>
		<button data-bs-target="#exampleModal" data-bs-toggle="modal"
			class="btn btn-primary" id="followRequest">팔로우 신청</button>
		<button data-bs-target="#searchFriendModal" data-bs-toggle="modal"
			class="btn btn-primary" id="searchFriend">친구 검색</button>
	</header>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<div class="container">
		<ul>
			<li class="active" onclick="setActive(this)" id="followerBt">팔로워</li>
			<li onclick="setActive(this)" id="followeeBt">팔로잉</li>
		</ul>
		<div class="bar"></div>
	</div>

	<ul class="list-group" id="friendList">
	</ul>


	<div class="modal fade" id="searchFriendModal" tabindex="-1"
		aria-labelledby="searchFriendModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="searchFriendModalLabel">친구 검색</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div
							class="mb-3 d-flex justify-content-between align-items-center">
							<input class="form-control me-2 flex-grow-1" type="search"
								placeholder="친구 검색" aria-label="Search">
							<button class="btn btn-outline-success ms-2 rounded-pill"
								type="button">Search</button>
						</div>
						<ul class="request-group" id='searchList'>
						</ul>

						<!-- 여백과 추천 친구 리스트 구분을 위한 <br> 태그 -->
						<p class="fw-bold fs-6">취향이 비슷한 친구를 찾아보세요</p>
						<hr class="my-3">

						<!-- 추천 친구 리스트 추가 -->
						<ul class="recommendation-group" id='recommendationList'>
							<!-- 추천 친구 리스트 아이템들을 동적으로 추가할 수 있습니다. -->
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>




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
							<ul class="request-group" id='unacceptedFriendList'>
							</ul>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/js/friend/following.js' />"></script>
</body>

</html>