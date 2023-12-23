package controller.visit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.review.ReviewTypeNum;
import model.dto.visit.VisitNum;
import model.service.review.ReviewManager;
import model.service.visit.VisitManager;

public class ReadVisitorController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return null;
		}

		VisitManager visitMan = VisitManager.getInstance();
		ReviewManager manager = ReviewManager.getInstance();

		// 미디어별 통계
		List<ReviewTypeNum> reviewTypeNumList = manager.getReviewByType(userId);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResult = objectMapper.writeValueAsString(reviewTypeNumList);

		request.setAttribute("reviewTypeNumJsonResult", jsonResult);

		String startDate = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate endDate = LocalDate.parse(endDateStr, formatter);
		LocalDate nextDay = endDate.plusDays(1);
		String nextDayStr = nextDay.format(formatter);

		List<VisitNum> visitNum = visitMan.getVisitNum(userId, startDate, nextDayStr);

		ObjectMapper objectMapper2 = new ObjectMapper();
		String jsonVisitNumList = objectMapper2.writeValueAsString(visitNum);

		request.setAttribute("jsonVisitNumList", jsonVisitNumList);
		request.setAttribute("startDateForVisit", startDate);
		request.setAttribute("endDateForVisit", endDateStr);

		return "/diary/overview.jsp";
	}

}
