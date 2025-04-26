package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
        	for (Cookie c : cookies) {
                if (c.getName().equals("autoLogin")) {
                    c.setValue("");
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
                // saveId가 "on"이 아니면 userId도 삭제
                if (c.getName().equals("saveId") && !c.getValue().equals("on")) {
                    Cookie userIdCookie = new Cookie("userId", "");
                    userIdCookie.setMaxAge(0);
                    userIdCookie.setPath("/");
                    response.addCookie(userIdCookie);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/main");
	}

}
