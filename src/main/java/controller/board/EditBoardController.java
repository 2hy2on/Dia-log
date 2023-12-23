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
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("ID");

        int boardID = Integer.parseInt(request.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO();
        Board board = boardDAO.getBoard(boardID);

        return "/board/WriteUpdate.jsp"; 
}
}