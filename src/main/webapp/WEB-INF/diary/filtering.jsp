<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.dto.review.Review"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>Filtering</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="<c:url value='/css/diary/reviewFiltering.css' />" type="text/css">
<style>
.card {
	height: 100px; /* Adjust the height as needed */
	width: 100px; /* Adjust the width as needed */
	display: inline-flex;
	position: relative;
	left: 0px;
	background-color: var(--background);
	border-radius: 10px;
	transition: 1000ms all;
	transform-origin: center left;
	box-shadow: 0 5px 12px rgba(0, 0, 0, 0.5);
	outline: 1px solid var(--background);
	overflow: hidden;
}

.gallery {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    background-color: var(--background);
    gap: 16px;
    margin-top: 420px;
    margin-bottom: 200px;
    text-align: center; /* 텍스트를 수평 가운데 정렬합니다. */
}

.card img {
	max-width: 100%;
	max-height: 100%;
	object-fit: cover;
	border-radius: 4px;
}

.card:hover ~.card {
	font-weight: bold;
	cursor: pointer;
	transform: translateX(22px);
}

.card figcaption {
	font-size: 0.6rem;
	position: absolute;
	height: 80px;
	width: 160px;
	display: flex;
	align-items: end;
	background: linear-gradient(to top, rgba(0, 0, 0, 0.9) 0%,
		rgba(0, 0, 0, 0) 100%);
	color: #FCF8EC;
	left: 0px;
	bottom: 0px;
	padding-left: 12px;
	padding-bottom: 10px;
	opacity: 0; /* Initially hide the title */
	transition: opacity 0.5s; /* Add a smooth transition for opacity */
}

.card:hover figcaption {
	opacity: 1; /* Make the title visible on hover */
}

.card:hover {
	cursor: pointer;
	transform: scale(1.15);
}

.container {
    text-align: center; /* 텍스트를 수평 가운데 정렬합니다. */
}

.row {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
}


.col {
    width: 100px !important;
    flex: 0 0 auto !important;
    max-width: 100px !important;
    padding: 0 !important;
    margin: 10px;
}
</style>
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
	
