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
        // 여기에 글쓰기 관련 로직을 추가할 수 있습니다.
        // 세션에서 사용자 정보를 가져옴
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("ID");

        // 사용자 ID를 가져와서 사용자 정보를 조회
        String userID = (String) userObject;
        System.out.println("User ID: " + userID);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserInfo(userID);
        if (user == null) {
            // 세션을 초기화하고 새로운 아이디로 다시 로그인
            session.invalidate();
            return "redirect:/login";
        }
        // 글쓰기가 완료된 후에는 다시 목록 페이지로 이동하도록 지정
        return "/board/Write.jsp";  // 또는 다른 페이지로 리다이렉트하도록 변경 가능
    }
}