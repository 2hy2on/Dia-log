package controller.diary;

import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import model.dto.review.ReviewDiary;
import model.dto.visit.Visit;
import model.service.review.ReviewManager;
import model.service.visit.VisitManager;

public class DiaryController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// friend에서 넘어온 친구 아이디
		String ownerId = request.getParameter("ownerId");

		ReviewManager manager = ReviewManager.getInstance();
		VisitManager visitMan = VisitManager.getInstance();
		List<ReviewDiary> reviewDiaryList;

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return null; // Stop further processing
		}

		if (ownerId != null) {
			int ownerIdInt = Integer.parseInt(ownerId);
			// 방문자 수 넣기
			Visit visit = new Visit();
			visit.setVisitorId(userId);
			// 바꿔야함
			visit.setOwnerId(ownerIdInt);
			visitMan.createVisitor(visit);
			reviewDiaryList = manager.getUserDiary(ownerIdInt);
			request.setAttribute("ownerId", ownerId);

		} else {
			reviewDiaryList = manager.getUserDiary(userId);
		}
		for (ReviewDiary review : reviewDiaryList) {
			review.setStart(review.getFormattedStart());
		}

////	   // List를 JSON 형태로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResult = objectMapper.writeValueAsString(reviewDiaryList);
////
		request.setAttribute("jsonResult", jsonResult);
	
		request.setAttribute("userId", userId);
		
		return "/diary/CalendarPage.jsp";

	}
}