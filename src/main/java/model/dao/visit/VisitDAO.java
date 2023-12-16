package model.dao.visit;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.dao.JDBCUtil;
import model.dto.review.Review;
import model.dto.visit.Visit;
import model.dto.visit.VisitNum;

public class VisitDAO {
private JDBCUtil jdbcUtil = null;
	
    public VisitDAO() {
    	jdbcUtil = new JDBCUtil();	
       
    } 
    
    public boolean createVisitor(Visit visit) {
    	
    	StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Visit (ownerId, visitorId, visitDate) VALUES (?, ?, ?)");

        // Get the current date
        java.util.Date currentDate = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        Object[] param = new Object[] { visit.getOwnerId(), visit.getVisitorId(), sqlDate };

 										
 		jdbcUtil.setSqlAndParameters(query.toString(), param);
 		
 		try {
  			ResultSet rs = jdbcUtil.executeQuery();
  			if(rs.next()) {
  				return true;
  			}
  		}
  		catch (Exception ex) {
            jdbcUtil.rollback();    // 트랜잭션 rollback 실행
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();      // 트랜잭션 commit 실행
            jdbcUtil.close();
        }
        return false; 
    }
    
    public List<VisitNum> getVisitNum(int ownerId ,String startDate, String endDate) {
    	
    	 StringBuilder query = new StringBuilder();
    	    query.append("SELECT COUNT(visitDate) AS visitCount, visitDate ");
    	    query.append("FROM VISIT WHERE ownerId=? AND visitDate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ");
    	    query.append("GROUP BY visitDate ORDER BY visitDate");

    	    Object[] param = new Object[]{ownerId, startDate, endDate};
    	    jdbcUtil.setSqlAndParameters(query.toString(), param);

    	    try {
    	        List<VisitNum> visitNumList = new ArrayList<>();
    	      
    	        ResultSet rs = jdbcUtil.executeQuery();
    	        
    	        while (rs.next()) {
    	        	VisitNum visit = new VisitNum();
    	           
    	            System.out.print(rs.getInt("visitCount"));

    	            visit.setNum(rs.getInt("visitCount"));
    	            visit.setVisitDate(rs.getDate("visitDate"));

    	            visitNumList.add(visit);
    	        }

    	        return visitNumList;
    	    } catch (Exception ex) {
    	        jdbcUtil.rollback();
    	        ex.printStackTrace();
    	    } finally {
    	        jdbcUtil.commit();
    	        jdbcUtil.close();
    	    }
    	    return null;
    
    }
//    public static void main(String[] args) {
//    	 //       Scanner scanner = new Scanner(System.in);
//    	 ///       Review re = new Review();
//    	 //       re.setContent("안녕ttttt");
//    	 //       re.setContentId(3);
//    	    //           re.setPrivate(false);
//    	    //         re.setRate(4.0f);
//    	        //           re.setTitle("너무나도ererer 요");
//    	        //           re.setWriterId(3);
//    	  //      re.setWatchedAt(null);
////    	        ReviewDAO reDao = new ReviewDAO();
//    	        VisitDAO visi = new VisitDAO();
//    	        Visit v = new Visit();
//    	        v.setOwnerId(3);
//    	        v.setVisitorId(0);
//    	//
//    	        System.out.println(visi.createVisitor(v));
//    	        //System.out.println(reDao.getReviewByDate(3, "/2023/11/27"));
//    	 //       System.out.println(reDao.deleteReview(8));
//    	 //       System.out.println(reDao.updateReview(12, re));
////    	        
////    	        
//    	VisitDAO dao = new VisitDAO();
//    	System.out.println(dao.getVisitNum(2, "11", "2023-11-23").get(1));
//////    	        scanner.close();
//    	    }
}
