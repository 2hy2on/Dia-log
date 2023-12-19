package model.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.dto.user.User;

public class UserDAO {

    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
   
    public UserDAO() {
        try {
            String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";
            String user = "dbp230208";
            String passwd = "84537";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, passwd);
        }catch(Exception e)
        {
            e.printStackTrace(); //오류가 뭔지 출력
        }
    } // 실제 sql에 접속코드
    
    public int login(String ID, String password) {
        String query = "SELECT password, userId From user4 WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,ID);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String dbPassword = rs.getString("password").trim();
                int userId = rs.getInt("userId");
                
                if (dbPassword.equals(password.trim())) {
                    return userId; // 로그인 성공
                } else {
                    return 0; // 비밀번호 불일치
                }
            } else {
                return -1; // 아이디 없음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2; // 데이터베이스 오류
        }
    }
    public int join(User user) {
        String query = "INSERT INTO user4 (ID, userName, password, email, movie_interest, book_interest, music_interest, gender)VALUES (?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(query); 
            pstmt.setString(1,user.getID()); 
            pstmt.setString(2,user.getUserName()); 
            pstmt.setString(3,user.getPassword()); 
            pstmt.setString(4,user.getEmail()); 
            pstmt.setString(5,user.getMovie_interest()); 
            pstmt.setString(6,user.getBook_interest()); 
            pstmt.setString(7,user.getMusic_interest()); 
            pstmt.setString(8,user.getGender()); 
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return -1;
    }
    public User getUserInfo(String ID) {
        String query = "SELECT * FROM USER4 WHERE ID = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setID(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getString("GENDER"));
                user.setEmail(rs.getString("Email"));
                user.setMovie_interest(rs.getString("MOVIE_INTEREST"));
                user.setBook_interest(rs.getString("BOOK_INTEREST"));
                user.setMusic_interest(rs.getString("MUSIC_INTEREST"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateUser(User user) {
        String query = "UPDATE USER4 SET USERNAME=?, EMAIL=? WHERE ID=?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getID());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteUser(String ID) {
        PreparedStatement pstmt = null;

        try {
            conn.setAutoCommit(false); // 자동 커밋을 비활성화

            // 자식 레코드 삭제 (REVIEW 테이블)
            String deleteChildRecordsQuery = "DELETE FROM REVIEW WHERE WRITERID IN (SELECT USERID FROM USER4 WHERE ID = ?)";
            pstmt = conn.prepareStatement(deleteChildRecordsQuery);
            pstmt.setString(1, ID);
            pstmt.executeUpdate();

            // 부모 레코드 삭제 (USER4 테이블)
            String deleteUserQuery = "DELETE FROM USER4 WHERE ID = ?";
            pstmt = conn.prepareStatement(deleteUserQuery);
            pstmt.setString(1, ID);
            int result = pstmt.executeUpdate();

            conn.commit(); // 수동으로 커밋
            
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
}