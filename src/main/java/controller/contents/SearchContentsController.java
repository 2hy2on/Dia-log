package controller.contents;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;

public class SearchContentsController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(SearchContentsController.class);
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsDAO dao = new ContentsDAO(); // ContentsDAO 인스턴스 생성

		String title = request.getParameter("title");
		List<Contents> searchList = dao.searchContentsByTitle(title);
		
		logger.debug(title);
		
		request.setAttribute("searchList", searchList);

		/* return "redirect:/contents/list"; */
		return "/contents/SearchResults.jsp";
	}

}
