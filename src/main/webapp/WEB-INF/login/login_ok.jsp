<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.dao.user.UserDAO" %>
<%@ page import="model.dto.user.User" %>
<%@page import="java.io.PrintWriter" %>
<jsp:useBean id = "user" class = "model.dto.user.User" scope = "page" />
<jsp:setProperty name = "user" property = "ID" />
<jsp:setProperty name = "user" property = "password" />
<!DOCTYPE html>
<html>
<head>
<title>로그인 성공</title>
</head>
<body>
   <%   
   String trimmedID = user.getID().trim();
   String trimmedPassword = user.getPassword().trim();

   String ID = null;
   if (session.getAttribute("ID") != null){
       ID = (String)session.getAttribute("ID");
   }
   if(ID != null) {
       PrintWriter script = response.getWriter();
       script.println("<script>");
       script.println("alert('이미 로그인이 되어있습니다')");
       response.sendRedirect(request.getContextPath() + "/contents/Contents.jsp");
       script.println("</script>");
   }
   UserDAO userDAO = new UserDAO();
   int result = userDAO.login(trimmedID, trimmedPassword);
      if(result == 1){
          session.setAttribute("ID",user.getID());
          PrintWriter script = response.getWriter();
          script.println("<script>");
          script.println("location.href = '" + request.getContextPath() + "/contents/Contents.jsp'");
          script.println("</script>");
      }
      else if (result == 0) {
          PrintWriter script = response.getWriter();
          script.println("<script>");
          script.println("alert('비밀번호가 틀립니다.')");
          script.println("history.back()");
          script.println("</script>");
      }
      else if (result == -1) {
          PrintWriter script = response.getWriter();
          script.println("<script>");
          script.println("alert('존재하지 않는 아이디입니다.\\n입력한 아이디: " + trimmedID + "\\n입력한 비밀번호: " + trimmedPassword + "')");
          script.println("history.back()");
          script.println("</script>");
      }
      else if (result == -2) {
          PrintWriter script = response.getWriter();
          script.println("<script>");
          script.println("alert('데이터베이스 오류가 발생했습니다.')");
          script.println("history.back()");
          script.println("</script>");
      }
   %>
</body>
</html>