package controller.common;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import dto.buyer.Address;
import dto.seller.SellerDetail;
import service.UserService;
import service.UserServiceImpl;
import vo.SellerVO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String birthStr = request.getParameter("birthDate");
		LocalDate birthDate = LocalDate.parse(birthStr);
		String email = request.getParameter("email");
		String isSellerStr = request.getParameter("isSeller");
		boolean isSeller = Boolean.parseBoolean(isSellerStr);

		User user = new User(userid,pwd,userName,phone,birthDate,email,isSeller);
		
		//주소
		String postCode = request.getParameter("postCode");
		String addr1 = request.getParameter("addr1");
		String addr2Str = request.getParameter("addr2");
		String extraAddr = request.getParameter("extraAddr");
		String addr2 = addr2Str + " " + extraAddr;
				
		Address address = new Address();
		address.setNickname("기본배송지");
		address.setName(userName);
		address.setPhone(phone);
		address.setPostCode(postCode);
		address.setAddr1(addr1);
		address.setAddr2(addr2);
		address.setIsDefault(true);
		
		//판매자 정보
		SellerDetail sellerDetail = null;
		if (isSeller) {
			String storeName = request.getParameter("storeName");
			String businessNum = request.getParameter("businessNum");
			sellerDetail = new SellerDetail(storeName, businessNum);
		}

		UserService service = new UserServiceImpl();
		
		try {
			service.join(user, address, sellerDetail);
			response.sendRedirect("login");
			
		} catch(Exception e) {
			request.setAttribute("userId", userid);
			request.setAttribute("pwd", pwd);
			request.setAttribute("userName", userName);
			request.setAttribute("phone", phone);
			request.setAttribute("birthDate", birthStr);
			request.setAttribute("email", email);
			request.setAttribute("isSeller", isSellerStr);
			request.setAttribute("storeName", request.getParameter("storeName"));
			request.setAttribute("businessNum", request.getParameter("businessNum"));
			request.setAttribute("postCode", postCode);
			request.setAttribute("addr1", addr1);
			request.setAttribute("addr2", addr2Str);
			request.setAttribute("extraAddr", extraAddr);
			
			e.printStackTrace();
			request.setAttribute("error", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("join.jsp").forward(request, response);
		}
	}

}
