package model.dao.review;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.JDBCUtil;
import model.dto.review.Review;
import model.dto.review.ReviewMonthly;
import model.dto.review.ReviewTypeNum;

public class ReviewDAO {
	private JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();

	}

	public boolean registerReview(Review review) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDate currentDate = LocalDate.now();
		StringBuilder query = new StringBuilder();
		query.append(
				"INSERT INTO Review (title, content, rate,createdAt, updatedAt, watchedAt, isPrivate, contentId, writerId) VALUES (?, ?, ?,?, ?,?,?,?,?)");

		Object[] param = new Object[] { review.getTitle(), review.getDetail(), review.getRate(), currentDateTime,
				currentDateTime, currentDate, review.isPrivate(), review.getContentId(), review.getWriterId() };

		jdbcUtil.setSqlAndParameters(query.toString(), param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
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

	public List<Review> getReviewByDate(int userId, String dateStr) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM REVIEW WHERE writerId = ? and watchedAt = ?");

		Object[] param = new Object[] { userId, dateStr };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		// Convert string date to java.sql.Date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.parse(dateStr, formatter);
		Date date = Date.valueOf(localDate);

		try {
			List<Review> reviews = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Review review = new Review();

				review.setDetail(rs.getString("detail"));
				review.setContentId(rs.getInt("contentId"));
				review.setCreatedAt(rs.getObject("createdAt", LocalDateTime.class));
//    			review.setMediaImg(rs.getString("mediaImg"));
				review.setPrivate(rs.getBoolean("isPrivate"));
				review.setRate(rs.getFloat("rate"));
				review.setTitle(rs.getString("title"));
				review.setUpdatedAt(rs.getObject("updatedAt", LocalDateTime.class));
				review.setWatchedAt(date);
				review.setWriterId(userId);

				reviews.add(review);
			}
			return reviews;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}

	public boolean deleteReview(int reviewId) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM Review where reviewId =?");

		Object[] param = new Object[] { reviewId };

		jdbcUtil.setSqlAndParameters(query.toString(), param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
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

	public boolean updateReview(int reviewId, Review review) {
		StringBuilder query = new StringBuilder();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDate currentDate = LocalDate.now();
		query.append(
				"UPDATE Review SET title =?, rate=?, watchedAt=?,isPrivate=?, content=?,updatedAt=? where reviewId =?");

		Object[] param = new Object[] { review.getTitle(), review.getRate(), review.getWatchedAt(), review.isPrivate(),
				review.getDetail(), currentDate, reviewId };

		jdbcUtil.setSqlAndParameters(query.toString(), param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
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

	public List<Review> getfilteredReviews(int userId, String genre) {
		StringBuilder query = new StringBuilder();
		query.append(
				"SELECT R.REVIEWID, R.TITLE, R.RATE, R.WATCHEDAT, R.ISPRIVATE, R.LIKECOUNT, R.MEDIAIMG, R.CONTENT, R.CREATEDAT, R.UPDATEDAT, R.CONTENTID, R.WRITERID, C.GENRE ");
		query.append("FROM REVIEW R ");
		query.append("JOIN CONTENTS C ON R.CONTENTID = C.CONTENTID ");
		query.append("WHERE R.WRITERID = ? AND C.GENRE = ?");

		Object[] param = new Object[] { userId, genre };

		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			List<Review> reviews = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Review review = new Review();

				review.setDetail(rs.getString("detail"));
				review.setContentId(rs.getInt("contentId"));
				review.setCreatedAt(rs.getObject("createdAt", LocalDateTime.class));
//    			review.setMediaImg(rs.getString("mediaImg"));
				review.setPrivate(rs.getBoolean("isPrivate"));
				review.setRate(rs.getFloat("rate"));
				review.setTitle(rs.getString("title"));
				review.setUpdatedAt(rs.getObject("updatedAt", LocalDateTime.class));
				review.setWriterId(userId);

				reviews.add(review);
			}
			return reviews;
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}
		return null;
	}
    public List<ReviewMonthly> getReviewByMonth(int userId, int year, int month){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM REVIEW JOIN CONTENTS ON REVIEW.contentId = CONTENTS.contentId WHERE REVIEW.writerId = ? AND EXTRACT(YEAR FROM REVIEW.watchedAt) = ? AND EXTRACT(MONTH FROM REVIEW.watchedAt) =?");

    	
    	Object[] param = new Object[] {userId, year, month};
    	
    	jdbcUtil.setSqlAndParameters(query.toString(), param);
    	


           
    	try {
    		List<ReviewMonthly> reviewMonthly = new ArrayList<>();
    		ResultSet rs = jdbcUtil.executeQuery();
    		
    		while(rs.next()) {
    			ReviewMonthly review = new ReviewMonthly();
    	
    			review.setContentId(rs.getInt("contentId"));
    			review.setContentType("contentType");
    			//review.setPrivate(rs.getBoolean("isPrivate"));
    			review.setTitle(rs.getString("title"));
    			 review.setReviewId(rs.getInt("reviewId"));
    			review.setYear(year);
    			review.setMonth(year);
    	        java.sql.Date watchedDate = rs.getDate("watchedAt");
    	        review.setDay(watchedDate.getDate());

    			
    			
    			reviewMonthly.add(review);
    		}
    		return reviewMonthly;
    	}catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
		return null;
    }
	// 미디어 테이블 생성 후 되는 지 확인 하고 제출하기!!!

//    public List<ReviewTypeNum> getReviewByType(int writerId) {
//    	 StringBuilder query = new StringBuilder();
//
//         query.append("SELECT COUNT(mediaType) AS mediaCount, mediaType");
//         query.append("FROM review, content WHERE review.contentId = content.contentId and review.writerId=? ");
//         query.append("GROUP BY mediaType");
//
//  		Object[] param = new Object[] {writerId};
//  										
//  		jdbcUtil.setSqlAndParameters(query.toString(), param);	// JDBCUtil 에 insert문과 매개 변수 설정
//		
//  		try {
//  			ResultSet rs = jdbcUtil.executeQuery();
//  			List<ReviewTypeNum> list = new ArrayList();
//  			while(rs.next()) {
//  				ReviewTypeNum review = new ReviewTypeNum();
//  				
//  				review.setNum(rs.getInt("mediaCount"));
//  				review.setType(rs.getString("mediaType"));
//  				
//  				list.add(review);
//  				
//  			}
//  			return list;
//  		}
//  		catch (Exception ex) {
//            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
//            ex.printStackTrace();
//        } finally {
//            jdbcUtil.commit();      // 트랜잭션 commit 실행
//            jdbcUtil.close();
//        }
//        return null; 
//    }
//    

//	 public static void main(String[] args) {
//	 Scanner scanner = new Scanner(System.in);
//	 Review re = new Review();
//	 re.setContent("안녕ttttt");
//	 re.setContentId(3);
//	 re.setPrivate(false);
//	 re.setRate(4.0f);
//	 re.setTitle("너무나도ererer 요");
//	 re.setWriterId(3);
//	 re.setWatchedAt(null);
//	 ReviewDAO reDao = new ReviewDAO();
//
//
//        System.out.println(reDao.registerReview(re));
//	 System.out.println(reDao.getReviewByDate(3, "/2023/11/27"));
//	 System.out.println(reDao.deleteReview(8));
//	 System.out.println(reDao.updateReview(12, re));
//        

//        scanner.close();
//	 }

}