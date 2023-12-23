package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.board.BoardDAO;
import model.dto.board.Board;

public class BoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();

		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}

		ArrayList<Board> list = boardDAO.getList(pageNumber);

		if (pageNumber != 1) {
			request.setAttribute("prevPageNumber", pageNumber - 1);
		}
		if (boardDAO.nextPage(pageNumber + 1)) {
			request.setAttribute("nextPageNumber", pageNumber + 1);
		}

		// 각 게시물의 날짜 형식 변경
		for (Board board : list) {
			formatBoardDate(board);
		}

		request.setAttribute("boardList", list);

		return "/board/board.jsp";
	}

	private void formatBoardDate(Board board) {
		String boardDate = board.getBoardDate();
		if (boardDate != null && boardDate.length() >= 16) {
			board.setBoardDate(
					boardDate.substring(0, 11) + boardDate.substring(11, 13) + "시" + boardDate.substring(14, 16) + "분");
		} else {
			board.setBoardDate("날짜 없음");
		}
	}
}