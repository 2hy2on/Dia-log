package controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.friend.FollowDAO;
import model.dao.user.UserDAO;
import model.dto.user.User;

public class MyPageUpdateController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("ID");

		if (userID == null) {
			System.out.println("User ID is null");
			return "redirect:/login/login.jsp";
		}

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserInfo(userID);

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "updateName":
				String newName = request.getParameter("Username");
				user.setUserName(newName);

				userDAO.updateUserInFollowTable(userID, newName);
				break;
			case "updateEmail":
				String newEmail = request.getParameter("Email");
				user.setEmail(newEmail);
				break;
			case "delete":
				if (userDAO.deleteUser(userID)) {
					session.invalidate();
					return "redirect:/login";
				} else {
					request.setAttribute("errorMessage", "회원 탈퇴에 실패했습니다.");
					return "redirect:/mypage";
				}
			case "logout":
				session.invalidate();
				return "redirect:/login";
			}
			userDAO.updateUser(user);
		}
		return "redirect:/mypage";
	}
}
