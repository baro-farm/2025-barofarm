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
import dto.seller.Product;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import service.seller.SellerDetailService;
import service.seller.SellerDetailServiceImpl;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/sellerProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
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
		User sessionUser =null;
		
		if(session != null) {
			sessionUser=(User)session.getAttribute("user");
		}
		if(sessionUser == null) {
			request.getRequestDispatcher("/login").forward(request, response);
		}
		
		
		List<Product> productList= new ArrayList<>();
		SellerDetailService detailService = new SellerDetailServiceImpl();
		ProductService service = new ProductServiceImpl();
		
		try {
			System.out.println(sessionUser.getUserId());
			Long sellerNum = detailService.selectSellerNumById(sessionUser.getUserId());
			System.out.println(sellerNum);
			productList = service.selectSellerProductList(sellerNum);
			System.out.println(productList);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/seller/productList.jsp").forward(request, response);

		}catch (Exception e) {
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
