package controller.contents;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;
import model.service.contents.ContentsManager;

public class SearchContentsController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(SearchContentsController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsManager manager = ContentsManager.getInstance();

		String title = request.getParameter("title");
		String contentType = request.getParameter("contentType");

		logger.debug("contentType: " + contentType);

		List<Contents> searchList = new ArrayList<>();

		if (!contentType.equals("0")) {
			searchList = manager.searchContentsByGenre(title, contentType);
		} else {
			searchList = manager.searchContentsByTitle(title);
		}

		request.setAttribute("searchList", searchList);
		request.setAttribute("from", "search");
		return "/contents/Contents.jsp";
	}

}