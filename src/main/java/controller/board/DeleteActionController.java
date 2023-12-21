package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.board.BoardDAO;

public class DeleteActionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userID = (String) request.getSession().getAttribute("ID");

        if (userID == null) {
            request.setAttribute("errorMessage", "로그인을 하세요");
            return "/error.jsp";
        } else {
            int boardID = 0;
            if (request.getParameter("boardID") != null) {
                boardID = Integer.parseInt(request.getParameter("boardID"));
            }

            // 글 삭제를 위해 기존 글 정보 불러오기
            BoardDAO boardDAO = new BoardDAO();
            model.dto.board.Board existingBoard = boardDAO.getBoard(boardID);

            if (existingBoard == null || !userID.equals(existingBoard.getID())) {
                request.setAttribute("errorMessage", "권한이 없습니다.");
                return "/error.jsp";
            }

            // 글 삭제 수행
            int result = boardDAO.delete(boardID);

            if (result == -1) {
                request.setAttribute("errorMessage", "글 삭제에 실패했습니다");
                return "/error.jsp";
            } else {
                // 삭제 성공 시 목록으로 이동
                return "redirect:/board";
            }
        }
    }
}
