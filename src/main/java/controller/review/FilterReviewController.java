package controller.review;

import java.io.PrintWriter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import controller.diary.DiaryController;
import model.dto.review.Review;

import model.service.review.ReviewManager;

public class FilterReviewController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return null;
		}

		if (request.getServletPath().equals("/diary/filter/genre")) {
			String contentType = request.getParameter("contentType");
			ReviewManager reviewmanager = ReviewManager.getInstance();

			List<Review> reviewList = reviewmanager.getfilteredReviews(userId, contentType);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(reviewList);
			logger.debug("filterByGenre in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
			request.setAttribute("reviewList", jsonString);

			return null;
		}

		return "/diary/filtering.jsp";
	}
}
