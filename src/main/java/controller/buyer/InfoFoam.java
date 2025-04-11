package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.User;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;

/**
 * Servlet implementation class InfoFoam
 */
@WebServlet("/infoFoam")
public class InfoFoam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoFoam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UserService service= new UserServiceImpl();
		User user = null;
		try {
			user=service.selectUserMyInfo("gogogo");
			System.out.println(user);
			request.setAttribute("user",user);
			request.getRequestDispatcher("/buyer/buyerInfoFoam.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
