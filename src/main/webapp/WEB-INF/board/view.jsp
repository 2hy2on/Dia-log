<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import="model.dao.board.BoardDAO"%>
<%@ page import="model.dto.board.Board"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String ID = null;
	if (session.getAttribute("ID") != null) {
	    ID = (String) session.getAttribute("ID");
	}
	int boardID = 0;
	if (request.getParameter("boardID") != null) {
	    boardID = Integer.parseInt(request.getParameter("boardID"));
	}
	if (boardID == 0) {
	    PrintWriter script = response.getWriter();
	    script.println("<script>");
	    script.println("alert('유효하지 않은 글입니다')");
	    script.println("location.href = 'board.jsp'");
	    script.println("</script>");
	}
	Board board = new BoardDAO().getBoard(boardID);
	%>
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; textalign: center;">게시판
							글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<td>글 제목</td>
					<td colspan = "2"><%=board.getBoardTitle()%></td>
					</tr>
					<tr>
					<td>작성자</td>
					<td colspan = "2"><%= board.getID()%></td>
					</tr>
					<tr>
					<td>작성일자</td>
					<td colspan="2"><%= board.getBoardDate().substring(0,11) + board.getBoardDate().substring(11,13) + "시" + board.getBoardDate().substring(14,16) + "분" %></td>
					</tr>
					<tr>
					<td>내용</td>
					<td colspan = "2" style= "min-height:200px; text-align: left;"><%= board.getBoardContent()%></td>
					</tr>
				</tbody>
			</table>
			<a href= "board.jsp" class = "btn btn-primary">목록</a>
			<%
			if(ID != null && ID.equals(board.getID())){
			%>
			<a href = "update.jsp?boardID=<%=boardID %>" class = "btn btn-primary">수정</a>
			<a href = "deleteAction.jsp?boardID=<%=boardID %>" class = "btn btn-primary">삭제</a>
			<%
			}
			%>
			<input type="submit" value="글쓰기">
			
		</div>
	</div>
</body>
</html>