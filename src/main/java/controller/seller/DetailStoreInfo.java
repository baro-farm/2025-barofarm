package controller.seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;
import vo.SellerVO;
import vo.UserVO;

/**
 * Servlet implementation class DetailStoreInfo
 */
@WebServlet("/detailStoreInfo")
public class DetailStoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailStoreInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user");
		
		SellerService sellerService = new SellerServiceImpl();
		UserService userService = new UserServiceImpl();
		
		
		try {
			SellerVO seller = sellerService.getSerllerDetail(user.getUserNum());
			UserVO userVo = userService.selectUserInfo(user.getUserId());
			request.setAttribute("seller", seller);
			request.setAttribute("user", userVo);
			request.getRequestDispatcher("/seller/detailStoreInfo.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "판매자의 스토어정보 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
