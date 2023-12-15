package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ViewBoardController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 여기에 글 상세보기와 관련된 로직을 추가할 수 있습니다.

        // 상세보기가 완료된 후에는 view.jsp로 이동하도록 지정
        return "/board/view.jsp";  // 또는 다른 페이지로 리다이렉트하도록 변경 가능
    }
}