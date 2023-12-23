package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.board.BoardDAO;

public class WriteUpdateActionController implements Controller {

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

            BoardDAO boardDAO = new BoardDAO();
            model.dto.board.Board existingBoard = boardDAO.getBoard(boardID);

            if (existingBoard == null || !userID.equals(existingBoard.getID())) {
                request.setAttribute("errorMessage", "권한이 없습니다.");
                return "/error.jsp";
            }

            String boardTitle = request.getParameter("boardTitle");
            String boardContent = request.getParameter("boardContent");

            int result = boardDAO.update(boardID, boardTitle, boardContent);

            if (result == -1) {
                request.setAttribute("errorMessage", "글 수정에 실패했습니다");
                return "/error.jsp";
            } else {
                return "redirect:/board";
            }
        }
    }
}