package controller.login;

import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MypageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 정보를 가져옴
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("ID");

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (userObject == null) {
            return "redirect:/login";
        }

        // 사용자 ID를 가져와서 사용자 정보를 조회
        String userID = (String) userObject;
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserInfo(userID);

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/login";
        }

        // 여기에 마이페이지 관련 로직을 추가
        // request에 사용자 정보를 저장하거나 필요한 작업을 수행

        // 마이페이지로 이동
        return "/login/Mypage.jsp";
    }
}