package controller.buyer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.CartProductGroup;
import dto.buyer.ShoppingCartItem;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}

		Properties props = new Properties();
//		try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties")) {
//		    props.load(input);
//		}
//		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties");

//		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties");
		if (input == null) {
		    System.out.println("❌ properties 파일 경로 잘못됨!");
		} else {
		    props.load(input);
		    System.out.println("✅ properties 파일 로드 성공!");
		}
		String impKey = props.getProperty("imp.key");
		System.out.println("impKey: " + impKey); // ✅ 값 나오는지 확인!
//		request.setAttribute("impKey", props.getProperty("imp.key"));
		request.setAttribute("impKey", impKey);

		String cartNumsStr = request.getParameter("cartNums");
		System.out.println(cartNumsStr);
//		if (cartNumsStr == null || cartNumsStr.isEmpty()) {
//			response.sendRedirect("/barofarm/shoppingCart");
//			System.out.println("xxxxx");
//			return;
//		}

		List<Long> cartNums = Arrays.stream(cartNumsStr.split(",")).map(String::trim).filter(s -> !s.isEmpty())
				.map(Long::parseLong).collect(Collectors.toList());
System.out.println(cartNums);
		ShoppingCartService cartService = new ShoppingCartServiceImpl();
		try {

			List<ShoppingCartItem> cartItems = cartService.selectCartByCartNums(cartNums);

			Map<String, List<CartProductGroup>> paymentCartMap = new LinkedHashMap<>();

			for (ShoppingCartItem item : cartItems) {
				String storeName = item.getStoreName();
				Long productNum = item.getProductNum();

				List<CartProductGroup> storeProducts = paymentCartMap.computeIfAbsent(storeName,
						k -> new ArrayList<>());

				CartProductGroup existingProduct = null;
				for (CartProductGroup group : storeProducts) {
					if (group.getProductNum().equals(productNum)) {
						existingProduct = group;
						break;
					}
				}

				if (existingProduct == null) {
					existingProduct = new CartProductGroup();
					existingProduct.setProductNum(productNum);
					existingProduct.setProductName(item.getProductName());
					existingProduct.setImgUrl(item.getImgUrl());
					existingProduct.setBasePrice(item.getPrice());
					existingProduct.setStoreName(storeName);
					existingProduct.setOptions(new ArrayList<>());
					storeProducts.add(existingProduct);
				}

				existingProduct.getOptions().add(item);
			}
			request.setAttribute("paymentCartMap", paymentCartMap);
			request.setAttribute("cartNums", cartNums);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userPhone", user.getPhone());

			request.getRequestDispatcher("/buyer/payment.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("/barofarm/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userPhone", user.getPhone());

		String[] cartNumsParam = request.getParameterValues("cartNums[]");
//		if (cartNumsParam == null || cartNumsParam.length == 0) {
//			response.sendRedirect("/barofarm/shoppingCart");
//			return;
//		}

		// cartNums를 다시 조합
		String joinedCartNums = String.join(",", cartNumsParam);

		// GET으로 다시 리디렉션
//		request.setAttribute("cartNums", cartNums);
		// 이건 됨..
		response.sendRedirect("/barofarm/payment?cartNums=" + URLEncoder.encode(joinedCartNums, "UTF-8"));
//		request.getRequestDispatcher("/barofarm/payment?cartNums=" + URLEncoder.encode(joinedCartNums, "UTF-8")).forward(request, response);
//		request.getRequestDispatcher("/barofarm/payment").forward(request, response);
	}

}
