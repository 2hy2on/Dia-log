package model.dto.visit;

import java.sql.Date;
import java.time.LocalDate;

public class VisitNum {

	private int num;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	private Date visitDate;
	
	@Override
	public String toString() {
	    return "Review{" +
	            "title='" + num + '\'' +
	            ", content='" + visitDate + '\'' +

	            '}';
	}
}
