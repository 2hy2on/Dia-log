package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.board.BoardDAO;
import model.dto.board.Board;


public class BoardViewController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "redirect:/board";
    }
}
