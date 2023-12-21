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

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (userID == null) {
            System.out.println("User ID is null");
            return "redirect:/login/login.jsp";
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserInfo(userID);

        // action 파라미터 값에 따라 처리
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
            case "updateName":
                // 이름 수정
                String newName = request.getParameter("Username");
                user.setUserName(newName);  
                userDAO.updateUserInFollowTable(userID, newName);
                case "updateEmail":
                    // 이메일 수정
                    String newEmail = request.getParameter("Email");
                    user.setEmail(newEmail);
                    break;
                case "delete":
                    // 회원 탈퇴
                    if (userDAO.deleteUser(userID)) {
                        // 회원 탈퇴 성공 시 세션 무효화하고 로그인 페이지로 리다이렉트
                        session.invalidate();
                        return "redirect:/login";
                    } else {
                        // 회원 탈퇴 실패 시 에러 메시지 설정
                        request.setAttribute("errorMessage", "회원 탈퇴에 실패했습니다.");
                        return "redirect:/mypage"; // 실패 시 마이페이지로 다시 이동
                    }
                case "logout":
                    // 로그아웃
                    session.invalidate(); // 세션 무효화
                    return "redirect:/login";
            }

            // 수정된 사용자 정보를 데이터베이스에 업데이트         
            userDAO.updateUser(user);
        }

        // 수정 후 다시 마이페이지로 이동
        return "redirect:/mypage";  // 이동할 URL을 해당 서블릿에 맞게 수정
    }
}
