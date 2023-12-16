package controller.contents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;

import controller.Controller;
import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;

public class PickContentsController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(PickContentsController.class);
    
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContentsDAO dao = new ContentsDAO();

        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        
        String contentIdStr = request.getParameter("contentId");
        int contentId = Integer.parseInt(contentIdStr);
        
//        boolean pickContent = dao.pickContent(userId, contentId);
//        request.setAttribute("pickContent", pickContent);

		logger.debug("userId: "+userIdStr, "contentId: "+contentIdStr);
        
		Contents contentList = dao.getContentsById(contentId);
        request.setAttribute("contentList", contentList);

        return "/contents/Contents.jsp";
    }
    
}
