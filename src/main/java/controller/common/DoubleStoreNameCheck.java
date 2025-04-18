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
 * Servlet implementation class DoubleStoreNameCheck
 */
@WebServlet("/doubleStoreNameCheck")
public class DoubleStoreNameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoubleStoreNameCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String storeName = request.getParameter("storeName");

		UserService service = new UserServiceImpl();

		try {
			boolean isDuplicate = service.doubleStoreNameCheck(storeName);
			response.getWriter().write(String.valueOf(isDuplicate));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(String.valueOf("스토어 중복 체크 실패"));
		}
	}

}
