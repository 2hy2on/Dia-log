package model.dao.contents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.JDBCUtil;
import model.dto.contents.Music;

public class MusicDAO {
	private JDBCUtil jdbcUtil = null;

	public MusicDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/*
	 * public boolean createMusic(Music music, int contentId) {
	 * 
	 * StringBuilder query = new StringBuilder(); String sql =
	 * "INSERT INTO Music (contentId, singer, album, title, genre, publishDate) " +
	 * "VALUES (?, ?, ?, ?, ?, ?)";
	 * 
	 * query.append(sql);
	 * 
	 * // JDBCUtil 객체 생성 JDBCUtil jdbcUtil = new JDBCUtil();
	 * 
	 * // Contents 테이블에서 title, genre, publishDate 값을 가져오기 List<Music> contentsList
	 * = getMusicList(contentId); if (contentsList.isEmpty()) {
	 * System.out.println("Contents 테이블에 해당하는 데이터가 없습니다."); return false; }
	 * 
	 * // Contents 테이블의 첫 번째 레코드를 사용 Music contentsData = contentsList.get(0);
	 * 
	 * // Contents 테이블의 값을 Music 테이블에 설정 Object[] param = new Object[] {
	 * contentsData.getContentId(), // Contents 테이블의 contentId를 사용
	 * music.getSinger(), music.getAlbum(), contentsData.getTitle(), // Contents
	 * 테이블의 title을 사용 contentsData.getGenre(), // Contents 테이블의 genre을 사용
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

	public List<Music> getMusicList(int contentId) {
		List<Music> musicList = new ArrayList<>();

		try {
			String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate "
					+ "FROM Contents c JOIN Movie m ON c.contentId = m.contentId " + "WHERE c.contentId = ?";

			jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Music music = new Music();

				music.setContentId(rs.getInt("contentId"));
				music.setTitle(rs.getString("title"));
				music.setGenre(rs.getString("genre"));
				music.setPublishDate(rs.getDate("publishDate"));

				musicList.add(music);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return musicList;
	}

	public List<Music> getMusicDetail(int contentId) {
		List<Music> musicDetail = new ArrayList<>();

		try {
			String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate, m.singer, m.album "
					+ "FROM Contents c JOIN Music m ON c.contentId = m.contentId " + "WHERE c.contentId = ?";

			jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Music music = new Music();

				music.setContentId(rs.getInt("contentId"));
				music.setTitle(rs.getString("title"));
				music.setGenre(rs.getString("genre"));
				music.setPublishDate(rs.getDate("publishDate"));

				music.setSinger(rs.getString("singer"));
				music.setAlbum(rs.getString("album"));

				musicDetail.add(music);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return musicDetail;
	}

	public List<Music> searchMusicByTitle(String title) {
		List<Music> musicList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Music WHERE title = ?";
			Object[] param = new Object[] { title };

			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Music music = new Music();

				music.setContentId(rs.getInt("contentId"));
				music.setSinger(rs.getString("singer"));
				music.setAlbum(rs.getString("album"));
				music.setTitle(rs.getString("title"));
				music.setGenre(rs.getString("genre"));
				music.setPublishDate(rs.getDate("publishDate"));

				musicList.add(music);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return musicList;
	}

	public List<Music> searchMusicBySinger(String singer) {
		List<Music> musicList = new ArrayList<>();

		try {
			// Movie 테이블에서 title에 해당하는 영화 정보를 조회
			String sql = "SELECT * FROM Music WHERE singer = ?";
			Object[] param = new Object[] { singer };

			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Music music = new Music();

				music.setContentId(rs.getInt("contentId"));
				music.setSinger(rs.getString("singer"));
				music.setAlbum(rs.getString("album"));
				music.setTitle(rs.getString("title"));
				music.setGenre(rs.getString("genre"));
				music.setPublishDate(rs.getDate("publishDate"));

				musicList.add(music);
			}
		} catch (Exception ex) {
			jdbcUtil.rollback(); // 트랜잭션 rollback 실행
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit(); // 트랜잭션 commit 실행
			jdbcUtil.close();
		}

		return musicList;
	}

	public static void main(String[] args) throws SQLException {
//		int contentId = 1;
//		
//		Music music = new Music();
//
//		music.setSinger("백예린");
//		music.setAlbum("Square");
//
//		MusicDAO dao = new MusicDAO();
//		System.out.println(dao.getMusicList(contentId)); // contentId 넘김
//		System.out.println(dao.getMusicDetail(contentId)); // contentId 넘김
//		System.out.println(dao.createMusic(music, contentId));
//
//		List<Music> searchResult = dao.searchMusicByTitle("title");
//		System.out.println(searchResult);
	}
}
