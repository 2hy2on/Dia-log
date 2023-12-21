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
	width: 600px;
	height: 300px;
	margin: 0 auto;
	margin-top: 20px;
	overflow-x: hidden;
}

.inner-list {
	display: flex;
	transition: .3s ease-out;
	height: 100%;
}

.inner {
	border: 6px solid olive;
	padding: 0 16px;
}

.button-list {
	text-align: center;
}
</style>
<body>
	<%
	List<Contents> hallOfFameList = (List<Contents>) request.getAttribute("hallOfFameList");
	System.out.println("hallOfFameList: " + hallOfFameList);
	%>
	<div class="outer">
		<div class="inner-list">
			<!-- 동적으로 아이템추가 -->
		</div>
	</div>

	<div class="button-list">
		<button class="button-left">← Left</button>
		<button class="button-right">Right →</button>
	</div>
</body>
<script>
	const outer = document.querySelector('.outer');
	const innerList = document.querySelector('.inner-list');
	const inners = document.querySelectorAll('.inner');
	let currentIndex = 0; // 현재 슬라이드 화면 인덱스
	
	inners.forEach((inner) => {
	  inner.style.width = `${outer.clientWidth}px`; // inner의 width를 모두 outer의 width로 만들기
	})
	
	innerList.style.width = `${outer.clientWidth * inners.length}px`; // innerList의 width를 inner의 width * inner의 개수로 만들기
	
	/*
	  버튼에 이벤트 등록하기
	*/
	const buttonLeft = document.querySelector('.button-left');
	const buttonRight = document.querySelector('.button-right');
	
	buttonLeft.addEventListener('click', () => {
	  currentIndex--;
	  currentIndex = currentIndex < 0 ? 0 : currentIndex; // index값이 0보다 작아질 경우 0으로 변경
	  innerList.style.marginLeft = `-${outer.clientWidth * currentIndex}px`; // index만큼 margin을 주어 옆으로 밀기
	});
	
	buttonRight.addEventListener('click', () => {
	  currentIndex++;
	  currentIndex = currentIndex >= inners.length ? inners.length - 1 : currentIndex; // index값이 inner의 총 개수보다 많아질 경우 마지막 인덱스값으로 변경
	  innerList.style.marginLeft = `-${outer.clientWidth * currentIndex}px`; // index만큼 margin을 주어 옆으로 밀기
	});
	
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
	    	    const inner = document.createElement('div');
	    	    inner.className = 'inner';
	    	    inner.innerHTML = `<h2>${item.title}</h2>`;
	    	    innerList.appendChild(inner);
	    	  });
	    	}
	    }

	    window.onload = function() {
	      getListHallOfFame();
	    };
</script>
</html>