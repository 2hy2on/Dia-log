package controller.visit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.review.ReviewDiary;
import model.dto.review.ReviewTypeNum;
import model.dto.visit.VisitNum;
import model.service.review.ReviewManager;
import model.service.visit.VisitManager;

public class ReadOverviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	      HttpSession session = request.getSession();
	      int userId = (int) session.getAttribute("userId");
		
		ReviewManager manager = ReviewManager.getInstance();
		VisitManager visitMan = VisitManager.getInstance();
		
		//미디어별 통계
		List<ReviewTypeNum> reviewTypeNumList = manager.getReviewByType(userId);
		
	     ObjectMapper objectMapper = new ObjectMapper();
	     String jsonResult = objectMapper.writeValueAsString(reviewTypeNumList);
////
	     request.setAttribute("reviewTypeNumJsonResult", jsonResult);
	     
	     
	     
	  // 현재 날짜를 문자열로 변환
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     LocalDate currentDate = LocalDate.now();
	     String currentDateString = currentDate.format(formatter);

	     // 한 달 뺀 날짜 계산
	     LocalDate oneMonthAgo = currentDate.minusMonths(1);

	     // 현재 날짜에서 1일을 더해줌
	     LocalDate currentDatePlusOne = currentDate.plusDays(1);

	     // 1일을 더한 날짜를 문자열로 변환
	     String currentDatePlusOneString = currentDatePlusOne.format(formatter);

	     // 한 달 뺀 날짜를 문자열로 변환
	     String oneMonthAgoString = oneMonthAgo.format(formatter);
	     
	     List<VisitNum> visitNum = visitMan.getVisitNum(userId, oneMonthAgoString, currentDatePlusOneString);
////	   // List를 JSON 형태로 변환
	     ObjectMapper objectMapper2 = new ObjectMapper();
	     String jsonVisitNumList = objectMapper2.writeValueAsString(visitNum);


	 	//장르별 통계
		List<ReviewTypeNum> reviewGenreNumList = manager.getReviewByGenreNum(userId);
	     ObjectMapper objectMapper3 = new ObjectMapper();
	     String reviewGenreNumListJson = objectMapper3.writeValueAsString(reviewGenreNumList);
////

	     
	     request.setAttribute("jsonVisitNumList", jsonVisitNumList);
	     request.setAttribute("startDateForVisit", oneMonthAgoString);
	     request.setAttribute("endDateForVisit", currentDateString);
	     request.setAttribute("genreNumList", reviewGenreNumListJson);
	     
		return "/diary/overview.jsp";
	}

}
