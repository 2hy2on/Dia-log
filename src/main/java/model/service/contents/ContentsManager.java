package model.service.contents;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.dao.contents.ContentsDAO;
import model.dto.contents.Contents;

public class ContentsManager {
	private static ContentsManager contentsMan = new ContentsManager();
	private ContentsDAO contentsDAO;

	private ContentsManager() {
		try {
			contentsDAO = new ContentsDAO();
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
	
	public List<Map<String, Object>> getReviewList(int contentId) throws SQLException {
		return contentsDAO.getReviewList(contentId);
	}

	public List<Contents> searchContentsByTitle(String title) throws SQLException {
		return contentsDAO.searchContentsByTitle(title);
	}

	public List<Contents> searchContentsByGenre(String title, String contentType) throws SQLException {
		return contentsDAO.searchContentsByGenre(title, contentType);
	}
	
	public boolean pickContent(int userId, int contentId) throws SQLException {
		return contentsDAO.pickContent(userId, contentId);
	}
	
	public List<Contents> getListHallOfFame() throws SQLException {
		return contentsDAO.getListHallOfFame();
	}
}
