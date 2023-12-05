package model.service.Review;

import java.util.List;

import model.dao.review.ReviewDAO;
import model.dto.review.Review;
import model.dto.review.ReviewDiary;
//import model.dto.review.ReviewMonthly;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;


	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReviewManager getInstance() {
		return reviewMan;
	}
	
	public List<ReviewDiary> getUserDiary(int userId){
		return reviewDAO.getReviewForDiary(userId);
	}
	
	public List<Review> getReviewByDate(int user, String dateStr){
		return reviewDAO.getReviewByDate(user, dateStr);
	}
}
