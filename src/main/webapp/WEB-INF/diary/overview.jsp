<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="no-js h-100" lang="en">
<div id="overiviewContrainer">
  <head>
  <style>
    .main-content-container {
        margin-left: auto;
        margin-right: auto;
    }
    #space{
    	height: 50px;
    }
    .container-fluid{
font-family: inherit;
  font-size: inherit;
  font-weight: inherit;
    }
    #overiviewContrainer{
    font-family: inherit;
  font-size: inherit;
  font-weight: inherit;
    }
</style>
<script>
  // Set the JSON data as a JavaScript variable
   <%
//Retrieve the jsonResult attribute
String jsonResult = (String) request.getAttribute("reviewTypeNumJsonResult");
String jsonVisitNumList =  (String)request.getAttribute("jsonVisitNumList");
String startDateForVisit = (String)request.getAttribute("startDateForVisit");
String endDateForVisit = (String)request.getAttribute("endDateForVisit");
String genreNumList = (String)request.getAttribute("genreNumList");

%>
var reviewTypeNumJsonResult = JSON.parse('<%= jsonResult %>');
var visitNumListJson = JSON.parse('<%= jsonVisitNumList %>');

var startDateForVisit = '<%= startDateForVisit %>';
var endDateForVisit = '<%= endDateForVisit %>';

var genreNumListJson = JSON.parse('<%= genreNumList%>');
  // Now you can use reviewTypeNumJsonResult in your JavaScript code
  
var resultList = [];

for (var i = 0; i < genreNumListJson.length; i++) {
    var currentGenre = genreNumListJson[i].type;
    var currentNum = genreNumListJson[i].num;

    // 중복 체크
    var existingEntry = resultList.find(item => item[0] === currentGenre);

    if (existingEntry) {
        // 이미 존재하는 경우에만 num 추가
        existingEntry.push(currentNum);
    } else {
        // 존재하지 않는 경우 새로운 entry 추가
        resultList.push([currentGenre, currentNum]);
    }

    // 4개까지만 추가되도록 체크
    if (resultList.length === 5) {
        break;
    }
}
//resultList에서 숫자 부분만을 추출하고 리스트 안에 리스트 형태로 유지
var numListArray = resultList.map(item => item.slice(1));

// resultList에서 Genre 부분만 추출
var genreList = resultList.map(item => item[0]);


// 결과 확인
console.log(numListArray);
console.log(genreList);

var sumList = numListArray.map(list => list.reduce((acc, num) => acc + num, 0));

//결과 확인
console.log("각 리스트의 합:", sumList);