</script>
<script>
	var reviewList;
	var rId;
	$(document).on("click", ".social-icon", function() {
		var contentType = $(this).attr("id");
		$.ajax({
			type : "GET",
			url : "<c:url value='/diary/filter/genre'/>",
			data : {
				contentType : contentType
			},
			cache : false,
			dataType : "json",
			success : function(reviews) {
				// 전역 변수에 할당
				reviewList = reviews;
				// printReviewList 함수 호출
				printReviewList(reviews);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				var message = jqXHR.getResponseHeader("Status");
				if (message == null || message.length <= 0) {
					alert("에러! 요청 상태는 " + jqXHR.status);
				} else {
					alert(message);
				}
			}
		});
	});

	function updateModalContent(reviewId) {
		rId = reviewId
		//localStorage.setItem("reviewId",reviewId)
		var review = reviewList.find(function(r) {

			return r.reviewId === reviewId;
		});

		if (review) {
			// Update modal title
			document.getElementById('exampleModalLabel').innerText = review.title;
			document.getElementById('message-text').innerText = review.detail;

			// Set the checked attribute for the correct radio button
			var star = document.getElementById(review.rate + '-stars');
			star.checked = true
			console.log("=============")
			console.log(localStorage.getItem("dateForReview"))
			document.getElementById('watchedAt').value = localStorage
					.getItem("dateForReview");
		}
	}
	
    function saveReview() {
        var reviewId = rId
        var watchedAt = document.getElementById('watchedAt').value;
        var rating = document.querySelector('input[name="rating"]:checked').value;
        var reviewText = document.getElementById('message-text').value;
        
        var data = {
           reviewId:  String(reviewId),
            watchedAt: watchedAt,
            rating:  String(rating),
            reviewText: reviewText
        };
        
        console.log(data.reviewId)
            swal({
        title: '리뷰 저장하기',
        text: '완료되었습니다.',
        icon: 'success',
    }).then((result) => {
        if (result) {
        	 fetch("<c:url value='/review/update'/>", {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json;charset=UTF-8'
                 },
                 body: JSON.stringify(data)
             })
             .then(response => {
                 if (!response.ok) {
                     throw new Error('Network response was not ok');
                 }
                 // You can handle the response here if needed
                 console.log('Review updated successfully.');
               	//alert("리뷰가 수정됐습니다");
                 // Reload the page
                 
                 location.reload();
             
                 
             })
             .catch(error => {
                 console.error('Error updating review:', error);
             });
        }
    });

       
    }

    function deleteReview() {
        console.log(rId);
        
        swal({
            title: '리뷰 삭제하기',
            text: '완료되었습니다.',
            icon: 'success',
        }).then((result) => {
            if (result) {
            	 fetch("<c:url value='/review/delete'/>?reviewId=" + encodeURIComponent(rId), {
                     method: 'GET',
                     headers: {
                         'Content-Type': 'application/json;charset=UTF-8'
                     },
                 })
                 .then(response => {
                     if (!response.ok) {
                         throw new Error('Network response was not ok');
                     }
                     // You can handle the response here if needed
                     console.log('Review delete successfully.');
                     // Reload the page after a short delay (adjust the delay as needed)
                     setTimeout(function () {
                         location.reload();
                     }, 1000); // 1000 milliseconds = 1 second
                 })
                 .catch(error => {
                     console.error('Error deleting review:', error);
                 });
            }
        });
       
   
    }

	function printReviewList(reviews) {
		// 기존 내용을 지우고 새로운 내용으로 업데이트
		$("#filteredReviewList").empty();
		if (reviews.length > 0) {
			for (var i = 0; i < reviews.length; i++) {
				var review = reviews[i];

				// 동적으로 생성된 HTML 코드 추가
				var dynamicHtml = '<div class="col" onclick="updateModalContent('
						+ review.reviewId
						+ ')">'
						+ '<article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal" id="reviewModal">'
						+ '<figure>'
						+ '<img src="' + review.mediaImg + '" alt="movie">'
						+ '<figcaption>'
						+  '<p class="h6" style="font-size: 0.5rem;">' 
						+ review.title
						+ '</p>'
						+ '</figcaption>'
						+ '</figure>'
						+ '</article>'
						+ '</div>';

				$("#filteredReviewList").append(dynamicHtml);

				// 한 행에 5개의 카드를 출력했을 때 다음 행을 추가
				if ((i + 1) % 5 === 0) {
					$("#filteredReviewList").append('</div><div class="row">');
				}
			}
		} else {
			// 리뷰가 없을 때의 처리
			$("#filteredReviewList").append('<p>아직 리뷰가 없어요 :)</p>');
		}
	}
</script>
<body>
	<header>
		<%@ include file="../Navibar.jsp"%>
	</header>
	<div class="gallery">
		<div class="container text-center">
			<div class="row" id="filteredReviewList"></div>
		</div>
	</div>

	<div class="modal" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">date :</label>
							<input id="watchedAt" type="date">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Review
								Score :</label>
							<div class="star-rating space-x-4 mx-auto">
								<input type="radio" id="5-stars" name="rating" value="5"
									v-model="ratings" /> <label for="5-stars" class="star pr-4">★</label>
								<input type="radio" id="4-stars" name="rating" value="4"
									v-model="ratings" /> <label for="4-stars" class="star">★</label>
								<input type="radio" id="3-stars" name="rating" value="3"
									v-model="ratings" /> <label for="3-stars" class="star">★</label>
								<input type="radio" id="2-stars" name="rating" value="2"
									v-model="ratings" /> <label for="2-stars" class="star">★</label>
								<input type="radio" id="1-star" name="rating" value="1"
									v-model="ratings" /> <label for="1-star" class="star">★</label>
							</div>
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">my
								review:</label>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						onclick="deleteReview()">삭제하기</button>
					<button type="button" class="btn btn-primary"
						onclick="saveReview()">저장하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
<div class="social-icons"
	style="position: absolute; top: 0; left: 0; padding: 10px; top: 190px; left: 10px;">
	<a class="social-icon" href="#!" id="Movie"><i class="bi bi-film"></i></a>
	<a class="social-icon" href="#!" id="Music"><i
		class="bi bi-music-note-beamed"></i></a> <a class="social-icon" href="#!"
		id="Book"><i class="bi bi-book"></i></a>
	<hr>
</div>
</html>