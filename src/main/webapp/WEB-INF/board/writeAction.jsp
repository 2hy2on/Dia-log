<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.board.BoardDAO"%>
<%@page import="java.io.PrintWriter" %>
<jsp:useBean id = "board" class = "model.dto.board.Board" scope = "page" />
<jsp:setProperty name = "board" property = "boardTitle" />
<jsp:setProperty name = "board" property = "boardContent" />
<!DOCTYPE html>
<html>
<head>
<title>jsp 게시판</title>
<style>

</style>
</head>
<body>
	<%   	
	String ID = null;
	if(session.getAttribute("ID") != null){
	    ID = (String) session.getAttribute("ID");
	}
	if(ID == null){
	    PrintWriter script = response.getWriter();
	    script.println("<script>");
	    script.println("alert('로그인을 하세요')");
	    script.println("location.href = 'login.jsp'");
	    script.println("</script>");
	}else{
	    if(board.getBoardTitle() == null || board.getBoardContent() == null) {
	    	        PrintWriter script = response.getWriter();
	    	        script.println("<script>");
	    	        script.println("alert('입력이 안된 사항이 있습니다.')");
	    	        script.println("history.back()");
	    	        script.println("<script>");    	        
	    	    }
	    else{
	        BoardDAO boardDAO = new BoardDAO();
	        int result = boardDAO.write(board.getBoardTitle() , ID, board.getBoardContent());
	    if (result == -1) {
		    PrintWriter script = response.getWriter();
		    script.println("<script>");
		    script.println("alert('글쓰기에 실패했습니다')");
		    script.println("history.back()");
		    script.println("</script>");
		}
		else  {
		    PrintWriter script = response.getWriter();
		    script.println("<script>");
		    script.println("location.href = 'board.jsp'");
		    script.println("</script>");
		}
	    }
	}	
	%>
</body>
</html>