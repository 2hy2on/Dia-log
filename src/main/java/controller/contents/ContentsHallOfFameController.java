package controller.contents;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.dto.contents.Contents;
import model.service.contents.ContentsManager;

public class ContentsHallOfFameController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ContentsHallOfFameController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentsManager manager = ContentsManager.getInstance();

		List<Contents> hallOfFameList = manager.getListHallOfFame();
		request.setAttribute("hallOfFameList", hallOfFameList);

//		logger.debug("hallOfFameList size in Controller: " + hallOfFameList.size());

		ObjectMapper mapper = new ObjectMapper();
		String jsonHOFList = mapper.writeValueAsString(hallOfFameList);

		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.print(jsonHOFList);
		}

		return null;
	}
}
