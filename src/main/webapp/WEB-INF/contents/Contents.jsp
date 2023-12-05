<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.contents.Contents" %>
<%@ page import="controller.contents.ListContentsController" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dia-log</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @font-face {
            font-family: 'LINESeedKR-Bd';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
            font-weight: 700;
            font-style: normal;
        }

        * {
            font-family: 'LINESeedKR-Bd';
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div>
        <%-- Debugging code to check if contentList is empty --%>
        <% 
            List<Contents> debugList = (List<Contents>)request.getAttribute("contentList");
            if (debugList != null && !debugList.isEmpty()) {
                System.out.println("Debugging contentList: " + debugList);
            } else {
                System.out.println("Debugging contentList is empty or null.");
            }
        %>

        <%@ include file="../Navibar.jsp"%>
        <%@ include file="Search.jsp"%>

<%--         <table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content ID</th>
                    <th>Genre</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty contentList}">
                    <c:forEach var="con" items="${contentList}">
                        <tr>
                            <td>${con.title}</td>
                            <td>${con.contentId}</td>
                            <td>${con.genre}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table> --%>

        <%@ include file="ContentsList.jsp"%>
    </div>
</body>
</html>
