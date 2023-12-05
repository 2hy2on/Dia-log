package model.dao.contents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.dao.JDBCUtil;
import model.dto.contents.Contents;
import model.dto.contents.Contents.ContentType;
import model.dto.contents.Movie;

public class ContentsDAO {
	private JDBCUtil jdbcUtil = null;

	public ContentsDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/*
	 * public boolean createContents(Contents cont) { StringBuilder query = new
	 * StringBuilder(); String sql =
	 * "INSERT INTO Contents (contentId, contentType, contentImg, title, genre, publishDate) VALUES (?, ?, ?, ?, ?, ?)"
	 * ;
	 * 
	 * // query에 SQL 쿼리 추가 query.append(sql);
	 * 
	 * // JDBCUtil 객체 생성 JDBCUtil jdbcUtil = new JDBCUtil();
	 * 
	 * Object[] param = new Object[] { cont.getContentId(), cont.getContentType(),
	 * cont.getContentImg(), // cont.getReviews(), cont.getTitle(), cont.getGenre(),
	 * cont.getPublishDate() };
	 * 
	 * // query에 대한 SQL 및 매개변수 설정 jdbcUtil.setSqlAndParameters(query.toString(),
	 * param);
	 * 
	 * try { // executeQuery 대신 executeUpdate 사용 int affectedRows =
	 * jdbcUtil.executeUpdate(); if (affectedRows > 0) { return true; } } catch
	 * (Exception ex) { jdbcUtil.rollback(); // 트랜잭션 rollback 실행
	 * ex.printStackTrace(); } finally { jdbcUtil.commit(); // 트랜잭션 commit 실행
	 * jdbcUtil.close(); }
	 * 
	 * return false; }
	 */

	public List<Contents> getContentList() {
		List<Contents> contentList = new ArrayList<>();

		try {
			String sql =  "SELECT c.contentId, c.contentImg, c.contentType, c.title, c.genre, c.publishDate FROM Contents c";

			jdbcUtil.setSqlAndParameters(sql, new Object[] { });

			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Contents cont = new Contents();

				cont.setContentId(rs.getInt("contentId"));
				cont.setContentImg(rs.getString("contentImg"));
				cont.setContentType(ContentType.valueOf(rs.getString("contentType")));
				cont.setTitle(rs.getString("title"));
				cont.setGenre(rs.getString("genre"));
				cont.setPublishDate(rs.getDate("publishDate").toLocalDate());
				
				contentList.add(cont);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return contentList;
	}
	
	public List<Contents> searchContentsByTitle(String title) {
		List<Contents> contentList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Contents WHERE title = ?";
			Object[] param = new Object[] { title };

			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Contents cont = new Contents();

				cont.setContentId(rs.getInt("contentId"));
				cont.setContentImg(rs.getString("contentImg"));
				cont.setReviews(null);
				cont.setContentType(ContentType.valueOf(rs.getString("contentType")));
				cont.setTitle(rs.getString("title"));
				cont.setGenre(rs.getString("genre"));
				cont.setPublishDate(rs.getDate("publishDate").toLocalDate());

				contentList.add(cont);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return contentList;
	}
	
	 public boolean pickContent(int userId, int contentId) {
	      LocalDateTime currentDateTime = LocalDateTime.now();
	      LocalDate currentDate = LocalDate.now();
	      StringBuilder query = new StringBuilder();
	      query.append(
	            "INSERT INTO Review (createdAt, updatedAt, watchedAt, contentId, writerId) VALUES (?, ?, ?,?, ?)");

	      Object[] param = new Object[] {currentDateTime, currentDateTime, currentDate, contentId, userId};

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
	 
	public static void main(String[] args) throws SQLException {
//		LocalDate currentDate = LocalDate.now();
//		Movie movie = new Movie();
//		Contents cont = new Contents();
//
//		cont.setContentId(0);
//		cont.setContentImg("https://ifh.cc/g/2DomCY.jpg");
//		cont.setContentType(ContentType.Movie);
//		cont.setReviews(null);
//		cont.setTitle("라푼젤");
//		cont.setGenre("애니메이션/모험/로맨스");
//		cont.setPublishDate(currentDate);

		ContentsDAO dao = new ContentsDAO();
		System.out.println(dao.getContentList());
	}
}
