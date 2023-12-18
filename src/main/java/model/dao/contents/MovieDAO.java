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
		System.out.println(title);
		try {
			// Movie 테이블에서 title에 해당하는 영화 정보를 조회
			String sql = "SELECT * FROM Movie WHERE title LIKE ?";
			String keyword = "%" + title + "%";
			
			Object[] param = new Object[] { keyword };

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
				movie.setContentImg(rs.getString("contentImg"));

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
