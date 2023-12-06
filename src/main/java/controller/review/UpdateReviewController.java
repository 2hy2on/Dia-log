package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.review.Review;
import model.dto.review.ReviewDiary;
import model.service.Review.ReviewManager;

public class UpdateReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		ReviewManager manager = ReviewManager.getInstance();
		Review review = new Review();
		
		manager.updateReview(review);
		return null;
	}

}
