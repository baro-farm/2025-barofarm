package controller.buyer;

import java.io.IOException;
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

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/insertAddress")
public class InsertAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAddress() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/buyer/insertAddress.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
		
		UserService service = new UserServiceImpl();

		try {
			Long userNum = service.selectUserNumByUserId(sessionUser.getUserId());

			String nickname = request.getParameter("nickname");
			String name = request.getParameter("name");
			String postCode = request.getParameter("postCode");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone = request.getParameter("phone");
			
			Address address = new Address(nickname,name,phone,postCode,addr1,addr2);
			address.setUserNum(userNum);
			service.insertUserAddress(address);
			response.sendRedirect("addressList");

			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
