package model.service.friend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.friend.FollowDAO;
import model.dao.review.ReviewDAO;
import model.dao.user.UserDAO;
import model.dto.friend.Follow;
import model.dto.user.User;

public class FriendManager {
	private static FriendManager friendMan = new FriendManager();
	private FollowDAO followDAO;

	private FriendManager() {
		try {
			followDAO = new FollowDAO();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FriendManager getInstance() {
		return friendMan;
	}

	public List<Follow> getFollowers(int userId) throws SQLException {
		return followDAO.getFollowers(userId);
	}

	public List<Follow> getFollowees(int userId) throws SQLException {
		return followDAO.getFollowees(userId);
	}

	public List<Follow> getUnacceptedFriends(int userId) throws SQLException {
		return followDAO.getUnacceptedFriends(userId);
	}

	public boolean deleteFollower(int userId, int friendId) throws SQLException {
		return followDAO.deleteFollower(userId, friendId);
	}

	public boolean deleteFollowee(int userId, int friendId) throws SQLException {
		return followDAO.deleteFollowee(userId, friendId);
	}

	public List<User> searchFriend(String searchFriendName) throws SQLException {
		return followDAO.searchFriend(searchFriendName);
	}

	public List<User> recsFriend(int userId) throws SQLException {
		return followDAO.recsFriend(userId);
	}

	public boolean sendFollow(User user, User friend) throws SQLException {
		return followDAO.sendFollow(user, friend);
	}

	public boolean receiveFollow(int userId, int friendId) throws SQLException {
		return followDAO.receiveFollow(userId, friendId);
	}
	public User getUserInfoByName(String userName) throws SQLException {
		return followDAO.getUserInfoByName(userName);
	}
	public User getUserInfoByUserID(int userid) throws SQLException {
		return followDAO.getUserInfoByUserID(userid);
	}
}
