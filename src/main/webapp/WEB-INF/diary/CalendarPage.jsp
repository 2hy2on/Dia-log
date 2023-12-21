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
            font-family: "Helvetica Nueue",Arial,Verdana,sans-serif;
             font-weight: 700;
    		font-style: normal;
    		margin-bottom: 200px;
    		margin-top: 100px;
        }

        #dateName {
            color: #f2a223;
            font-weight: bold;
        }

        .calendarLayer {
            /* Set a fixed width for each section */
            width: 550px;
            
        }
        
        .listLayer {
            margin-right: 100px;
        }
    </style>
    <title>Insert title here</title>
</head>
<div>
<jsp:include page="../Navibar.jsp" /> 

</div>
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
