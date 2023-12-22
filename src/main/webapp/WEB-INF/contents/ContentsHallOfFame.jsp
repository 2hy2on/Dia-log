<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.dto.contents.Contents"%>
<%@ page import="model.dto.review.Review"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOF</title>
</head>
<style>
.outer {
	width: auto;
	margin: 20px auto;
	overflow-x: hidden;
	display: flex;
	justify-content: center;
	flex-direction: column; /* 수직 정렬을 위한 추가 */
    align-items: center; /* 수직 정렬을 위한 추가 */
}

.HOF {
	text-align: center;
	height: fit-content;
}

.inner-list {
	display: flex;
	transition: .3s ease-out;
	flex-direction: row;
}

.inner {
	border: 3px solid #79A3B1;
	padding: 10px 16px;
	flex-grow: 1;
	box-sizing: border-box;
	display: flex;
	flex-direction: row;
	width: 300px;
	justify-content: space-between;
	margin-bottom: 5px;
}

.imgHOF {
	height: 80px;
	width: 50px;
}

.button-list {
	text-align: center;
}

.social-icon:hover .bi-journal {
	display: block; /* 호버 시에만 보이도록 설정합니다. */
}

.social-icon {
	display: inline-flex;
	margin: 0;
	padding: 0;
	padding-left: 6%;
	height: 3.5rem;
	width: 3.5rem;
	background-color: #79A3B1;
	color: #FCF8EC;
	border-radius: 100%;
	font-size: 1.5rem;
	margin-right: 1.5rem;
	align-items: center;
}
</style>
<body>
	<%
	List<Contents> hallOfFameList = (List<Contents>) request.getAttribute("hallOfFameList");
	System.out.println("hallOfFameList: " + hallOfFameList);
	%>
	<div class="outer">
		<h6 class="HOF">
			HALL OF FAME<br> 카테고리별 1위
		</h6>
		<div class="inner-list">
			<!-- 동적으로 아이템추가 -->
		</div>

	</div>
</body>
<script>
	const outer = document.querySelector('.outer');
	const innerList = document.querySelector('.inner-list');
	const inners = document.querySelectorAll('.inner');
	
	function getListHallOfFame() {
		fetch("<c:url value='/contents/hallOfFame'/>", {
	        method: 'GET',
	        headers: {
	          'Content-Type': 'application/json;charset=UTF-8'
	        },
	      })
	      .then(response => {
	        if (!response.ok) {
	          throw new Error("에러 났다");
	        }
	        return response.json();
	      })
	      .then(data => {
	        console.log('(HallOfFame) Parsed JSON:', data);
	        updateHallOfFame(data);
	      })
	      .catch(error => {
	        console.error('Error content HallOfFame:', error.message);
	        console.error(error.stack);
	      });

	      function updateHallOfFame(hallOfFameData) {
	    	  const innerList = document.querySelector('.inner-list');
	    	  innerList.innerHTML = '';

	    	  hallOfFameData.forEach(item => {
	    		const icon = document.createElement('div');
	    		icon.className = 'icon';

	    		switch(item.contentType) {
	    		case 'Music':
	    			icon.innerHTML = `<i class="bi bi-music-note-beamed"></i>`;
	    			break;
	    		case 'Book':
	    			icon.innerHTML = `<i class="bi bi-book"></i>`;
	    			break;
	    		case 'Movie':
	    			icon.innerHTML = `<i class="bi bi-film"></i>`;
	    			break;
	    		}
	    		icon.style.height = '24px';
	    		icon.style.width = '24px';
	    	    icon.style.marginRight = '5px';
	    	    icon.style.marginLeft = '10px';
	    	    
	    		innerList.appendChild(icon);
	    		
	    	    const inner = document.createElement('div');
	    	    inner.className = 'inner';
	    	    inner.innerHTML = `
	    	    	<div class="contentHOF">
		    	    	<h5>${item.title}</h5>
		                <h6>${item.genre}</h6>
		            </div>
	                <img class="imgHOF" src="${item.contentImg}" alt="Content Image">`;
	    	    innerList.appendChild(inner);
	    	  });
	    	}
	    }

	    window.onload = function() {
	      getListHallOfFame();
	    };
</script>
</html>