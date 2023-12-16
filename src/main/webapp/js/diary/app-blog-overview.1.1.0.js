/*
 |--------------------------------------------------------------------------
 | Shards Dashboards: Blog Overview Template
 |--------------------------------------------------------------------------
 */

'use strict';

(function ($) {
  $(document).ready(function () {
	  
	   // 버튼 클릭 이벤트 처리
   $("button").click(function() {
        // 시작 날짜와 종료 날짜 값을 가져오기
        var startDate = $("#blog-overview-date-range-1").val();
        var endDate = $("#blog-overview-date-range-2").val();
        // 날짜 문자열을 Date 객체로 변환
        if(startDate == null){
			return null
		}
		
				
		// 날짜 문자열을 배열로 분리
		var startSplit = startDate.split("/");
		
		// "YYYY-MM-DD" 형식으로 조립
		var formattedStartDate = startSplit[2] + "-" + startSplit[0].padStart(2, '0') + "-" + startSplit[1].padStart(2, '0');
		
		console.log(formattedStartDate);  // "2023-12-29"
		
		var endSplit = endDate.split("/");
		
		// "YYYY-MM-DD" 형식으로 조립
		var formattedEndDate = endSplit[2] + "-" + endSplit[0].padStart(2, '0') + "-" + endSplit[1].padStart(2, '0');
		
		console.log(formattedStartDate);  // "2023-12-29"
        // AJAX를 사용하여 서버에 데이터 요청
        $.ajax({
            type: "GET", // 또는 GET 등 요청 방식 설정
            url: "/dialog/readVisitor", // 실제 서버 엔드포인트로 변경
            data: {
                startDate: formattedStartDate,
                endDate: formattedEndDate
            },
            success: function(response) {
                // 서버에서 받은 데이터를 처리
                
                console.log(response);
  				//var visitContainer = $('#visitContainer');

				// Insert the modified content into the 'visitContainer'
				$('#overiviewContrainer').html(response);
				$('#dateText').text(formattedStartDate + " ~ " + formattedEndDate);
            },
            error: function(error) {
                console.error("Error:", error);
            }
        });
    });

    // Blog overview date range init.
    $('#blog-overview-date-range').datepicker({});

    //
    // Small Stats
    //

    // Datasets
    var boSmallStatsDatasets = [
      {
		       backgroundColor: 'rgba(231, 243, 250)',
        borderColor: 'rgb(0,123,255)',
        data: [1, 2, 1, 3, 5, 4, 7],
      },
      {
        backgroundColor: 'rgba(121, 163, 177, 0.4)',
        borderColor: 'rgba(121, 163, 177, 1)',
        data: [1, 2, 3, 3, 3, 4, 4]
      },
      {
           backgroundColor: 'rgba(69, 98, 104, 0.4)',
        borderColor: 'rgba(69, 98, 104)',
        data: [2, 3, 3, 3, 4, 3, 3]
      },
      {
        backgroundColor: 'rgba(164, 209, 233,0.2)',
        borderColor: 'rgb( 164, 209, 233)',
        data: [1, 7, 1, 3, 1, 4, 8]
      },
      {
        backgroundColor: 'rgba(208, 232, 242, 0.7)',
       borderColor: 'rgb(0,123,255)',
        data: [3, 2, 3, 2, 4, 5, 4]
      }
    ];

    // Options
    function boSmallStatsOptions(max) {
      return {
        maintainAspectRatio: true,
        responsive: true,
        // Uncomment the following line in order to disable the animations.
        // animation: false,
        legend: {
          display: false
        },
        tooltips: {
          enabled: false,
          custom: false
        },
        elements: {
          point: {
            radius: 0
          },
          line: {
            tension: 0.3
          }
        },
        scales: {
          xAxes: [{
            gridLines: false,
            scaleLabel: false,
            ticks: {
              display: false
            }
          }],
          yAxes: [{
            gridLines: false,
            scaleLabel: false,
            ticks: {
              display: false,
              // Avoid getting the graph line cut of at the top of the canvas.
              // Chart.js bug link: https://github.com/chartjs/Chart.js/issues/4790
              suggestedMax: max
            }
          }],
        },
      };
    }

    // Generate the small charts
    boSmallStatsDatasets.map(function (el, index) {
      var chartOptions = boSmallStatsOptions(Math.max.apply(Math, el.data) + 1);
      var ctx = document.getElementsByClassName('blog-overview-stats-small-' + (index + 1));
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: ["Label 1", "Label 2", "Label 3", "Label 4", "Label 5", "Label 6", "Label 7"],
          datasets: [{
            label: 'Today',
            fill: 'start',
            data: el.data,
            backgroundColor: el.backgroundColor,
            borderColor: el.borderColor,
            borderWidth: 1.5,
          }]
        },
        options: chartOptions
      });
    });


    //
    // Blog Overview Users
    //
    
    
    console.log(visitNumListJson)

	const startDate = new Date(startDateForVisit);
	const endDate = new Date(endDateForVisit);
	const dateList = [];
	const dataNumList = []
	var j = 0
	
	console.log("=============");
	console.log(dateList);
	console.log(dataNumList)
	console.log(endDate)
// startDate부터 endDate까지의 날짜를 하루씩 증가시키며 반복
// startDate부터 endDate까지의 날짜를 하루씩 증가시키며 반복
for (let currentDate = new Date(startDate); currentDate <= endDate; currentDate.setDate(currentDate.getDate() + 1)) {
    // currentDate는 각 반복에서의 날짜입니다.
    console.log("여기!!!!!!");

    // visitNumListJson이 null이면 예외 처리
    if (visitNumListJson && j < visitNumListJson.length && new Date(visitNumListJson[j].formattedStart).getTime() == currentDate.getTime()) {
        dataNumList.push(visitNumListJson[j].num);
        console.log("들어감");
        j++;
    } else {
        console.log("else들어감");
        dataNumList.push(0);
    }
    dateList.push(currentDate.getDate()); // 일(day) 부분을 배열에 추가
}

	// 결과 확인
	console.log("결과");
	console.log(dateList);
	console.log(dataNumList)
    
    var bouCtx = document.getElementsByClassName('blog-overview-users')[0];
    
    // Data
    var bouData = {
      // Generate the days labels on the X axis.
    labels: Array.from(new Array(dateList.length), function (_, i) {
  return dateList[i]; //
}),
      datasets: [{
        label: 'Current Month',
        fill: 'start',
        data: dataNumList,
        backgroundColor: 'rgba(121, 163, 177, 0.3)',
        borderColor: 'rgba(121, 163, 177, 1)',
        pointBackgroundColor: '#ffffff',
        pointHoverBackgroundColor: 'rgb(0,123,255)',
        borderWidth: 1.5,
        pointRadius: 0,
        pointHoverRadius: 3
      }, {
        label: 'Past Month',
        fill: 'start',
        data: [],
        backgroundColor: 'rgba(252, 248, 236, 0.4)',
        borderColor: 'rgba(255,65,105,1)',
        pointBackgroundColor: '#ffffff',
        pointHoverBackgroundColor: 'rgba(255,65,105,1)',
        borderDash: [3, 3],
        borderWidth: 1,
        pointRadius: 0,
        pointHoverRadius: 2,
        pointBorderColor: 'rgba(255,65,105,1)'
      }]
    };

    // Options
    var bouOptions = {
      responsive: true,
      legend: {
        position: 'top'
      },
      elements: {
        line: {
          // A higher value makes the line look skewed at this ratio.
          tension: 0.3
        },
        point: {
          radius: 0
        }
      },
      scales: {
        xAxes: [{
          gridLines: false,
          ticks: {
            callback: function (tick, index) {
              // Jump every 7 values on the X axis labels to avoid clutter.
              return index % 7 !== 0 ? '' : tick;
            }
          }
        }],
        yAxes: [{
          ticks: {
            suggestedMax: 15,
            callback: function (tick, index, ticks) {
              if (tick === 0) {
                return tick;
              }
              // Format the amounts using Ks for thousands.
              return tick > 999 ? (tick/ 1000).toFixed(1) + 'K' : tick;
            }
          }
        }]
      },
      // Uncomment the next lines in order to disable the animations.
      // animation: {
      //   duration: 0
      // },
      hover: {
        mode: 'nearest',
        intersect: false
      },
      tooltips: {
        custom: false,
        mode: 'nearest',
        intersect: false
      }
    };

    // Generate the Analytics Overview chart.
    window.BlogOverviewUsers = new Chart(bouCtx, {
      type: 'line',
      data: bouData,
      options: bouOptions
    });

    // Hide initially the first and last analytics overview chart points.
    // They can still be triggered on hover.
    var aocMeta = BlogOverviewUsers.getDatasetMeta(0);
    aocMeta.data[0]._model.radius = 0;
    aocMeta.data[bouData.datasets[0].data.length - 1]._model.radius = 0;

    // Render the chart.
    window.BlogOverviewUsers.render();

    //
    // Users by device pie chart
    //

    // Data 이게 원통 그림
//    $.ajax({
//  		url: '/dialog/readOverview', // Replace with your actual API endpoint
//  		method: 'GET',
//  		dataType: 'json',
//  		async: false, // Set to false for synchronous request
//  		success: function (data) {
//    	// Assuming your data is an array of values [comic, thrill, action]
//    		var newData = data;
//    		console.log(data)
    	// Call the update function with the new data
    	//updateUsersByDeviceChart(newData);
//  },
//  error: function (error) {
//    console.error('Error fetching data:', error);
//  }
//	});

var dataForUbd = [];
var totalReviewNum = 0.0;
var dataForUbdLabel =[]
var dataForUbdPer = [];
console.log(reviewTypeNumJsonResult)
for (var i = 0; i < reviewTypeNumJsonResult.length; i++) {
  var num = reviewTypeNumJsonResult[i].num;
  dataForUbdLabel.push(reviewTypeNumJsonResult[i].type)
  dataForUbd.push(num);
  totalReviewNum += num;
}
//console.log(dataForUbd)
///console.log(totalReviewNum)

for (var i = 0; i < reviewTypeNumJsonResult.length; i++){
	 dataForUbdPer.push(dataForUbd / totalReviewNum * 100);
}
//console.log(dataForUbdPer)
//console.log(dataForUbdLabel)
 
    var ubdData = {
      datasets: [{
        hoverBorderColor: '#ffffff',
        data: dataForUbdPer,
        backgroundColor: [
          'rgba(121, 163, 177,0.9)',
          'rgba(121, 163, 177,0.5)',
          'rgba(121, 163, 177,0.3)'
        ]
      }],
      labels: dataForUbdLabel
    };

    // Options
    var ubdOptions = {
      legend: {
        position: 'bottom',
        labels: {
          padding: 25,
          boxWidth: 20
        }
      },
      cutoutPercentage: 0,
      // Uncomment the following line in order to disable the animations.
      // animation: false,
      tooltips: {
        custom: false,
        mode: 'index',
        position: 'nearest'
      }
    };

    var ubdCtx = document.getElementsByClassName('blog-users-by-device')[0];

    // Generate the users by device chart.
    window.ubdChart = new Chart(ubdCtx, {
      type: 'pie',
      data: ubdData,
      options: ubdOptions
    });

  });
})(jQuery);
