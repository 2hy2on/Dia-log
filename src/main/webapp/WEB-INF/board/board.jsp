<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.dao.board.BoardDAO"%>
<%@ page import="model.dto.board.Board"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp 게시판 웹사이트</title>
<style type="text/css">
a, a:hover {
	color: #000000;
	text-decoration: none;
}

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	display: flex;
	flex-direction: column;
	height: 100vh;
	background-size: cover;
}

.container {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	margin-top: 50px;
}
.content {
	flex: 1;
	padding: 10px;
	margin-top: 30px; /* 조절 가능한 여백 추가 */
}

.content table {
	width: 100%;
	text-align: center;
	border: 1px solid #dddddd;
}

.content table th, .content table td {
	border: 1px solid #dddddd;
	padding: 10px;
}

.content .btn {
	margin-top: 20px;
	display: block;
	padding: 10px;
	background-color: #456268;
	color: white;
	text-decoration: none;
	text-align: center;
}

.content .btn:hover {
	background-color: #345056;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../Navibar.jsp" />
	</header>

	<div class="container">
		<div class="content">
			<!-- 게시판 내용 영역 -->
			<%
			String ID = null;
			if (session.getAttribute("ID") != null) {
			    ID = (String) session.getAttribute("ID");
			}
			int pageNumber = 1;
			if (request.getParameter("pageNumber") != null) {
			    pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}
			%>

			<table>
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">아이디</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					BoardDAO boardDAO = new BoardDAO();
					ArrayList<Board> list = boardDAO.getList(pageNumber);
					for (int i = 0; i < list.size(); i++) {
					    Board board = list.get(i);
					%>
					<tr>
						<td><%=list.get(i).getBoardID()%></td>
						<td><a
							href="/dialog/board/view?boardID=<%=list.get(i).getBoardID()%>"><%=list.get(i).getBoardTitle()%></a></td>
						<td><%=list.get(i).getID()%></td>
						<td>
							<%
							String boardDate = list.get(i).getBoardDate();
							if (boardDate != null && boardDate.length() >= 16) {
							    out.print(boardDate.substring(0, 11) + boardDate.substring(11, 13) + "시" + boardDate.substring(14, 16) + "분");
							} else {
							    out.print("날짜 형식 오류");
							}
							%>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<%
			if (pageNumber != 1) {
			%>
			<a
				href="<%=request.getContextPath()%>/board?pageNumber=<%=pageNumber - 1%>"
				class="btn btn-success btn-arrow-left">이전</a>
			<%
			}
			if (boardDAO.nextPage(pageNumber + 1)) {
			%>
			<a
				href="<%=request.getContextPath()%>/board?pageNumber=<%=pageNumber + 1%>"
				class="btn btn-success btn-arrow-left">다음</a>
			<%
			}
			%>
			<a href="/dialog/board/write" class="btn">글쓰기</a>
		</div>
	</div>
</body>
</html>