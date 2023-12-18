<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.dto.contents.Contents"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/contents/search.css' />"
	type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="<c:url value='/css/contents/contentsList.css' />" type="text/css">
<script>
	function updateFormAction() {
		var contentTypeSelect = document.getElementById("contentTypeSelect");
		var selectedValue = contentTypeSelect.options[contentTypeSelect.selectedIndex].value;

		var form = document.getElementById("searchForm");
		if (selectedValue === "movie") {
			form.action = "<c:url value='/contents/search/movie' />";
		} else if (selectedValue === "music") {
			form.action = "<c:url value='/contents/search/music' />";
		} else if (selectedValue === "book") {
			form.action = "<c:url value='/contents/search/book' />";
		} else {
			form.action = "<c:url value='/contents/search' />";
		}
	}

	function submitForm() {
		updateFormAction();
		document.forms["search"].submit();
	}
</script>

</head>
<body>
	<div class="container" style="display: flex;">
		<div class="row" style="margin: auto;">
			<form name="search" method="POST" action="" id="searchForm">
				<table class="pull-right">
					<tr>
						<td><select id="contentTypeSelect"
							onchange="updateFormAction()">
								<option value="0">선택</option>
								<option value="movie">영화</option>
								<option value="music">음악</option>
								<option value="book">책</option>
						</select></td>
						<td>
							<div class="search">
								<input type="text" name="title" placeholder="검색어 입력"> <img
									id="search-icon"
									src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
									alt="Submit" onclick="submitForm()">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<%
	List<Contents> searchList = (List<Contents>) request.getAttribute("searchList");

	if (searchList != null && !searchList.isEmpty()) {
		Iterator<Contents> iterator = searchList.iterator();
		int index = 1;
	%>

	<div class="gallery">
		<div class="container text-center">

			<%
			while (iterator.hasNext()) {
				Contents content = iterator.next();
				if (index % 5 == 1) {
			%>
			<div class="row">
				<%
				}
				%>
				<div class="col">
					<article class="card" data-bs-toggle="modal"
						data-bs-target="#exampleModal">
						<figure>
							<img src="<%=content.getContentImg()%>"
								alt="<%=content.getGenre()%>">
							<figcaption>
								<p class="h6"><%=content.getTitle()%></p>
							</figcaption>
						</figure>
					</article>
				</div>
				<%
				index++;
				}
				%>
				<%
				if (index % 5 == 1) {
				%>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>
</body>
</html>