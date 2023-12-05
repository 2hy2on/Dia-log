package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.DispatcherServlet;

public class FilterReviewController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        // Contents.jsp로 포워딩
        return "/diary/filtering.jsp";
    }
    
}
