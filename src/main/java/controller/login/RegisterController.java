package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class RegisterController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // 사용자가 입력한 회원가입 정보 받아오기
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String ID = request.getParameter("ID");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String movieInterest = request.getParameter("Movie_interest");
        String bookInterest = request.getParameter("Book_interest");
        String musicInterest = request.getParameter("Music_interest");

        // 회원 정보 객체 생성
        User user = new User();
        user.setUserName(userName);
        user.setGender(gender);
        user.setID(ID);
        user.setPassword(password);
        user.setEmail(email);
        user.setMovie_interest(movieInterest);
        user.setBook_interest(bookInterest);
        user.setMusic_interest(musicInterest);

        // 회원가입 처리
        UserDAO userDAO = new UserDAO();
        int result = userDAO.join(user);

        // 회원가입 결과에 따라 처리
        if (result == -1) {
            // 이미 존재하는 아이디인 경우
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('이미 존재하는 아이디입니다.')");
            script.println("history.back()");
            script.println("</script>");
            return null;
        } else {
            // 회원가입 성공 시 로그인 페이지로 이동
            return "redirect:/login";
        }
    }
}