package model.dao.friend;

import java.sql.Connection;


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.dao.JDBCUtil;
import model.dto.friend.Follow;
import model.dto.review.Review;
import model.dto.user.User;

public class FollowDAO {

	private JDBCUtil jdbcUtil = null;

	public FollowDAO() {
		jdbcUtil = new JDBCUtil();

	}

	public List<Follow> getFollowers(int userId) {
		// 나를 팔로우함 팔로워 리스트 가져오기
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM FOLLOW WHERE followeeid= ? AND status = 'Y'");

		Object[] param = new Object[] { userId };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<Follow> followers = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Follow follower = new Follow();

				follower.setFolloweeId(rs.getInt("followeeid"));
				follower.setFolloweeName(rs.getString("followeename"));
				follower.setFollowerId(rs.getInt("followerid"));
				follower.setFollowerName(rs.getString("followername"));
				follower.setFollowId(rs.getInt("followid"));
				follower.setStatus(rs.getString("status"));

				// System.out.println(followeeName + "를 팔로우하는 사람: " + followerName);

				followers.add(follower);
			}
			return followers;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

	public List<Follow> getFollowees(int userId) {
		// 내가 팔로우 함 팔로잉 리스트 가져오기
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM FOLLOW WHERE followerid = ? AND status = 'Y'");

		Object[] param = new Object[] { userId };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<Follow> followees = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Follow followee = new Follow();

				followee.setFolloweeId(rs.getInt("followeeid"));
				followee.setFolloweeName(rs.getString("followeename"));
				followee.setFollowerId(rs.getInt("followerid"));
				followee.setFollowerName(rs.getString("followername"));
				followee.setFollowId(rs.getInt("followid"));
				followee.setStatus(rs.getString("status"));

				// System.out.println(followerName + "가 팔로잉하는 사람: " + followeeName);

				followees.add(followee);
			}
			return followees;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<Follow> getUnacceptedFriends(int userId) {
		// 신청 받지 않은 친구 리스트
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM FOLLOW WHERE followeeid = ? AND status = 'N'");

		Object[] param = new Object[] { userId };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<Follow> unacceptedFriends = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Follow unacceptedFriend = new Follow();

				unacceptedFriend.setFolloweeId(rs.getInt("followeeid"));
				unacceptedFriend.setFolloweeName(rs.getString("followeename"));
				unacceptedFriend.setFollowerId(rs.getInt("followerid"));
				unacceptedFriend.setFollowerName(rs.getString("followername"));
				unacceptedFriend.setFollowId(rs.getInt("followid"));
				unacceptedFriend.setStatus(rs.getString("status"));

				unacceptedFriends.add(unacceptedFriend);
			}
			return unacceptedFriends;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

