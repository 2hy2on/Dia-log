package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.diary.DiaryController;
import model.dto.review.Review;
import model.service.review.ReviewManager;

public class ReadReviewForDateController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		String ownerId = request.getParameter("ownerId");
		String dateStr = request.getParameter("dateStr"); // Retrieve data sent via AJAX

		String modifiedDateStr = dateStr.replace("-", "/");

		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewDateList;

		if (!"null".equals(ownerId)) {
			int ownerIdInt = Integer.parseInt(ownerId);
			reviewDateList = manager.getReviewByDate(ownerIdInt, modifiedDateStr);
			request.setAttribute("ownerId", ownerIdInt);
		} else {

			reviewDateList = manager.getReviewByDate(userId, modifiedDateStr);
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("reviewDateList", reviewDateList);

		return "/diary/review.jsp";
	}

}