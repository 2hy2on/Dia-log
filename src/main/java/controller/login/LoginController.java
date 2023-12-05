package controller.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showContents(request, response);
    }

    private void showContents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Contents.jsp로 이동
        request.getRequestDispatcher("/WEB-INF/contents/Contents.jsp").forward(request, response);
    }
}