/*
 |--------------------------------------------------------------------------
 | Shards Dashboards: Blog Overview Template
 |--------------------------------------------------------------------------
 */

'use strict';

(function ($) {
  $(document).ready(function () {

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
    
    
    
    
    var bouCtx = document.getElementsByClassName('blog-overview-users')[0];

    // Data
    var bouData = {
      // Generate the days labels on the X axis.
      labels: Array.from(new Array(30), function (_, i) {
        return i === 0 ? 1 : i;
      }),
      datasets: [{
        label: 'Current Month',
        fill: 'start',
        data: [500, 800, 320, 180, 240, 320, 230, 650, 590, 1200, 750, 940, 1420, 1200, 960, 1450, 1820, 2800, 2102, 1920, 3920, 3202, 3140, 2800, 3200, 3200, 3400, 2910, 3100, 4250],
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
        data: [380, 430, 120, 230, 410, 740, 472, 219, 391, 229, 400, 203, 301, 380, 291, 620, 700, 300, 630, 402, 320, 380, 289, 410, 300, 530, 630, 720, 780, 1200],
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
            suggestedMax: 45,
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
      type: 'LineWithLine',
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
