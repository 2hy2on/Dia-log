package model.service.visit;

import java.util.List;

import model.dao.visit.VisitDAO;
import model.dto.visit.Visit;
import model.dto.visit.VisitNum;

public class VisitManager {
	private static VisitManager visitMan = new VisitManager();
	private VisitDAO visitDAO;

	private VisitManager() {
		try {
			visitDAO = new VisitDAO();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static VisitManager getInstance() {
		return visitMan;
	}

	public boolean createVisitor(Visit visit) {
		if (visit.getOwnerId() == visit.getVisitorId()) {
			return false;
		} else
			return visitDAO.createVisitor(visit);
	}

	public List<VisitNum> getVisitNum(int ownerId, String startDate, String endDate) {
		return visitDAO.getVisitNum(ownerId, startDate, endDate);
	}
}
