package model.dao.contents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.dao.JDBCUtil;
import model.dto.contents.Movie;

public class MovieDAO {
	private JDBCUtil jdbcUtil = null;

	public MovieDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/*
	 * public boolean createMovie(Movie movie, int contentId) {
	 * 
	 * StringBuilder query = new StringBuilder(); String sql =
	 * "INSERT INTO Movie (contentId, actors, summary, audienceNum, director, title, genre, publishDate) "
	 * + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	 * 
	 * query.append(sql);
	 * 
	 * // JDBCUtil 객체 생성 JDBCUtil jdbcUtil = new JDBCUtil();
	 * 
	 * // Contents 테이블에서 title, genre, publishDate 값을 가져오기 List<Movie> contentsList
	 * = getMovieList(contentId); if (contentsList.isEmpty()) {
	 * System.out.println("Contents 테이블에 해당하는 데이터가 없습니다."); return false; }
	 * 
	 * // Contents 테이블의 첫 번째 레코드를 사용 Movie contentsData = contentsList.get(0);
	 * 
	 * // Contents 테이블의 값을 Movie 테이블에 설정 Object[] param = new Object[] {
	 * contentsData.getContentId(), // Contents 테이블의 contentId를 사용
	 * movie.getActors(), movie.getSummary(), movie.getAudienceNum(),
	 * movie.getDirector(), contentsData.getTitle(), // Contents 테이블의 title을 사용
	 * contentsData.getGenre(), // Contents 테이블의 genre을 사용
	 * contentsData.getPublishDate() // Contents 테이블의 publishDate를 사용 };
	 * 
	 * jdbcUtil.setSqlAndParameters(query.toString(), param);
	 * 
	 * try { int affectedRows = jdbcUtil.executeUpdate(); if (affectedRows > 0) {
	 * return true; } } catch (Exception ex) { jdbcUtil.rollback(); // 트랜잭션 rollback
	 * 실행 ex.printStackTrace(); } finally { jdbcUtil.commit(); // 트랜잭션 commit 실행
	 * jdbcUtil.close(); }
	 * 
	 * return false; }
	 */

	public List<Movie> getMovieList(int contentId) {
		List<Movie> movieList = new ArrayList<>();

		try {
			String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate "
					+ "FROM Contents c JOIN Movie m ON c.contentId = m.contentId " + "WHERE c.contentId = ?";

			jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();

				movie.setContentId(rs.getInt("contentId"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(rs.getString("genre"));
				movie.setPublishDate(rs.getDate("publishDate"));
				
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return movieList;
	}

	public List<Movie> getMovieDetail(int contentId) {
		List<Movie> movieDetail = new ArrayList<>();

		try {
			String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate, m.actors, m.summary, m.audienceNum, m.director "
					+ "FROM Contents c JOIN Movie m ON c.contentId = m.contentId " + "WHERE c.contentId = ?";

			jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();

				movie.setContentId(rs.getInt("contentId"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(rs.getString("genre"));
				movie.setPublishDate(rs.getDate("publishDate"));

				movie.setActors(rs.getString("actors"));
				movie.setSummary(rs.getString("summary"));
				movie.setAudienceNum(rs.getInt("audienceNum"));
				movie.setDirector(rs.getString("director"));
				
				movieDetail.add(movie);
			}
		} catch (Exception ex) {
            jdbcUtil.rollback(); // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit(); // 트랜잭션 commit 실행
            jdbcUtil.close();
        }

		return movieDetail;
	}

	public List<Movie> searchMovieByTitle(String title) {
		List<Movie> movieList = new ArrayList<>();

		try {
			// Movie 테이블에서 title에 해당하는 영화 정보를 조회
			String sql = "SELECT * FROM Movie WHERE title = ?";
			Object[] param = new Object[] { title };

			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();

				movie.setContentId(rs.getInt("contentId"));
				movie.setActors(rs.getString("actors"));
				movie.setSummary(rs.getString("summary"));
				movie.setAudienceNum(rs.getInt("audienceNum"));
				movie.setDirector(rs.getString("director"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(rs.getString("genre"));
				movie.setPublishDate(rs.getDate("publishDate"));

				movieList.add(movie);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return movieList;
	}

	public static void main(String[] args) throws SQLException {
		int contentId = 1;
//		
//		Movie movie = new Movie();
//
//		movie.setAudienceNum(1);
//		movie.setActors("백종원");
//		movie.setSummary("summary1");
//
		MovieDAO dao = new MovieDAO();
		System.out.println(dao.getMovieList(contentId)); // contentId 넘김
		System.out.println(dao.getMovieDetail(contentId)); // contentId 넘김
//		System.out.println(dao.createMovie(movie, contentId));
//
//		List<Movie> searchResult = dao.searchMovieByTitle("title");
//		System.out.println(searchResult);
	}
}