	public boolean deleteFollower(int userId, int friendId) {
		// 팔로워 목록에서 삭제하기
		int result = 0;

		StringBuilder delete = new StringBuilder();
		delete.append("DELETE FROM FOLLOW ");
		delete.append("WHERE followeeId = ? AND followerId = ?");

		Object[] param = new Object[] { userId, friendId };

		jdbcUtil.setSqlAndParameters(delete.toString(), param);

		try {
//			System.out.println(userId + "의 팔로워 목록에서 " + friendId + "삭제");
			result = jdbcUtil.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return false;
	}

	public boolean deleteFollowee(int userId, int friendId) {
		// 팔로잉 목록에서 삭제하기
		int result = 0;

		StringBuilder delete = new StringBuilder();
		delete.append("DELETE FROM FOLLOW ");
		delete.append("WHERE followeeId = ? AND followerId = ?");

		Object[] param = new Object[] { friendId, userId };

		jdbcUtil.setSqlAndParameters(delete.toString(), param);

		try {
//			System.out.println(userId + "의 팔로잉 목록에서 " + friendId + "삭제");
			result = jdbcUtil.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return false;
	}

	public List<User> searchFriend(String searchFriendName) {
		// 전체 user에서 원하는 친구 찾기 (이름으로 찾기)
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM USER4 ");
		query.append("WHERE username=? ");

		Object[] param = new Object[] { searchFriendName };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<User> searchFriendsList = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				 User user = new User();
	                user.setID(rs.getString("ID"));
	                user.setPassword(rs.getString("PASSWORD"));
	                user.setUserName(rs.getString("userName"));
	                user.setGender(rs.getString("GENDER"));
	                user.setEmail(rs.getString("Email"));
	                user.setMovie_interest(rs.getString("MOVIE_INTEREST"));
	                user.setBook_interest(rs.getString("BOOK_INTEREST"));
	                user.setMusic_interest(rs.getString("MUSIC_INTEREST"));

				searchFriendsList.add(user);
			}
			return searchFriendsList;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

	public List<User> recsFriend(int userId) {
		// 취향별 맞는 친구 추천 리스트 가져오기
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM USER4 ");
		query.append("WHERE USERID <> ? ");
		query.append("  AND (MOVIE_INTEREST = (SELECT MOVIE_INTEREST FROM USER4 WHERE USERID = ?) ");
		query.append("       OR BOOK_INTEREST = (SELECT BOOK_INTEREST FROM USER4 WHERE USERID = ?) ");
		query.append("       OR MUSIC_INTEREST = (SELECT MUSIC_INTEREST FROM USER4 WHERE USERID = ?)) ");

		Object[] param = new Object[] { userId, userId, userId, userId };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<User> recsFriends = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				User friend = new User();

				friend.setBook_interest(rs.getString("BOOK_INTEREST"));
				friend.setMovie_interest(rs.getString("MOVIE_INTEREST"));
				friend.setMusic_interest(rs.getString("MUSIC_INTEREST"));
				friend.setUserName(rs.getString("username"));
				friend.setPassword(null);
				friend.setID(rs.getString("id"));
				friend.setEmail(rs.getString("email"));
				friend.setGender(rs.getString("gender"));

				recsFriends.add(friend);
			}
			return recsFriends;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

	public boolean sendFollow(User user, User friend) {
		// 친구 추가 신청 보내기
		int count = 0;
		int result = 0;

		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM FOLLOW WHERE followerid = ? AND followeeid = ?");
			Object[] param = new Object[] { user.getUserID(), friend.getUserID() };
			jdbcUtil.setSqlAndParameters(query.toString(), param);
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) { // 실행 결과 레코드 fetch
				System.out.print(rs.getString("followid")+ " ");
				count++;
			}
			System.out.print(count);

			if (count == 0) {
				StringBuilder insert = new StringBuilder();
				insert.append(
						"INSERT INTO FOLLOW (followId, followerId, followerName, followeeId, followeeName, status) ");
				insert.append("VALUES (followid_sequence.nextval, ?, ?, ?, ?, 'N') ");

				jdbcUtil.setSqlAndParameters(insert.toString(), new Object[] { user.getUserID(), user.getUserName(),
						friend.getUserID(), friend.getUserName() });
				result = jdbcUtil.executeUpdate();

				System.out.println(user.getUserName() + "이 " + friend.getUserName() + "에게 친구신청");
				return true;
			} else {
				System.out.println("이미 친구 신청 했습니다.");
				return false;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return false;
	}

	public boolean receiveFollow(int userId, int friendId) {
		// 친구 신청 승낙하기
		int result = 0;

		StringBuilder update = new StringBuilder();
		update.append("UPDATE FOLLOW SET status = 'Y' ");
		update.append("WHERE followerid = ? AND followeeid = ? ");

		Object[] param = new Object[] { friendId, userId };

		jdbcUtil.setSqlAndParameters(update.toString(), param);

		try {
//			System.out.println(userId + "이 " + friendId + "친구수락");
			result = jdbcUtil.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return false;

	}
	
	public User getUserInfoByName(String userName) {
		// 전체 user에서 원하는 친구 찾기 (이름으로 찾기)
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM USER4 ");
		query.append("WHERE username=? ");

		Object[] param = new Object[] { userName };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			 User user = new User();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
	                user.setID(rs.getString("ID"));
	                user.setPassword(rs.getString("PASSWORD"));
	                user.setUserName(rs.getString("userName"));
	                user.setGender(rs.getString("GENDER"));
	                user.setEmail(rs.getString("Email"));
	                user.setMovie_interest(rs.getString("MOVIE_INTEREST"));
	                user.setBook_interest(rs.getString("BOOK_INTEREST"));
	                user.setMusic_interest(rs.getString("MUSIC_INTEREST"));
	                user.setUserID(rs.getInt("USERID"));
			}
			return user;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		FollowDAO followDAO = new FollowDAO();
//
//	}
//		Iterator<User> iterator = followeeList.iterator();
//		while (iterator.hasNext()) {
//			User user = iterator.next();
//			System.out.println(user.getUserName() + user.getID() + user.getPassword() + "내가 찾은 사람");
//			System.out.println();
//		}
//	}
//		List<Follow> followList = followDAO.getFollowers(4);
//		List<Follow> followeeList = followDAO.getFollowees(4);
//
//		Iterator<Follow> iterator = followList.iterator();
//		while (iterator.hasNext()) {
//			Follow follow = iterator.next();
//			System.out.println(follow.getFolloweeName() + "를 팔로우하는 사람: " + follow.getFollowerName());
//			System.out.println();
//		}
//	}

//		Iterator<Follow> iterator1 = followeeList.iterator();
//		while (iterator1.hasNext()) {
//			Follow followee = iterator1.next();
//			System.out.println(followee.getFollowerName() + "가 팔로잉하는 사람: " + followee.getFolloweeName());
//			System.out.println();
//		}
//		System.out.println(followDAO.deleteFollowee(3, 2));
//		System.out.println(followDAO.receiveFollow(2, 5));

//		List<User> userlist = followDAO.recsFriend(4);
//		System.out.println(userlist.size());
//		Iterator<User> iterator2 = userlist.iterator();
//		while (iterator2.hasNext()) {
//			User user = iterator2.next();
//			System.out.println(user.getBook_interest() + user.getMovie_interest() + user.getUserName());
//			System.out.println();
//		}
//		User user = new User();
//		user.setUserID(5);
//		User friend = new User();
//		friend.setUserID(0);
//		
//		
//		System.out.println(followDAO.sendFollow(user, friend));
//		scanner.close();
//	}
}