package model.dao.contents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.JDBCUtil;
import model.dto.contents.Book;

public class BookDAO {
    private JDBCUtil jdbcUtil = null;

    public BookDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

	/*
	 * public boolean createBook(Book book, int contentId) { List<Book> bookList =

	 * new ArrayList<>();
	 * 
	 * StringBuilder query = new StringBuilder(); String sql =
	 * "INSERT INTO Book (contentId, summary, writer, title, genre, publishDate) " +
	 * "VALUES (?, ?, ?, ?, ?, ?)";
	 * 
	 * query.append(sql);
	 * 
	 * // JDBCUtil 객체 생성 JDBCUtil jdbcUtil = new JDBCUtil();
	 * 
	 * // Contents 테이블에서 title, genre, publishDate 값을 가져오기 List<Book> contentsList =
	 * getBookList(contentId); if (contentsList.isEmpty()) {
	 * System.out.println("Contents 테이블에 해당하는 데이터가 없습니다."); return false; }
	 * 
	 * // Contents 테이블의 첫 번째 레코드를 사용 Book contentsData = contentsList.get(0);
	 * 
	 * // Contents 테이블의 값을 Book 테이블에 설정 Object[] param = new Object[] {
	 * contentsData.getContentId(), // Contents 테이블의 contentId를 사용
	 * book.getSummary(), book.getWriter(), contentsData.getTitle(), // Contents
	 * 테이블의 title을 사용 contentsData.getGenre(), // Contents 테이블의 genre을 사용
	 * contentsData.getPublishDate() // Contents 테이블의 publishDate를 사용 };
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

    public List<Book> getBookList(int contentId) {
        List<Book> bookList = new ArrayList<>();

        try {
            String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate " +
                         "FROM Contents c" +
                         "WHERE c.contentId = ?";

            jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                book.setContentId(rs.getInt("contentId"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));

                bookList.add(book);
            }
        } catch (Exception ex) {
            jdbcUtil.rollback(); // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit(); // 트랜잭션 commit 실행
            jdbcUtil.close();
        }

        return bookList;
    }
    
    public List<Book> getBookDetail(int contentId) {
        List<Book> bookDetail = new ArrayList<>();

        try {
            String sql = "SELECT c.contentId, c.title, c.genre, c.publishDate, m.summary, m.writer " +
                         "FROM Contents c JOIN Book m ON c.contentId = m.contentId " +
                         "WHERE c.contentId = ?";

            jdbcUtil.setSqlAndParameters(sql, new Object[] { contentId });

            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                book.setContentId(rs.getInt("contentId"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                
                book.setPublishDate(rs.getDate("publishDate"));
                book.setSummary(rs.getString("summary"));
                book.setWriter(rs.getString("writer"));

                bookDetail.add(book);
            }
        } catch (Exception ex) {
            jdbcUtil.rollback(); // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit(); // 트랜잭션 commit 실행
            jdbcUtil.close();
        }

        return bookDetail;
    }
    
    public List<Book> searchBookByTitle(String title) {
        List<Book> bookList = new ArrayList<>();

        try {
            // SQL 쿼리: Book 테이블에서 title에 해당하는 책 정보를 조회
            String sql = "SELECT * FROM Book WHERE title = ?";
            Object[] param = new Object[] { title };

            jdbcUtil.setSqlAndParameters(sql, param);
            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                book.setContentId(rs.getInt("contentId"));
                book.setSummary(rs.getString("summary"));
                book.setWriter(rs.getString("writer"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setPublishDate(rs.getDate("publishDate"));

                bookList.add(book);
            }
        } catch (Exception ex) {
            jdbcUtil.rollback(); // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit(); // 트랜잭션 commit 실행
            jdbcUtil.close();
        }

        return bookList;
    }

    public static void main(String[] args) throws SQLException {
//        int contentId = 1;
//
//        Book book = new Book();
//
//        book.setWriter("히가시노 게이고");
//        book.setSummary("summary2");
//
//        BookDAO dao = new BookDAO();
//        System.out.println(dao.getBookList(contentId)); // contentId 넘김
////        System.out.println(dao.createBook(book, contentId));
//
//        List<Book> searchResult = dao.searchBookByTitle("title");
//        System.out.println(searchResult);
    }
}