</script>



    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <div>
		<jsp:include page="../Navibar.jsp"/> 
	</div>
	<div id="space">
	</div>
    <title>diary overview</title>
    <meta name="description" content="A high-quality &amp; free Bootstrap admin dashboard template pack that comes with lots of templates and components.">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="<c:url value='/css/diary/shards-dashboards.1.1.0.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/diary/extras.1.1.0.min.css'/>">
    <script async defer src="https://buttons.github.io/buttons.js"></script>
  </head>
  <body class="h-100">

    <div class="container-fluid">
      <div class="row">
        
        <!-- End Main Sidebar -->
        <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0" style="margin-left: auto; margin-right: auto;">
          
          <!-- / .main-navbar -->
    <div class="main-content-container container-fluid px-4" style="margin-left: auto; margin-right: auto;">
            <!-- Page Header -->
            <div class="page-header row no-gutters py-4">
              <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                <span class="text-uppercase page-subtitle">Dashboard</span>
                <h3 class="page-title">Diary Overview</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Small Stats Blocks -->
            <div class="row">
              <div class="col-lg col-md-6 col-sm-6 mb-4">
                <div class="stats-small stats-small--1 card card-small">
                  <div class="card-body p-0 d-flex">
                    <div class="d-flex flex-column m-auto">
                      <div class="stats-small__data text-center">
                        <span id="genreName1" class="stats-small__label text-uppercase"></span>
                        <h6 id="sum1" class="stats-small__value count my-3"></h6>
                      </div>
                      <div class="stats-small__data">
                      
                      </div>
                    </div>
                    <canvas height="120" class="blog-overview-stats-small-1"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-lg col-md-6 col-sm-6 mb-4">
                <div class="stats-small stats-small--1 card card-small">
                  <div class="card-body p-0 d-flex">
                    <div class="d-flex flex-column m-auto">
                      <div class="stats-small__data text-center">
                        <span id="genreName2" class="stats-small__label text-uppercase"></span>
                        <h6 id="sum2" class="stats-small__value count my-3"></h6>
                      </div>
                      <div class="stats-small__data">
                       
                      </div>
                    </div>
                    <canvas height="120" class="blog-overview-stats-small-2"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-lg col-md-4 col-sm-6 mb-4">
                <div class="stats-small stats-small--1 card card-small">
                  <div class="card-body p-0 d-flex">
                    <div class="d-flex flex-column m-auto">
                      <div class="stats-small__data text-center">
                        <span id="genreName3" class="stats-small__label text-uppercase"></span>
                        <h6 id="sum3" class="stats-small__value count my-3"></h6>
                      </div>
                      <div class="stats-small__data">
                       
                      </div>
                    </div>
                    <canvas height="120" class="blog-overview-stats-small-3"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-lg col-md-4 col-sm-6 mb-4">
                <div class="stats-small stats-small--1 card card-small">
                  <div class="card-body p-0 d-flex">
                    <div class="d-flex flex-column m-auto">
                      <div class="stats-small__data text-center">
                        <span id="genreName4" class="stats-small__label text-uppercase"></span>
                        <h6 id="sum4" class="stats-small__value count my-3"></h6>
                      </div>
                      <div class="stats-small__data">
                    
                      </div>
                    </div>
                    <canvas height="120" class="blog-overview-stats-small-4"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-lg col-md-4 col-sm-12 mb-4">
                <div class="stats-small stats-small--1 card card-small">
                  <div class="card-body p-0 d-flex">
                    <div class="d-flex flex-column m-auto">
                      <div class="stats-small__data text-center">
                        <span id="genreName5" class="stats-small__label text-uppercase"></span>
                        <h6 id="sum5" class="stats-small__value count my-3"></h6>
                      </div>
                      <div class="stats-small__data">
                        
                      </div>
                    </div>
                    <canvas height="120" class="blog-overview-stats-small-5"></canvas>
                  </div>
                </div>
              </div>
            </div>
            <!-- End Small Stats Blocks -->
            <div class="row">
              <!-- Users Stats -->
              <div class="col-lg-8 col-md-12 col-sm-12 mb-4">
                <div class="card card-small">
                  <div class="card-header border-bottom">
                    <h6 id="dateText" class="m-0">Users</h6>
                  </div>
                  <div class="card-body pt-0">
                    <div class="row border-bottom py-2 bg-light">
                      <div class="col-12 col-sm-6">
                        <div id="blog-overview-date-range" class="input-daterange input-group input-group-sm my-auto ml-auto mr-auto ml-sm-auto mr-sm-0" style="max-width: 350px;">
                          <input type="text" class="input-sm form-control" name="start" placeholder="Start Date" id="blog-overview-date-range-1">
                          <input type="text" class="input-sm form-control" name="end" placeholder="End Date" id="blog-overview-date-range-2">
                          <span class="input-group-append">
                            <!-- <span class="input-group-text">
                              <i class="material-icons">î¤</i>
                            </span> -->
                          </span>
                        </div>
                      </div>
                      <div class="col-12 col-sm-6 d-flex mb-2 mb-sm-0">
                        <button type="button" class="btn btn-sm btn-white ml-auto mr-auto ml-sm-auto mr-sm-0 mt-3 mt-sm-0">View Full Report &rarr;</button>
                      </div>
                    </div>
                    <canvas height="130" style="max-width: 100% !important;" class="blog-overview-users"></canvas>
                  </div>
                </div>
              </div>
              <!-- End Users Stats -->
              <!-- Users By Device Stats -->
              <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
                <div class="card card-small h-100">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">미디어</h6>
                  </div>
                  <div class="card-body d-flex py-0">
                    <canvas height="220" class="blog-users-by-device m-auto"></canvas>
                  </div>
                </div>
              </div>
             
                <!-- End Quick Post -->
              </div>
            </div>
          </main>
      </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script src="https://unpkg.com/shards-ui@latest/dist/js/shards.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Sharrre/2.0.1/jquery.sharrre.min.js"></script>
   <script src="<c:url value='/js/diary/shards-dashboards.1.1.0.min.js'/>"></script>
    <script src="<c:url value='/js/diary/app-blog-overview.1.1.0.js'/>"></script>
  </body>
  </div>
</html>
