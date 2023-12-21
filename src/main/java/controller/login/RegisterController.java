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
        response.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=UTF-8");

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

        // 아이디 중복 여부 확인
        UserDAO userDAO = new UserDAO();
        boolean isDuplicateId = userDAO.checkDuplicateId(ID);

        // 회원가입 처리
        if (isDuplicateId) {
            // 이미 존재하는 아이디인 경우
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('이미 존재하는 아이디입니다.');");
            script.println("history.back()");
            script.println("</script>");
            return null;
        } else {
            // 중복이 아니라면 회원가입 처리
            int result = userDAO.join(user);
            if (result == -1) {
                // 회원가입 실패
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('회원가입에 실패했습니다. 다시 시도해주세요.');");
                script.println("history.back()");
                script.println("</script>");
                return null;
            } else {
                // 회원가입 성공 시 로그인 페이지로 이동
                return "redirect:/login";
            }
        }
    }
}