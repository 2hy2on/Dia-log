package controller.contents;

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
        String contentType = request.getParameter("contentType"); // 추가된 부분

        List<?> searchList = null;

        // contentType에 따라 다른 DAO 메소드 호출
        if ("movie".equals(contentType)) {
            searchList = manager.searchMovieByTitle(title);
        } else if ("music".equals(contentType)) {
            searchList = manager.searchMusicByTitle(title);
        } else if ("book".equals(contentType)) {
            searchList = manager.searchBookByTitle(title);
        } else {
            // 기본적으로는 일반 검색 메소드 호출
            searchList = manager.searchContentsByTitle(title);
        }
        logger.debug(title);

        request.setAttribute("searchList", searchList);

        return "/contents/Contents.jsp";
    }
}
