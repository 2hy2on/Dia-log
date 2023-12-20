package controller.board;

import controller.Controller;
import model.dao.board.BoardDAO;
import model.dto.board.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditBoardController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 정보를 가져옴
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("ID");

        // 게시물 ID 가져오기
        int boardID = Integer.parseInt(request.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO();
        Board board = boardDAO.getBoard(boardID);

        // 로그인한 사용자와 게시물 작성자가 일치하는지 확인
        if (board != null && board.getID().equals(userID)) {
            // 수정 및 삭제를 허용하는 로직 수행
            // (예: request.setAttribute를 사용하여 수정 페이지로 이동하거나 삭제 수행)
            // ...

            return "redirect:/board/editPage";  // 수정 페이지로 이동하도록 수정
        } else {
            // 권한이 없는 경우에 대한 처리
            // (예: 메시지를 설정하고 에러 페이지로 이동)
            // ...

            return "redirect:/errorPage";  // 에러 페이지로 이동하도록 수정
        }
    }
}