<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dto.review.ReviewDiary"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <style>
        .container {
            display: flex;
            flex-direction: row; /* Make the container a row */
            justify-content: space-around; /* Spread items horizontally */
            align-items: center; /* Center items vertically */
            margin: 0 auto; /* Center the container horizontally */
            margin-top: 100px;
        }

        #dateName {
            font-size: 100px;
            color: #f2a223;
            font-weight: bold;
        }

        .calendarLayer {
            width: 400px; /* Set a fixed width for each section */
            margin-left: 20px; /* Adjust the margin as needed */
        }

        .listLayer {
            width: 400px; /* Set a fixed width for each section */
            margin-left: 20px; /* Adjust the margin as needed */
            margin-top: 100px;
        }
    </style>
    <title>Insert title here</title>
</head>

<body>

    <div class="container">
        <div class="calendarLayer">
            <jsp:include page="fullcalendar.jsp" />
        </div>
        <div class="listLayer">
            <jsp:include page="review.jsp" />
        </div>
    </div>

</body>

</html>
