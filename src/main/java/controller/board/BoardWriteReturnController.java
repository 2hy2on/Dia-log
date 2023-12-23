package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class BoardWriteReturnController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "redirect:/board/write";
    }
} 
