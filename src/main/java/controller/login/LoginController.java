package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get the session
        HttpSession session = request.getSession();

        // Check if the user is already logged in
        if (session.getAttribute("ID") != null) {
            // If already logged in, redirect to the home page
            return "redirect:/contents/list";
        }

        // Get user input from the login form
        String userID = request.getParameter("ID");
        String password = request.getParameter("password");
        
        session.setAttribute("ID", userID);
        // Validate the login credentials
        UserDAO userDAO = new UserDAO();
        int loginResult = userDAO.login(userID, password);

        // Process the login result
        switch (loginResult) {
            case 1:
                // Login successful
                session.setAttribute("ID", userID);
                return "redirect:/contents/list"; // Redirect to the home page
            case 0:
                // Password incorrect
                request.setAttribute("errorMessage", "비밀번호가 틀렸습니다.");
                return "/login/login.jsp"; // Show login page with error message
            case -1:
                // User ID not found
                request.setAttribute("errorMessage", "존재하지 않는 아이디입니다.");
                return "/login/login.jsp"; // Show login page with error message
            case -2:
                // Database error
                request.setAttribute("errorMessage", "데이터베이스 오류가 발생했습니다.");
                return "/login/login.jsp"; // Show login page with error message
            default:
                // Unexpected result
                request.setAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
                return "/login/login.jsp"; // Show login page with error message
        }
    }
}