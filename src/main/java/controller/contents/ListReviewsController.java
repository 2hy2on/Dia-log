package controller.contents;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.review.Review;
import model.service.contents.ContentsManager;

public class ListReviewsController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ListReviewsController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsManager manager = ContentsManager.getInstance();

		String contentIdStr = request.getParameter("contentId");
		int contentId = Integer.parseInt(contentIdStr);

		List<Map<String, Object>> reviewList = manager.getReviewList(contentId);
		
//		request.setAttribute("reviewList", reviewList);
//
//		return "/contents/Contents.jsp";
		
        ObjectMapper mapper = new ObjectMapper();
        String jsonReviewList = mapper.writeValueAsString(reviewList);

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonReviewList);
        }

        return null;
	}
}