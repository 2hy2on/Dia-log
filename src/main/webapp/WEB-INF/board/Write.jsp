<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>
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

        .navi {
            width: 200px;
            padding: 10px;
            background-color: #eeeeee;
        }

        .content {
            flex: 1;
            padding: 10px;
            margin-top: 100px; /* 조절 가능한 여백 추가 */
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
       <form method="post" action="/dialog/board/writeAction">
            <table class="table">
                <thead>
                    <tr>
                        <th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" class="form-control" placeholder="글 제목" name="boardTitle" maxlength="50"></td>
                    </tr>
                    <tr>
                        <td><textarea class="form-control" placeholder="글 내용" name="boardContent" maxlength="2048" style="height: 350px;"></textarea></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="글쓰기">
        </form>
    </div>
</body>
</html>