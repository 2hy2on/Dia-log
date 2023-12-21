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

        return "/board/WriteUpdate.jsp"; 
}
}