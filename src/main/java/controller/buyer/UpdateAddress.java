package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.Address;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;

/**
 * Servlet implementation class UpdateAddress
 */
@WebServlet("/updateAddress")
public class UpdateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UserService service = new UserServiceImpl();
		Long addrNum = Long.parseLong(request.getParameter("addrNum"));
		Address address = null;
		try {
			address=service.selectUserAddress(addrNum);
			request.setAttribute("address", address);
			request.getRequestDispatcher("/buyer/updateAddress.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		UserService service = new UserServiceImpl();
		Address address = null;
		
		Long addrNum=Long.parseLong(request.getParameter("addrNum"));
		String nickname= request.getParameter("nickname");
		String name=request.getParameter("name");
		String postCode=request.getParameter("postCode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone = request.getParameter("phone");
		
		try {
			address=new Address(addrNum,nickname,name,phone,postCode,addr1,addr2);
			service.updateUserAddress(address);
			response.sendRedirect("addressList?userNum=3");

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
