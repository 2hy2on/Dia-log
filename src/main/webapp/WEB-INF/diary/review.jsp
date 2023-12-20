
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.dto.review.Review"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<!DOCTYPE html>
<html>
<div id="reviewContainer">

	<%
            //Retrieve the jsonResult attribute
            List<Review> reviewDateList = (List<Review>) request.getAttribute("reviewDateList");
            
       %>

	<head>
<style>
.gallery {
	width: 400px;
	hegiht: 1000px;
}
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Filtering</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="<c:url value='/css/diary/filtering.css' />"
	type="text/css">
<script src="<c:url value='/js/diary/fullcalendar.js'/>"
	type="text/javascript"></script>
	</head>
	<style>
</style>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">

</script>

	<body>
		<%
// Check if reviewDateList is not null
if (reviewDateList != null) {
   
   ObjectMapper objectMapper = new ObjectMapper();
    String jsonResult = objectMapper.writeValueAsString(reviewDateList);
%>
		<script>
    var reviewList = JSON.parse('<%= jsonResult %>');
    console.log(reviewList)
   var rId 
    function updateModalContent(reviewId) {
       rId = reviewId
       //localStorage.setItem("reviewId",reviewId)
        var review = reviewList.find(function (r) {
 
   
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
                document.getElementById('watchedAt').value = localStorage.getItem("dateForReview");
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
        // Make an AJAX request using fetch
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
            
            // Reload the page
            
            location.reload();
        
            
        })
        .catch(error => {
            console.error('Error updating review:', error);
        });
    }

    function deleteReview() {
        console.log(rId);
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
       
</script>
		<div class="gallery">
			<div class="container text-center">
				<% for (int i = 0; i < reviewDateList.size(); i++) { %>
				<% if (i % 4 == 0) { %>
				<div class="row">
					<% } %>
					<div id="<%= reviewDateList.get(i).getReviewId()%>" class="col"
						onclick="updateModalContent(<%= reviewDateList.get(i).getReviewId()%>)">
						<article class="card" data-bs-toggle="modal"
							data-bs-target="#exampleModal">
							<figure>
								<img src="<%= reviewDateList.get(i).getMediaImg() %>"
									alt="movie">
								<figcaption>
									<p class="h6">

										<%= reviewDateList.get(i).getTitle() %>
									</p>
								</figcaption>
							</figure>
						</article>
					</div>
					<% if (i % 4 == 3 || i == reviewDateList.size() - 1) { %>
				</div>
				<% } %>
				<% } %>
			</div>
		</div>

		<%
} 
if("null".equals(request.getAttribute("ownerId"))){
%>
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
								<label for="recipient-name" class="col-form-label">date
									:</label> <input id="watchedAt" type="date">
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
							<!--                <div class="mb-3">
                     <label for="recipient-name" class="col-form-label">my review:</label>
                     <input type="text" class="form-control" id="recipient-name">
                  </div>
                  -->
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
		<%} %>
	</body>

</div>
</html>