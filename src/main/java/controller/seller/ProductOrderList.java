package controller.seller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;
import vo.ProdOrderVO;

/**
 * Servlet implementation class ProductOrderList
 */
@WebServlet("/sellerProdOrderList")
public class ProductOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}	
		
		List<ProdOrderVO> orderList = new ArrayList<>();
		SellerDetailService detailService = new SellerDetailServiceImpl();
		ProdOrderService service = new ProdOrderServiceImpl();
		
		int page=1;
		int pageSize=10;
		int totalCount=0;
		
		try {
			Long sellerNum = detailService.selectSellerNumById(sessionUser.getUserId());
			
		}catch(Exception e) {
			e.printStackTrace();
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
