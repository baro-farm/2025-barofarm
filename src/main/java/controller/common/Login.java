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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = "";
	    String saveId = "";
	    String autoLogin = "";
	    String pwd = "";

	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie c : cookies) {
	            switch (c.getName()) {
	                case "userId": userId = c.getValue(); break;
	                case "saveId": saveId = c.getValue(); break;
	                case "autoLogin": autoLogin = c.getValue(); break;
	                case "pwd": pwd = c.getValue(); break;
	            }
	        }
	    }

	    request.setAttribute("userId", userId);
	    request.setAttribute("saveId", saveId);
	    request.setAttribute("autoLogin", autoLogin);
	    request.setAttribute("pwd", pwd);

	    request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		String saveId = request.getParameter("saveId");
		String autoLogin = request.getParameter("autoLogin");
		
		UserService service = new UserServiceImpl();
		try {
			User user = service.login(userId, pwd);
			user.setPwd("");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			Cookie cookieId = null;
			Cookie cookiePwd = null;
			Cookie cookieSaveId = null;
			Cookie cookieAutoLogin = null;
			
			if (saveId != null || autoLogin != null) { // 아이디 저장
			    cookieId = new Cookie("userId", userId);
			    cookieId.setMaxAge(60*60*24*31);
			} else {
			    cookieId = new Cookie("userId", "");
			    cookieId.setMaxAge(0);
			}
			if (saveId != null) {
				cookieSaveId = new Cookie("saveId", "on");
	            cookieSaveId.setMaxAge(60*60*24*31);
			} else {
				cookieSaveId = new Cookie("saveId", "");
				cookieSaveId.setMaxAge(0);
	        }
			// 자동 로그인
			if (autoLogin != null) {
				cookiePwd = new Cookie("pwd", pwd);
				cookieAutoLogin = new Cookie("autoLogin", "on");
	            cookiePwd.setMaxAge(60*60*24*31);
	            cookieAutoLogin.setMaxAge(60*60*24*31);
			}else {
				cookiePwd = new Cookie("pwd", "");
				cookieAutoLogin = new Cookie("autoLogin", "");
	            cookiePwd.setMaxAge(0);
	            cookieAutoLogin.setMaxAge(0);
	            
	        }
			cookieId.setPath("/");
			cookiePwd.setPath("/");
			cookieSaveId.setPath("/");
			cookieAutoLogin.setPath("/");
			response.addCookie(cookieId);
            response.addCookie(cookieSaveId);
            response.addCookie(cookiePwd);
            response.addCookie(cookieAutoLogin);
			
			response.sendRedirect("main.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "로그인에 실패했습니다.");
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
