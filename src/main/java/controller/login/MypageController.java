package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class MypageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 정보를 가져옴
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("user"); // "user"로 세션에 저장된 사용자 정보를 가져옴

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (userObject == null || !(userObject instanceof User)) {
            System.out.println("User Object is null or not an instance of User");
            return "redirect:/login";
        }

        // 여기에 필요한 작업을 추가할 수 있습니다.

        // 사용자 정보를 request에 추가하여 JSP 페이지에서 사용할 수 있도록 함
        request.setAttribute("user", (User) userObject);

        // 글쓰기가 완료된 후에는 다시 목록 페이지로 이동하도록 지정
        return "/login/Mypage.jsp";  // 또는 다른 페이지로 리다이렉트하도록 변경 가능
    }
}