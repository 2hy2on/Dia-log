package controller.login;

import model.dao.user.UserDAO;
import model.dto.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그인 페이지로 바로 포워딩
        request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
    }
    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자 입력 받기
       
        String userID = request.getParameter("ID");
        String password = request.getParameter("password");

        // 로그인 로직을 UserDAO를 사용하여 구현
        int loginResult = userDAO.login(userID, password);

        // 로그인 성공 여부에 따라 처리
        if (loginResult == 1) {
            request.getSession().setAttribute("ID", userID);

            // 로그인 성공 시 포워딩을 통해 페이지 이동
            request.getRequestDispatcher("/WEB-INF/contents/Contents.jsp").forward(request, response);
        } else {
            // 로그인 실패 시 어떻게 처리할지 추가적인 로직을 구현할 수 있음
            response.sendRedirect(request.getContextPath() + "/login"); // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }
}
