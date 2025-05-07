package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.User;
import dto.seller.Product;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;

/**
 * Servlet implementation class InsertCart
 */
@WebServlet("/insertCart")
public class InsertCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// product 서비스로 sellerNum 가져오기
		// 쇼핑카트에 데이터 넣기

		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();

		try (BufferedReader reader = request.getReader()) {
			JsonObject json = gson.fromJson(reader, JsonObject.class);

			Long productNum = json.get("productNum").getAsLong();
			Long optionNum = json.get("optionNum").getAsLong();
			Integer quantity = json.get("quantity").getAsInt();

			// 세션에서 userNum 가져오기
			User user = (User) request.getSession().getAttribute("user");
			
			
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("{\"message\": \"로그인이 필요합니다.\"}");
				return;
			}
			
			Long userNum = user.getUserNum();
			ShoppingCartService cartService = new ShoppingCartServiceImpl();
			boolean isProductInCart = cartService.isProductInCart(userNum, optionNum);
			System.out.println(isProductInCart);
			if (isProductInCart) {
				Long cartNum = cartService.getCartNum(userNum, optionNum);
				System.out.println(cartNum);
				cartService.updateCartQuantityIncrease(userNum, cartNum, quantity);
			} else {	
				ProductService productService = new ProductServiceImpl();
				Product product = productService.selectProduct(productNum);
				Long sellerNum = product.getSellerNum();

				cartService.addToCart(sellerNum, user.getUserNum(), productNum, optionNum, quantity);
			}

			response.getWriter().write("{\"message\": \"장바구니에 담겼습니다.\"}");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("{\"message\": \"오류가 발생했습니다.\"}");
		}

	}

}
