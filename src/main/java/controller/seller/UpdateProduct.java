package controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import dto.seller.Product;
import dto.seller.ProductOption;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 해당 상품 정보를 가져오고 해당 상품의 판매자인지 판별
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		// 양배추 상품 번호 임시로 정함
		Long productNum = 160L;
		ProductService productService = new ProductServiceImpl();
		SellerService sellerService = new SellerServiceImpl();
		Product product = null;
		List<ProductOption> productOption = null;
		Long sellerNum = 0L;
		
		try {
			product = productService.selectProduct(productNum);
			productOption = productService.selectProductOption(productNum);
			sellerNum = sellerService.selectSellerNum(user.getUserNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		if (sellerNum == product.getSellerNum()) {
			request.setAttribute("product", product);
			request.setAttribute("productOption", productOption);
			request.getRequestDispatcher("/seller/updateProduct.jsp").forward(request, response);
		} else {
			response.sendRedirect("/barofarm/login");
			return;
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
