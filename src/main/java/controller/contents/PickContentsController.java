package controller.contents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.*;

import controller.Controller;
import model.service.contents.ContentsManager;

public class PickContentsController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(PickContentsController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsManager manager = ContentsManager.getInstance();
		logger.debug("컨트롤러 들어옴!! ");

		HttpSession session = request.getSession();
//        int userId = (int) session.getAttribute("userId");

		String contentIdStr = request.getParameter("contentId");
		logger.debug("들어옴!! " + contentIdStr);

		int contentId = Integer.parseInt(contentIdStr);

		manager.pickContent(3, contentId);

		return "/contents/Contents.jsp";
	}
}
