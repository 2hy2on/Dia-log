package controller.contents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;

public class ListContentsController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        // Contents.jsp로 포워딩
        return "/contents/Contents.jsp";
    }
    
}
