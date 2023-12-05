package controller.contents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;

public class SearchContentsController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ContentsDAO dao = new ContentsDAO();

        List<Contents> contentList = dao.searchContentsByTitle("라푼젤");

        // 가져온 데이터를 request에 저장
        request.setAttribute("contentList", contentList);

        // Contents.jsp로 포워딩
        return "/contents/Search.jsp";
    }
    
}
