package model.dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.board.Board;

public class BoardDAO {
    private static Connection conn;
    private static ResultSet rs;

    public BoardDAO() {
        try {
            String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";
            String user = "dbp230208";
            String passwd = "84537";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace(); // 오류가 뭔지 출력
        }
    } // 실제 sql에 접속코드

    public String getDate() {
        String query = "SELECT TO_CHAR(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH24:MI:SS') FROM DUAL";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getNext() {
        String query = "SELECT boardID FROM BOARD ORDER BY boardID DESC";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int write(String boardTitle, String ID, String boardContent) {
        String query = "INSERT INTO Board VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, getNext());
            pstmt.setString(2, boardTitle);
            pstmt.setString(3, ID);
            pstmt.setString(4, getDate());
            pstmt.setString(5, boardContent);
            pstmt.setInt(6, 1);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Board> getList(int pageNumber) {
        String query = "SELECT * FROM (SELECT * FROM BOARD WHERE BoardID < ? AND boardAvailable = 1 ORDER BY BoardID DESC) WHERE ROWNUM <= 10";
        ArrayList<Board> list = new ArrayList<Board>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Board board = new Board();
                board.setBoardID(rs.getInt(1));
                board.setBoardTitle(rs.getString(2));
                board.setID(rs.getString(3));
                board.setBoardDate(rs.getString(4));
                board.setBoardContent(rs.getString(5));
                board.setBoardAvailable(rs.getInt(6));
                list.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean nextPage(int pageNumber) {
        String query = "SELECT * FROM Board WHERE BoardID < ? AND boardAvailable = 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Board getBoard(int BoardID) {
        String query = "SELECT * FROM Board WHERE BoardID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, BoardID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Board board = new Board();
                board.setBoardID(rs.getInt(1));
                board.setBoardTitle(rs.getString(2));
                board.setID(rs.getString(3));
                board.setBoardDate(rs.getString(4));
                board.setBoardContent(rs.getString(5));
                board.setBoardAvailable(rs.getInt(6));
                return board;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }
    }
