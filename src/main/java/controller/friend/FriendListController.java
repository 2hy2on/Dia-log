package controller.friend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.DispatcherServlet;
import model.dto.friend.Follow;
import model.dto.user.User;
import model.service.friend.FriendManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;

public class FriendListController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FriendManager friendManager = FriendManager.getInstance();

		// FriendListcontroller 세부기능 분류 후, JSON 생성
		if (request.getServletPath().equals("/friend/list/follower")) {
			int userId = 4;
			List<Follow> followerList = friendManager.getFollowers(userId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(followerList);
			logger.debug("followerList in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;

		} else if (request.getServletPath().equals("/friend/list/followee")) {
			int userId = 4;
			List<Follow> followeeList = friendManager.getFollowees(userId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(followeeList);
			logger.debug("followeeList in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/request")) {
			int userId = 4;
			List<Follow> unacceptedFriends = friendManager.getUnacceptedFriends(userId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(unacceptedFriends);
			logger.debug("unacceptedFreindsList in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/search")) {
			int userId = 4;
			String searchTerm = request.getParameter("searchTerm");
			List<User> searchFriendList = friendManager.searchFriend(searchTerm);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(searchFriendList);
			logger.debug("searchFriendList in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/list/recommend")) {
			int userId = 66;

			List<User> recommendationList = friendManager.recsFriend(userId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(recommendationList);
			logger.debug("recommendationList in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/delete/followee")) {
			int userId = 4;
			String followeeName = request.getParameter("followeeName");
			User friend = friendManager.getUserInfoByName(followeeName);
			int friendId = friend.getUserID();
			logger.debug("deleteResult(Follower) in JSON : {}", friendId);

			Boolean deleteResult = friendManager.deleteFollowee(userId, friendId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(deleteResult);
			logger.debug("deleteResult(Followee) in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/delete/follower")) {
			int userId = 4;
			String followerName = request.getParameter("followerName");
			User friend = friendManager.getUserInfoByName(followerName);
			int friendId = friend.getUserID();
			logger.debug("deleteResult(Follower) in JSON : {}", friendId);
			Boolean deleteResult = friendManager.deleteFollower(userId, friendId);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(deleteResult);
			logger.debug("deleteResult(Follower) in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/request/send")) {
			String friendName = request.getParameter("friendName");
			User user = friendManager.getUserInfoByName("가영");
			User friend = friendManager.getUserInfoByName(friendName);

			Boolean sendResult = friendManager.sendFollow(user, friend);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(sendResult);
			logger.debug("sendFollow in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		} else if (request.getServletPath().equals("/friend/request/receive")) {
			String friendName = request.getParameter("receivedFriendName");
			User user = friendManager.getUserInfoByName("나영");
			User friend = friendManager.getUserInfoByName(friendName);

			Boolean receiveResult = friendManager.receiveFollow(user.getUserID(), friend.getUserID());

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(receiveResult);
			logger.debug("receiveResult in JSON : {}", jsonString);

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);

			return null;
		}

		return "/friend/following.jsp";
	}
}
