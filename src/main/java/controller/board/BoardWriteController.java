package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class BoardWriteController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("ID");

        String userID = (String) userObject;
        System.out.println("User ID: " + userID);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserInfo(userID);
        if (user == null) {
            session.invalidate();
            return "redirect:/login";
        }
        return "/board/Write.jsp";
    }
}