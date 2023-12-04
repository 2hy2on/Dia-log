<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <title>Filtering</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
   <link rel="stylesheet" href="<c:url value='/css/diary/filtering.css' />" type="text/css">

</head>
<header>
   <h1>My Review</h1>
   <div class="underline"></div>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
   </script>

<body>
   <div>

   </div>
   <div class="gallery">
      <div class="container text-center">
         <div class="row">
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie10.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">eagle eye</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie11.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">narnia</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie12.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">angel denon</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie13.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">angel denon</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
         </div>
         <div class="row">
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie14.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">다음 카드</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie15.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">또 다른 카드</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie16.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">여기도 카드</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
            <div class="col">
               <article class="card" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  <figure>
                     <img src="./img/movie17.jpg" alt="movie">
                     <figcaption>
                        <p class="h6">마지막 카드</p>
                     </figcaption>
                  </figure>
               </article>
            </div>
         </div>
      </div>
   </div>

   <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
         <div class="modal-content">
            <div class="modal-header">
               <h1 class="modal-title fs-5" id="exampleModalLabel">Title</h1>
               <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
               <form>
                  <div class="mb-3">
                     <label for="recipient-name" class="col-form-label">date :</label>
                     <input type="date">
                  </div>
                  <div class="mb-3">
                     <label for="message-text" class="col-form-label">Review Score :</label>
                     <div class="star-rating space-x-4 mx-auto">
                        <input type="radio" id="5-stars" name="rating" value="5" v-model="ratings" />
                        <label for="5-stars" class="star pr-4">★</label>
                        <input type="radio" id="4-stars" name="rating" value="4" v-model="ratings" />
                        <label for="4-stars" class="star">★</label>
                        <input type="radio" id="3-stars" name="rating" value="3" v-model="ratings" />
                        <label for="3-stars" class="star">★</label>
                        <input type="radio" id="2-stars" name="rating" value="2" v-model="ratings" />
                        <label for="2-stars" class="star">★</label>
                        <input type="radio" id="1-star" name="rating" value="1" v-model="ratings" />
                        <label for="1-star" class="star">★</label>
                     </div>
                  </div>
                  <div class="mb-3">
                     <label for="recipient-name" class="col-form-label">my review:</label>
                     <input type="text" class="form-control" id="recipient-name">
                  </div>
                  <div class="mb-3">
                     <label for="message-text" class="col-form-label">others:</label>
                     <textarea class="form-control" id="message-text"></textarea>
                  </div>
               </form>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-danger">삭제하기</button>
               <button type="button" class="btn btn-primary">저장하기</button>
            </div>
         </div>
      </div>
   </div>
</body>


</html>