package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class AutoLoginCheck
 */
@WebServlet("/autoLoginCheck")
public class AutoLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// 로그인 세션이 없을 때
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
		    // 1. 자동로그인 쿠키가 있는지 확인
		    Cookie[] cookies = request.getCookies();
		    String sessionId = null;
		    if (cookies != null) {
		        for (Cookie c : cookies) {
		            if (c.getName().equals("autoLogin")) {
		                sessionId = c.getValue();
		            }
		        }
		    }
		    // 2. DB에서 세션ID로 사용자 조회
		    if (sessionId != null) {
		    	UserService service = new UserServiceImpl();
		    	try {
                    User user = service.findUserById(sessionId);
                    if (user != null) {
                        session = request.getSession();
                        session.setAttribute("user", user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
