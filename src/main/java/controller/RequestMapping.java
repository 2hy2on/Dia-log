package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.contents.ListContentsController;
import controller.contents.PickContentsController;

import controller.friend.FriendListController;

import controller.diary.DiaryController;
import controller.review.FilterReviewController;
import controller.contents.SearchContentsController;


public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        
        // 메인 컨텐츠 관련 request URI 추가
        mappings.put("/diary", new DiaryController());

        // 메인 컨텐츠 관련 request URI 추가
        mappings.put("/contents/list", new ListContentsController());
        mappings.put("/contents/search", new SearchContentsController());
        mappings.put("/contents/pickContents", new PickContentsController());
        
        // 다이어리 관련 request URI 추가
        mappings.put("/diary/filter", new FilterReviewController());
        // 다이어리 관련 request URI 추가
        
        // 친구 관련 request URI 추가
        mappings.put("/friend/list", new FriendListController());

        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}