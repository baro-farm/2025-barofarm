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

	    // 현재 URL 저장 (Referer를 사용)
	    String referer = request.getHeader("Referer");
	    if (referer != null && !referer.contains("/login")) {  // 로그인 페이지는 제외
	        request.getSession().setAttribute("prevPage", referer);
	    }
	    
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie c : cookies) {
	            switch (c.getName()) {
	                case "userId": userId = c.getValue(); break;
	                case "saveId": saveId = c.getValue(); break;
	                case "autoLogin": autoLogin = c.getValue(); break;
	            }
	        }
	    }

	    request.setAttribute("userId", userId);
	    request.setAttribute("saveId", saveId);
	    request.setAttribute("autoLogin", autoLogin);

	    request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
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

			int cookieTime = 60 * 60 * 24 * 31;
			
			// 아이디 저장 쿠키
            Cookie cookieId = new Cookie("userId", (saveId != null) ? userId : "");
            cookieId.setMaxAge((saveId != null) ? cookieTime : 0);
            cookieId.setPath("/");
            // 아이디 저장 체크 여부 쿠키
            Cookie cookieSaveId = new Cookie("saveId", (saveId != null) ? "on" : "");
            cookieSaveId.setMaxAge((saveId != null) ? cookieTime : 0);
            cookieSaveId.setPath("/");

	        // 이전 페이지 저장 여기 추가
	        String prevPage = (String) session.getAttribute("prevPage");
	        session.removeAttribute("prevPage");  // 쓰고 나면 지우기
	        String redirectUrl = (prevPage != null) ? prevPage : request.getContextPath() + "/main";

			// 자동 로그인 체크 여부 쿠키
            Cookie cookieAutoLogin = new Cookie("autoLogin", (autoLogin != null) ? "on" : "");
            cookieAutoLogin.setMaxAge((autoLogin != null) ? cookieTime : 0);
            cookieAutoLogin.setPath("/");

            // (보안상 비밀번호 쿠키는 저장하지 않음)

            // 쿠키 추가(삭제)
            request.setAttribute("saveId", cookieSaveId);
            request.setAttribute("autoLogin", cookieAutoLogin);
            response.addCookie(cookieId);
            response.addCookie(cookieSaveId);
            response.addCookie(cookieAutoLogin);
            
			response.getWriter().write("{\"success\": true, \"redirectUrl\": \"" + redirectUrl + "\"}");
			
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write("{\"success\": false, \"message\": \"아이디 또는 비밀번호가 잘못되었습니다.\"}");
		}
	}

}
