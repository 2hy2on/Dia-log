package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("ID") != null) {
			return "redirect:/contents/list";
		}

		String userID = request.getParameter("ID");
		String password = request.getParameter("password");

		session.setAttribute("ID", userID);
		UserDAO userDAO = new UserDAO();
		int loginResult = userDAO.login(userID, password);

		switch (loginResult) {
//      session.setAttribute("ID", userID); 
		case 0:
			request.setAttribute("errorMessage", "비밀번호가 틀렸습니다.");
			return "/login/login.jsp";
		case -1:
			request.setAttribute("errorMessage", "존재하지 않는 아이디입니다.");
			return "/login/login.jsp";
		case -2:
			request.setAttribute("errorMessage", "데이터베이스 오류가 발생했습니다.");
			return "/login/login.jsp";
		default:
//      request.setAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");           
			session.setAttribute("userId", loginResult);
			return "redirect:/contents/list";
		}
	}
}