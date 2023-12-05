package controller.diary;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import model.dto.review.ReviewDiary;

//import model.dto.review.ReviewMonthly;
import model.service.Review.ReviewManager;

public class DiaryController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		  int userId = Integer.parseInt(request.getParameter("userId"));
		//	
		int userId = 3;
//		//
	        ReviewManager manager = ReviewManager.getInstance();
//////
	        List<ReviewDiary> reviewDiaryList = manager.getUserDiary(userId);
	      
//////
//	        // Set the start field as a formatted string
	        for (ReviewDiary review : reviewDiaryList) {
	            review.setStart(review.getFormattedStart());
	        }
////
////	   // List를 JSON 형태로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonResult = objectMapper.writeValueAsString(reviewDiaryList);
////
	        request.setAttribute("jsonResult", jsonResult);
	        
	        
	        return "/diary/CalendarPage.jsp";
	
	}
}
