<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.dao.user.UserDAO" %>
<%@ page import="model.dto.user.User" %>
<%
    String ID = (String)session.getAttribute("ID");
    UserDAO userDAO = new UserDAO();
    User user = userDAO.getUserInfo(ID);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 정보 수정</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            background: #FCF8EC;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .wrap {
            width: 750px;
            padding: 50px;
            box-sizing: border-box;
            background: #fff;
            text-align: center;
        }

        .wrap h1 {
            padding-bottom: 12px;
            border-bottom: 3px solid #D0E8F2;
            font-size: 32px;
            letter-spacing: -2px;
            color: #456268;
            margin-bottom: 50px;
        }

        .wrap form {
            margin-top: 30px;
        }

        .wrap input[type="text"],
        .wrap input[type="password"],
        .wrap input[type="tel"],
        .wrap input[type="email"] {
            padding: 10px;
            width: 100%;
            box-sizing: border-box;
            margin-bottom: 15px;
        }

        .wrap input[type="submit"] {
            width: 100%;
            height: 50px;
            background: #79A3B1;
            color: #fff;
            font-size: 20px;
            border: none;
            border-radius: 25px;
            cursor: pointer;
        }

        .wrap input[type="button"] {
            padding: 6px 10px;
            border: none;
            border-radius: 25px;
            background: #79A3B1;
            font-weight: 600;
            color: #fff;
            cursor: pointer;
        }

        .wrap dt, .wrap dd {
            display: block;
            font-size: 14px;
            margin-bottom: 10px;
        }

        .wrap dl {
            margin-top: 10px;
        }

        .wrap dt {
            width: 100%;
        }

        div {
            width: 500px;
            float: left;
            border: 1px solid;
        }
    </style>
</head>
<body>
    <div class="wrap">
        <h1>내 정보 수정</h1>
        <form action="./myPageUpdate" method="post">  
            <dl>
                <dt>이름</dt>
                <dd>
                    <input type="text" value="<%= user.getUserName() %>" name="Username">
                    <input type="submit" value="이름 수정">
                </dd>  
            </dl>
            <dl>
                <dt>이메일</dt>
                <dd>
                    <input type="email" value="<%= user.getEmail() %>" name="Email">
                    <input type="submit" value="이메일 수정">
                </dd>
            </dl>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="회원탈퇴">
        </form>
    </div>
</body>
</html>