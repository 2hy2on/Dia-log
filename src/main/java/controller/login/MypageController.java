package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.user.User;

public class MypageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 정보를 가져옴
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 사용자 객체를 가져옴

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (user == null) {
            return "/login/login.jsp";      
        }
        // 여기에 글쓰기 관련 로직을 추가할 수 있습니다.

        // 글쓰기가 완료된 후에는 다시 목록 페이지로 이동하도록 지정
        return "/login/Mypage.jsp";  // 또는 다른 페이지로 리다이렉트하도록 변경 가능
    }
}