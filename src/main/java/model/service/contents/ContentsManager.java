package model.service.contents;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.dao.contents.BookDAO;
import model.dao.contents.ContentsDAO;
import model.dao.contents.MovieDAO;
import model.dao.contents.MusicDAO;
import model.dto.contents.Book;
import model.dto.contents.Contents;
import model.dto.contents.Movie;
import model.dto.contents.Music;
import model.dto.review.Review;

public class ContentsManager {
	private static ContentsManager contentsMan = new ContentsManager();
	private ContentsDAO contentsDAO;
	private MovieDAO movieDAO;
	private BookDAO bookDAO;
	private MusicDAO musicDAO;

	private ContentsManager() {
		try {
			contentsDAO = new ContentsDAO();
			movieDAO = new MovieDAO();
			bookDAO = new BookDAO();
			musicDAO = new MusicDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static ContentsManager getInstance() {
		return contentsMan;
	}

	public List<Contents> getContentList() throws SQLException {
		return contentsDAO.getContentList();
	}
	
	public List<Contents> searchContentsByTitle(String title) throws SQLException {
		return contentsDAO.searchContentsByTitle(title);
	}
	
//	public boolean pickContent(int userId, int contentsId) throws SQLException {
//		return contentsDAO.pickContent(userId, contentsId);
//	}
	
	public List<Map<String, Object>> getReviewList(int contentId) throws SQLException {
		return contentsDAO.getReviewList(contentId);
	}
	
	public List<Movie> searchMovieByTitle(String title) throws SQLException {
		return movieDAO.searchMovieByTitle(title);
	}
	
	public List<Music> searchMusicByTitle(String title) throws SQLException {
		return musicDAO.searchMusicByTitle(title);
	}
	
	public List<Book> searchBookByTitle(String title) throws SQLException {
		return bookDAO.searchBookByTitle(title);
	}
}
