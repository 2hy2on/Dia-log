package model.dao.review;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.JDBCUtil;
import model.dto.review.Review;
import model.dto.review.ReviewDiary;
//import model.dto.review.ReviewMonthly;
import model.dto.review.ReviewTypeNum;

public class ReviewDAO {
	private JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();

	}

	public List<Review> getReviewByDate(int userId, String dateStr) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM REVIEW JOIN CONTENTS ON REVIEW.contentId = CONTENTS.contentId WHERE writerId = ? and watchedAt = ?");

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

				review.setReviewId(rs.getInt("reviewId"));
				review.setDetail(rs.getString("detail"));
				review.setContentId(rs.getInt("contentId"));
//				review.setCreatedAt(rs.getObject("createdAt", LocalDateTime.class));
    			review.setMediaImg(rs.getString("contentImg"));
				review.setPrivate(rs.getBoolean("isPrivate"));
				review.setRate(rs.getFloat("rate"));
			
				review.setTitle(rs.getString("title"));
//				review.setUpdatedAt(rs.getObject("updatedAt", LocalDateTime.class));
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

	public boolean updateReview(Review review) {
		StringBuilder query = new StringBuilder();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDate currentDate = LocalDate.now();
		query.append(
				"UPDATE Review SET rate=?, watchedAt=?,isPrivate=?, detail=?,updatedAt=? where reviewId =?");

		Object[] param = new Object[] { review.getRate(), review.getWatchedAt(), review.isPrivate(), review.getDetail(), currentDate, review.getReviewId() };

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
//				review.setTitle(rs.getString("title"));
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
    public List<ReviewDiary> getReviewForDiary(int userId){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM REVIEW JOIN CONTENTS ON REVIEW.contentId = CONTENTS.contentId WHERE REVIEW.writerId = ?");

    	
    	Object[] param = new Object[] {userId};
    	
    	jdbcUtil.setSqlAndParameters(query.toString(), param);
    	

           
    	try {
    		List<ReviewDiary> reviewMonthly = new ArrayList<>();
    		ResultSet rs = jdbcUtil.executeQuery();
    		
    		while(rs.next()) {
    			ReviewDiary review = new ReviewDiary();
    	
    			review.setContentId(rs.getInt("contentId"));
    			review.setContentType("contentType");
//    			review.setPrivate(rs.getBoolean("isPrivate"));
    			review.setTitle(rs.getString("title"));
    			review.setReviewId(rs.getInt("reviewId"));
    	        review.setWatchedAt(rs.getDate("watchedAt"));
    			
    			
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

    public List<ReviewTypeNum> getReviewByType(int writerId) {
    	 StringBuilder query = new StringBuilder();

         query.append("SELECT COUNT(contentType) AS contentCount, contentType FROM review, contents ");
         query.append("WHERE review.contentId = contents.contentId and review.writerId=? GROUP BY contentType ");

       
  		Object[] param = new Object[] {writerId};
  										
  		jdbcUtil.setSqlAndParameters(query.toString(), param);	// JDBCUtil 에 insert문과 매개 변수 설정
		
  		try {
  			ResultSet rs = jdbcUtil.executeQuery();
  			List<ReviewTypeNum> list = new ArrayList();
  			while(rs.next()) {
  				ReviewTypeNum review = new ReviewTypeNum();
  				
  				review.setNum(rs.getInt("contentCount"));
  				review.setType(rs.getString("contentType"));
  				
  				list.add(review);
  				
  			}
  			return list;
  		}
  		catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
        return null; 
    }
    

    
	 public static void main(String[] args) {
//	 Scanner scanner = new Scanner(System.in);
//	 Review re = new Review();
//	 re.setDetail("짱잼!!!!");
//	 re.setContentId(0);
//	 re.setPrivate(false);
//	 re.setRate(4.0f);
//	 
//	 re.setWriterId(3);
//	

	 // re.setWatchedAt에 LocalDate 객체 전달
	
//	// re.setWatchedAt에 LocalDate 객체 전달
//	 String dateString1 = "2023-11-23";
//	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	 try {
//	     Date date1 = new Date(dateFormat.parse(dateString1).getTime()); // java.util.Date를 java.sql.Date로 변환
//	     re.setWatchedAt(date1);
//	 } catch (ParseException e) {
//	     e.printStackTrace();
//	 }
//	
//	 
	 ReviewDAO reDao = new ReviewDAO();
	 System.out.println(reDao.getReviewByType(3));
////
////
////       System.out.println(reDao.getReviewForDiary(1).get(0).getWatchedAt());
//	 System.out.println(reDao.getReviewByDate(3, "2023/11/23"));
////	 System.out.println(reDao.deleteReview(8));
////	 System.out.println(reDao.updateReview(12, re));
//	 System.out.println(reDao.updateReview(re));        

//        scanner.close();
//	 }
	 }
}