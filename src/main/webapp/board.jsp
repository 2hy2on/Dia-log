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
<link rel="stylesheet" href="style5.css">
<style type="text/css">
a, a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
<style>
* {margin: 0; padding: 0; box-sizing: border-box;}
body{
    display: flex;
    justify-content:center;
    align-items:center;
    height: 50vh;
    background-size: cover;
}
button.btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #456268;
            color: white;
            text-decoration: none;
            border: none; /* 버튼의 기본 테두리 제거 */
            cursor: pointer;
        }

        button.btn:hover {
            background-color: #345056;
        }
</style>
</head>
<body>
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
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; textalign: center;">번호</th>
						<th style="background-color: #eeeeee; textalign: center;">제목</th>
						<th style="background-color: #eeeeee; textalign: center;">작성자</th>
						<th style="background-color: #eeeeee; textalign: center;">작성일</th>
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
						<td><%=board.getBoardID()%></td>
						<td><a href="view.jsp?BoardID=<%=board.getBoardID()%>"><%=board.getBoardTitle()%></a></td>
						<td><%=board.getID()%></td>
						<td>
							<%
							String boardDate = board.getBoardDate();
							if (boardDate != null && boardDate.length() >= 16) {
							    out.print(boardDate.substring(0, 11) + boardDate.substring(11, 13) + "시" + boardDate.substring(14, 16) + "분");
							} else {
							    out.print("날짜 없음");
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
			<a href="board.jsp?pageNumber=<%=pageNumber - 1%>"
				class="btn btn-success btn-arrow-left">이전</a>
			<%
			}
			if (boardDAO.nextPage(pageNumber + 1)) {
			%>
			<a href="board.jsp?pageNumber=<%=pageNumber + 1%>"
				class="btn btn-success btn-arrow-left">다음</a>
			<%
			}
			%>
			<a href="Write.jsp" class="btn">글쓰기</a>
		</div>
	</div>
</body>
</html>