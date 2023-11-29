package model.dao.contents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dao.JDBCUtil;
import model.dto.contents.Contents;
import model.dto.contents.Movie;
import model.dto.contents.Music;

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

	public static void main(String[] args) throws SQLException {
		LocalDate currentDate = LocalDate.now();
		Movie movie = new Movie();
		Contents cont = new Contents();

//		cont.setContentId(4);
//		cont.setContentImg("img4");
//		cont.setContentType(null);
//		cont.setReviews(null);
//		cont.setTitle("title4");
//		cont.setGenre("genre4");
//		cont.setPublishDate(currentDate);

		ContentsDAO dao = new ContentsDAO();
		System.out.println(dao.searchContentsByTitle("title4"));
	}
}
