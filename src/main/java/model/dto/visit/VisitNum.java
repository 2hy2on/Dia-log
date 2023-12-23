package model.dto.visit;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class VisitNum {

	private int num;
	private Date visitDate;

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

	@Override
	public String toString() {
		return "Review{" + "title='" + num + '\'' + ", content='" + visitDate + '\'' +

				'}';
	}

	public String getFormattedStart() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(visitDate);
	}
}
