package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.diary.DiaryController;
import model.dto.review.Review;
import model.dto.review.ReviewDiary;
import model.service.Review.ReviewManager;

public class ReadReviewForDateController implements Controller{
	   private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int userId = 3;
        String dateStr = request.getParameter("dateStr"); // Retrieve data sent via AJAX

        // Further processing...
        String modifiedDateStr = dateStr.replace("-", "/");
//        logger.info("Received dateStr: {}", modifiedDateStr);

	        ReviewManager manager = ReviewManager.getInstance();
//////\
	        List<Review> reviewDateList = manager.getReviewByDate(userId, modifiedDateStr);
	        logger.info("Received dateStr: {}",reviewDateList.get(0).getContentId());
	        request.setAttribute("reviewDateList", reviewDateList);
	        //logger.info("Contents of reviewDateList: {}",  request.getAttribute("reviewDateList"));
		 return "/diary/review.jsp";
	}

}
