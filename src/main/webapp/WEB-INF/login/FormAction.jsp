<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.dao.user.UserDAO" %>
<%@ page import="model.dto.user.User" %>
<%@page import="java.io.PrintWriter" %>
<jsp:useBean id = "user" class = "model.dto.user.User" scope = "page" />
<jsp:setProperty name = "user" property = "ID" />
<jsp:setProperty name = "user" property = "password" />
<jsp:setProperty name = "user" property = "userName" />
<jsp:setProperty name = "user" property = "gender" />
<jsp:setProperty name = "user" property = "email" />
<jsp:setProperty name = "user" property = "movie_interest" />
<jsp:setProperty name = "user" property = "book_interest" />
<jsp:setProperty name = "user" property = "music_interest" />
<!DOCTYPE html>
<html>
<head>
<title>로그인 성공</title>
</head>
<body>
	<%   
	request.setCharacterEncoding("UTF-8");
	
    String movieInterest = request.getParameter("Movie_interest");
    String bookInterest = request.getParameter("Book_interest");
    String musicInterest = request.getParameter("Music_interest");
    String gender = request.getParameter("gender");
    
    user.setMovie_interest(movieInterest);
    user.setBook_interest(bookInterest);
    user.setMusic_interest(musicInterest);
    user.setGender(gender);

    System.out.println("ID: " + user.getID());
    System.out.println("Password: " + user.getPassword());
    System.out.println("UserName: " + user.getUserName());
    System.out.println("Gender: " + user.getGender());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Movie_interest: " + user.getMovie_interest());
    System.out.println("Book_interest: " + user.getBook_interest());
    System.out.println("Music_interest: " + user.getMusic_interest());
    
    String ID = null;
    if (session.getAttribute("ID") != null){
        ID = (String)session.getAttribute("ID");
    }
    if(ID != null) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('이미 로그인이 되어있습니다')");
        script.println("location.href = '/dialog/showContents'");
        script.println("</script>");
    }
	    if(user.getID() == null || user.getPassword() == null || user.getUserName() == null
	    || user.getGender() == null || user.getEmail() == null || user.getMovie_interest() == null
	    || user.getBook_interest() == null || user.getMusic_interest() == null) {
	        PrintWriter script = response.getWriter();
	        script.println("<script>");
		    script.println("alert('입력이 안 된 사항이 있습니다.')");
		    script.println("history.back()");
		    script.println("</script>");
	    }else {
		UserDAO userDAO = new UserDAO();
		int result = userDAO.join(user);
		if (result == -1) {
		    PrintWriter script = response.getWriter();
		    script.println("<script>");
		    script.println("alert('이미 존재하는 아이디입니다.')");
		    script.println("history.back()");
		    script.println("</script>");
		}
		else {
		    PrintWriter script = response.getWriter();
		    script.println("<script>");
		    script.println("location.href = 'login.jsp'");
		    script.println("</script>");
		}
	    }
	%>
</body>
</html>