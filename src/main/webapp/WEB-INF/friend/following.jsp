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

						//document ready�� ���ÿ� follwerList ��������
						$
								.ajax({ // �񵿱����� Ajax request �߻� �� ��� ó��
									type : "GET",
									url : "<c:url value='/friend/list/follower'/>",
									cache : false,
									dataType : "json", // ����� JSON ������ data (Console�� ��µǴ� log ����)
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
													.ajax({ // �񵿱����� Ajax request �߻� �� ��� ó��
														type : "GET",
														url : "<c:url value='/friend/list/follower'/>",
														cache : false,
														dataType : "json", // ����� JSON ������ data (Console�� ��µǴ� log ����)
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
													.ajax({ // �񵿱����� Ajax request �߻� �� ��� ó��
														type : "GET",
														url : "<c:url value='/friend/list/followee'/>",
														cache : false,
														dataType : "json", // ����� JSON ������ data (Console�� ��µǴ� log ����)
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
											// Ŭ���� ��ư�� ���� li ������Ʈ�� ã��
											var liElement = $(this).closest(
													"li");

											// li ������Ʈ���� �ȷο��� �̸��� ����
											var followerName = liElement
													.clone() // li ������Ʈ ����
													.children() // �ڽ� ������Ʈ ���� (��ư ����)
													.remove() // �ڽ� ������Ʈ ���� (��ư ����)
													.end() // ������ �ڽ� ������Ʈ �����ϰ� ������ ��ȯ
													.text() // �ؽ�Ʈ ����
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/delete/follower'/>",
														data : {
															followerName : followerName
														}, // �ȷο��� �̸��� ������ ����
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// ��� ó��
															if (result === true) {
																alert(followerName
																		+ "���� �����߽��ϴ�.");
															} else {
																alert("�ٽ� �õ����ּ���. ");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// ���� ó��
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("����! ��û ���´� "
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
											// Ŭ���� ��ư�� ���� li ������Ʈ�� ã��
											var liElement = $(this).closest(
													"li");

											// li ������Ʈ���� �ȷ����� �̸��� ����
											var followeeName = liElement
													.clone() // li ������Ʈ ����
													.children() // �ڽ� ������Ʈ ���� (��ư ����)
													.remove() // �ڽ� ������Ʈ ���� (��ư ����)
													.end() // ������ �ڽ� ������Ʈ �����ϰ� ������ ��ȯ
													.text() // �ؽ�Ʈ ����
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/delete/followee'/>",
														data : {
															followeeName : followeeName
														}, // �ȷ����� �̸��� ������ ����
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// ��� ó��
															if (result === true) {
																alert(followeeName
																		+ "���� �ȷ��׿��� �����߽��ϴ�.");
															} else {
																alert("�ٽ� �õ����ּ���.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// ���� ó��
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("����! ��û ���´� "
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
											// Ŭ���� ��ư�� ���� li ������Ʈ�� ã��
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
														}, // �ȷ����� �̸��� ������ ����
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// ��� ó��
															if (result === true) {
																alert(friendName
																		+ "�Կ��� �ű� ��û�� ���½��ϴ�.");
															} else {
																alert("�ٽ� �õ����ּ���.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// ���� ó��
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("����! ��û ���´� "
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
											// Ŭ���� ��ư�� ���� li ������Ʈ�� ã��
											var liElement = $(this).closest(
													"li");
											var receivedFriendName = liElement
													.clone() // li ������Ʈ ����
													.children() // �ڽ� ������Ʈ ���� (��ư ����)
													.remove() // �ڽ� ������Ʈ ���� (��ư ����)
													.end() // ������ �ڽ� ������Ʈ �����ϰ� ������ ��ȯ
													.text() // �ؽ�Ʈ ����
													.trim();

											$
													.ajax({
														type : "GET",
														url : "<c:url value='/friend/request/receive'/>",
														data : {
															receivedFriendName : receivedFriendName
														}, // �ȷ����� �̸��� ������ ����
														cache : false,
														dataType : "json",
														success : function(
																result) {
															// ��� ó��
															if (result === true) {
																alert(receivedFriendName + "���� �ȷο츦 �����߽��ϴ�.");
															} else {
																alert("�ٽ� �õ����ּ���.");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															// ���� ó��
															var message = jqXHR
																	.getResponseHeader("Status");
															if ((message == null)
																	|| (message.length <= 0)) {
																alert("����! ��û ���´� "
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
													.ajax({ // �񵿱����� Ajax request �߻� �� ��� ó��
														type : "GET",
														url : "<c:url value='/friend/request'/>",
														cache : false,
														dataType : "json", // ����� JSON ������ data (Console�� ��µǴ� log ����)
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
													.ajax({ // �񵿱����� Ajax request �߻� �� ��� ó��
														type : "GET",
														url : "<c:url value='/friend/list/recommend'/>",
														cache : false,
														dataType : "json", // ����� JSON ������ data (Console�� ��µǴ� log ����)
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
															// ���� ó��
															console
																	.error(
																			"Error:",
																			errorThrown);
														}
													});
										});

					});

	function printUnacceptedFriends(unacceptedFriends) {
		// ���� ������ ����� ���ο� �������� ������Ʈ
		$("#unacceptedFriendList").empty();

		// follow �����͸� �̿��Ͽ� ��� ����
		if (unacceptedFriends.length > 0) {
			for (var i = 0; i < unacceptedFriends.length; i++) {
				var friend = unacceptedFriends[i];
				$("#unacceptedFriendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followerName
										+ '<div class="d-flex">'
										+ '<button type="button" class="btn btn-custom-danger me-1 rounded-pill" id="deleteFollowerBt">��û ����</button>'
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="receiveFollowBt">��û ����</button>'
										+ '</div>' + '</li>');
			}
		}
	}

	function printRecommendList(recommendList) {
		// ���� ������ ����� ���ο� �������� ������Ʈ
		$("#recommendationList").empty();

		// follow �����͸� �̿��Ͽ� ��� ����
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
										+ '<button type="button" class="btn btn-primary btn-sm rounded-pill" id="sendRequestBt">ģ�� ��û</button>'
										+ '</li>');
			}
		}
	}

	function printFollowers(followers) {
		// ���� ������ ����� ���ο� �������� ������Ʈ
		$("#friendList").empty();

		// follow �����͸� �̿��Ͽ� ��� ����
		if (followers.length > 0) {
			for (var i = 0; i < followers.length; i++) {
				var friend = followers[i];
				$("#friendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followerName
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="deleteFollowerBt">�ȷο� ����</button>'
										+ '</li>');
			}
		}
	}

	function printFollowees(followees) {
		// ���� ������ ����� ���ο� �������� ������Ʈ
		$("#friendList").empty();

		// follow �����͸� �̿��Ͽ� ��� ����
		if (followees.length > 0) {
			for (var i = 0; i < followees.length; i++) {
				var friend = followees[i];
				$("#friendList")
						.append(
								'<li class="list-group-item d-flex justify-content-between align-items-center">'
										+ '<a class="social-icon" href="#!"></a>'
										+ friend.followeeName
										+ '<button type="button" class="btn btn-primary me-1 rounded-pill" id="deleteFolloweeBt">�ȷ��� ���</button>'
										+ '</li>');
			}
		}
	}

	function printSearchList(searchFriends) {
		// ���� ������ ����� ���ο� �������� ������Ʈ
		$("#searchList").empty();

		// follow �����͸� �̿��Ͽ� ��� ����
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
										+ '<button type="button" class="btn btn-primary btn-primary rounded-pill" id="sendRequestBt">ģ�� ��û</button>'
										+ '</li>');
			}
		} else {
			// �˻� ����� ���� �� �޽����� ���
			$("#searchList")
					.append(
							'<li class="list-group-item text-center">�˻� ����� �����ϴ�.</li>');
		}
	}
</script>
</head>

<body>
	<header>
		<h1>FOLLOW</h1>
		<div class="underline"></div>
		<button data-bs-target="#exampleModal" data-bs-toggle="modal"
			class="btn btn-primary" id="followRequest">�ȷο� ��û</button>
		<button data-bs-target="#searchFriendModal" data-bs-toggle="modal"
			class="btn btn-primary" id="searchFriend">ģ�� �˻�</button>
	</header>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<div class="container">
		<ul>
			<li class="active" onclick="setActive(this)" id="followerBt">�ȷο�</li>
			<li onclick="setActive(this)" id="followeeBt">�ȷ���</li>
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
					<h1 class="modal-title fs-5" id="searchFriendModalLabel">ģ�� �˻�</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div
							class="mb-3 d-flex justify-content-between align-items-center">
							<input class="form-control me-2 flex-grow-1" type="search"
								placeholder="ģ�� �˻�" aria-label="Search">
							<button class="btn btn-outline-success ms-2 rounded-pill"
								type="button">Search</button>
						</div>
						<ul class="request-group" id='searchList'>
						</ul>

						<!-- ����� ��õ ģ�� ����Ʈ ������ ���� <br> �±� -->
						<p class="fw-bold fs-6">������ ����� ģ���� ã�ƺ�����</p>
						<hr class="my-3">

						<!-- ��õ ģ�� ����Ʈ �߰� -->
						<ul class="recommendation-group" id='recommendationList'>
							<!-- ��õ ģ�� ����Ʈ �����۵��� �������� �߰��� �� �ֽ��ϴ�. -->
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
					<h1 class="modal-title fs-5" id="exampleModalLabel">�ȷο� ��û</h1>
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