package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class RegisterPageController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 여기에 글쓰기 관련 로직을 추가할 수 있습니다.

        // 글쓰기가 완료된 후에는 다시 목록 페이지로 이동하도록 지정
        return "/login.jsp";  // 또는 다른 페이지로 리다이렉트하도록 변경 가능
    }
}
