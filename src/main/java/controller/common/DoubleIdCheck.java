package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;


/**
 * Servlet implementation class DoubleIdCheck
 */
@WebServlet("/doubleIdCheck")
public class DoubleIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoubleIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("userid");
		System.out.println("Received userid: " + id); // 로그 추가
		
		UserService service = new UserServiceImpl();
		
		try {
			boolean doubleId = service.checkDoubleId(id);
			response.getWriter().write(String.valueOf(doubleId));
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write(String.valueOf("아이디 중복 체크 실패"));
		}
	}

}
