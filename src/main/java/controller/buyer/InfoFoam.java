package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.Address;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import vo.UserVO;

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
		HttpSession session = request.getSession(false);
		
		UserService service= new UserServiceImpl();
		User sessionUser =null;
		UserVO user = null;

		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		
		try {
			
			user=service.selectUserInfo(sessionUser.getUserId());
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
