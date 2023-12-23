<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link href="<c:url value='/css/diary/fullcalendar.css' />"
	rel='stylesheet' />
<link href="<c:url value='/css/diary/fullcalendar.print.css' />"
	rel='stylesheet' media='print' />
<script src="<c:url value='/js/diary/jquery-1.10.2.js' />"
	type="text/javascript"></script>
<script src="<c:url value='/js/diary/jquery-ui.custom.min.js' />"
	type="text/javascript"></script>
<script src="<c:url value='/js/diary/fullcalendar.js'/>"
	type="text/javascript"></script>
<script>
<%String jsonResult = (String) request.getAttribute("jsonResult");%>

	$(document).ready(function() {
		var ownerId = (<%=(String) request.getAttribute("ownerId")%>)
	    var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		localStorage.setItem("ownerId", ownerId)
		console.log(localStorage.getItem("ownerId", ownerId))
		 var events = JSON.parse('<%=jsonResult%>
	');

				var dateForReview = localStorage.getItem("dateForReview");

				$('#external-events div.external-event').each(function() {

					var eventObject = {
						title : $.trim($(this).text())
					};

					$(this).data('eventObject', eventObject);

					$(this).draggable({
						zIndex : 999,
						revert : true, 
						revertDuration : 0
					});

				});

				/* 캘린더 초기화
				-----------------------------------------------------------------*/

				var calendar = $('#calendar').fullCalendar(
						{
							header : {
								left : 'title',
								center : 'agendaDay,agendaWeek,month',
								right : 'prev,next today'
							},
							editable : true,
							firstDay : 1,
							selectable : true,
							defaultView : 'month',

							axisFormat : 'h:mm',
							columnFormat : {
								month : 'ddd',
								week : 'ddd d',
								day : 'dddd M/d', 
								agendaDay : 'dddd d'
							},
							titleFormat : {
								month : 'MMMM yyyy', 
								week : "MMMM yyyy",
								day : 'MMMM yyyy' 
							},
							allDaySlot : false,
							selectHelper : true,
							select : function(start, end, allDay) {
								var title = prompt('Event Title:');
								if (title) {
									calendar.fullCalendar('renderEvent', {

										title : title,
										start : start,
										end : end,
										allDay : allDay
									}, true 
									);
								}
								calendar.fullCalendar('unselect');
							},
							droppable : true, 
							drop : function(date, allDay) { 

								var originalEventObject = $(this).data(
										'eventObject');

								var copiedEventObject = $.extend({},
										originalEventObject);

								copiedEventObject.start = date;
								copiedEventObject.allDay = allDay;

								$('#calendar').fullCalendar('renderEvent',
										copiedEventObject, true);

								if ($('#drop-remove').is(':checked')) {
									$(this).remove();
								}

							},

							events : events,
						});

			});
</script>
<style>
body {
	text-align: center;
	font-size: 10px;
	font-family: "Helvetica Nueue", Arial, Verdana, sans-serif;
}

#wrap {
	
}

#external-events {
	float: left;
	width: 150px;
	padding: 0 10px;
	text-align: left;
}

#external-events h4 {
	font-size: 16px;
	margin-top: 0;
	padding-top: 1em;
}

.external-event { /* try to mimick the look of a real event */
	margin: 10px 0;
	padding: 2px 4px;
	background: #3366CC;
	color: #fff;
	font-size: .85em;
	cursor: pointer;
}

#external-events p {
	margin: 1.5em 0;
	font-size: 11px;
	color: #666;
}

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar {
	float: right;
	width: 600px;
	background-color: #FFFFFF;
	border-radius: 6px;
	box-shadow: 0 1px 2px #C3C3C3;
}

#dateHeader {
	color: #ffffff;
}
</style>
</head>
<body>
	<div id='wrap'>
		<div id="dateHeader"></div>
		<div id='calendar'></div>
		<div style='clear: both'></div>
	</div>
</body>
</html>
