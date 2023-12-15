package controller.visit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.review.ReviewDiary;
import model.dto.review.ReviewTypeNum;
import model.service.Review.ReviewManager;

public class ReadOverviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ReviewManager manager = ReviewManager.getInstance();
		
		List<ReviewTypeNum> reviewTypeNumList = manager.getReviewByType(3);
		

////
////	   // List를 JSON 형태로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonResult = objectMapper.writeValueAsString(reviewTypeNumList);
////
	        request.setAttribute("reviewTypeNumJsonResult", jsonResult);
		
		return "/diary/overview.jsp";
	}

}
