package controller.review;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.diary.DiaryController;
import model.dto.review.Review;
import model.service.Review.ReviewManager;

public class UpdateReviewController implements Controller {
	   private static final Logger logger = LoggerFactory.getLogger(UpdateReviewController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	ObjectMapper objectMapper = new ObjectMapper();
//
//      // Read the JSON data from the request body
      Map<String, String> jsonData = objectMapper.readValue(request.getReader(), Map.class);
      String watchedAtStr = jsonData.get("watchedAt");
      String reviewText = jsonData.get("reviewText");
      String reviewIdStr= jsonData.get("reviewId");
      String rateStr = jsonData.get("rating");
//      logger.info("Received datddddeStr: {}",jsonData);
     
   
      int reviewId = Integer.parseInt(reviewIdStr);
      float rating = Float.parseFloat(rateStr);
      
      logger.info("Received datddddeStr: {}",watchedAtStr);
// 
//      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    
//      Date parsedDate = dateFormat.parse(watchedAtStr);
      
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(watchedAtStr, formatter);
		Date date = Date.valueOf(localDate);
      logger.info("Received datddddeStr: {}",date);
      
      ReviewManager manager = ReviewManager.getInstance();
		Review review = new Review();
		
		 review.setReviewId(reviewId);
	     review.setWatchedAt(date);
	     review.setRate(rating);
	     review.setDetail(reviewText);
		
		manager.updateReview(review);
//      logger.info("Received datddddeStr: {}",reviewId);
      
      return "/diary/CalendarPage.jsp";
      
    }
}
