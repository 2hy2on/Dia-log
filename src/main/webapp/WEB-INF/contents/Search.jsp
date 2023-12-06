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
<!-- <link href="../../css/contents/search.css" rel="stylesheet"
	type="text/css"> -->
<script>
	function submitForm() {
		document.forms["search"].submit();
	}
</script>
<style>
body {
	width: 100%;
	margin: 0;
}

.pull-right select {
	padding: 0 20px;
	height: 30px;
	background-color: #FCF8EC;
	color: #000;
	right: 30px;
	margin-top: 15px;
	margin-right: 15px;
	border: 1px solid #fff;
	border-radius: 30px;
	text-color: #000;
}

/* select:hover {
	border: 2px solid #000;
} */
.search {
	position: relative;
	width: 300px;
	margin-top: 20px;
}

input {
	width: 100%;
	border: 1px solid #bbb;
	border-radius: 15px;
	padding: 10px 12px;
	font-size: 14px;
	background-color: #FCF8EC;
	margin-bottom: 20px;
}

.search img {
	position: absolute;
	width: 17px;
	top: 10px;
	right: 12px;
	margin: 0;
}
</style>
</head>
<body>
	<div class="container" style="display: flex;">
		<div class="row" style="margin: auto;">
			<form name="search" method="POST"
				action="<c:url value='/contents/search' />">
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

	<div class="search-results">
		<% 
        List<Contents> searchList = (List<Contents>)request.getAttribute("searchList");
        
        if (searchList != null && !searchList.isEmpty()) {
            Iterator<Contents> iterator = searchList.iterator();
            
            while (iterator.hasNext()) {
                Contents content = iterator.next();
    %>
		<div class="search-result">
			<h3><%= content.getTitle() %></h3>
			<p><%= content.getGenre() %></p>
		</div>
		<% 
            } 
        } %>
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
					<button type="button" class="btn btn-danger"
						onclick="alert('담기 완료')">담기</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>