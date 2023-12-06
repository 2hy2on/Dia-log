package controller.friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class FriendListController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        return "/friend/following.jsp";
    }
    
}
