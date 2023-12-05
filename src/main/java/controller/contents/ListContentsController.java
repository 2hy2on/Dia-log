package controller.contents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dto.contents.Contents;
import model.service.contents.ContentsManager;

public class ListContentsController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ListContentsController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContentsManager manager = ContentsManager.getInstance();
        List<Contents> contentList = manager.getContentList();
        
        for (Contents content : contentList) {
            logger.debug("Content ID: {}, Title: {}, Genre: {}", 
                    content.getContentId(), content.getTitle(), content.getGenre());
        }
        logger.debug("Content List Size: {}", contentList.size());
        
        request.setAttribute("contentList", contentList);

        return "/contents/Contents.jsp";
    }
}
