package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.board.BoardDAO;

public class WriteActionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userID = (String) request.getSession().getAttribute("ID");

        if (userID == null) {
            request.setAttribute("errorMessage", "로그인을 하세요");
            return "/error.jsp";
        } else {
            String boardTitle = request.getParameter("boardTitle");
            String boardContent = request.getParameter("boardContent");

            if (boardTitle == null || boardContent == null || boardTitle.isEmpty() || boardContent.isEmpty()) {
                request.setAttribute("errorMessage", "입력이 안된 사항이 있습니다.");
                return "/error.jsp";
            } else {
                BoardDAO boardDAO = new BoardDAO();
                int result = boardDAO.write(boardTitle, userID, boardContent);

                if (result == -1) {
                    request.setAttribute("errorMessage", "글쓰기에 실패했습니다");
                    return "/error.jsp";
                } else {
                    return "redirect:/board";
                }
            }
        }
    }
}