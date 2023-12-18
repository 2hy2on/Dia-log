package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class BoardWriteReturnController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // "/board"로 요청이 오면 "board/list"로 리다이렉트
        return "redirect:/board/write";
    }
} 
