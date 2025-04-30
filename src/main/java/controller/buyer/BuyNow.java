package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.buyer.Address;
import dto.buyer.CartProductGroup;
import dto.buyer.ShoppingCartItem;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;

/**
 * Servlet implementation class BuyNow
 */
@WebServlet("/buyNow")
public class BuyNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		Long userNum = user.getUserNum();
		Properties props = new Properties();
		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties");
		
		if (input == null) {
		    System.out.println("❌ properties 파일 경로 잘못됨!");
		} else {
		    props.load(input);
		    System.out.println("✅ properties 파일 로드 성공!");
		}
		
		String impKey = props.getProperty("imp.key");
		request.setAttribute("impKey", impKey);

		String cartNumsStr = request.getParameter("cartNums");
		System.out.println(cartNumsStr);
//		
//		try {
//			// 주소 정보도 동일하게 조회
//			UserService userService = new UserServiceImpl();
//			Long userNum = user.getUserNum();
//			Address defaultAddress = userService.selectDefaultAddress(userNum);
//			List<Address> addressList = userService.selectUserAddressList(user.getUserId());
//			
//			request.setAttribute("defaultAddress", defaultAddress);
//			request.setAttribute("addressList", addressList);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
		request.getRequestDispatcher("/buyer/buyNow.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}

		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userPhone", user.getPhone());
		
		try {			
			BufferedReader reader = request.getReader();
			
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
	
			Long productNum = json.get("productNum").getAsLong();
			JsonArray optionArray = json.get("options").getAsJsonArray();
	
			List<ShoppingCartItem> tempOptions = new ArrayList<>();
			ShoppingCartService cartService = new ShoppingCartServiceImpl();

			for (JsonElement e : optionArray) {
				JsonObject obj = e.getAsJsonObject();
				Long optionNum = obj.get("optionNum").getAsLong();
				int quantity = obj.get("quantity").getAsInt();
				System.out.println("💡 호출: optionNum=" + optionNum + ", quantity=" + quantity);

				ShoppingCartItem item = cartService.selectTempItem(optionNum, quantity);
				
				if (item != null) tempOptions.add(item);
			}
		
			System.out.println("✔️ productName: " + tempOptions.get(0).getProductName());
			System.out.println("✔️ imgUrl: " + tempOptions.get(0).getImgUrl());
			System.out.println("✔️ price: " + tempOptions.get(0).getBasePrice());
			System.out.println("✔️ storeName: " + tempOptions.get(0).getStoreName());

			if (tempOptions.isEmpty()) {
			    System.out.println("❌ tempOptions is empty - NPE 위험!");
			    response.sendRedirect("/barofarm/error.jsp");
			    return;
			}
			
			
			// 그룹 만들기
			CartProductGroup group = new CartProductGroup();
			group.setProductNum(productNum);
			group.setProductName(tempOptions.get(0).getProductName());
			group.setImgUrl(tempOptions.get(0).getImgUrl());
			group.setBasePrice(tempOptions.get(0).getBasePrice());
			group.setStoreName(tempOptions.get(0).getStoreName());
			group.setOptions(tempOptions);
	
			Map<String, List<CartProductGroup>> paymentCartMap = Map.of(
				group.getStoreName(), List.of(group)
			);

			request.getSession().setAttribute("paymentCartMap", paymentCartMap);
			request.getSession().setAttribute("isBuyNow", true);
			
			// 주소 정보도 동일하게 조회
			UserService userService = new UserServiceImpl();
			Long userNum = user.getUserNum();
			Address defaultAddress = userService.selectDefaultAddress(userNum);
			List<Address> addressList = userService.selectUserAddressList(user.getUserId());
			
			request.getSession().setAttribute("defaultAddress", defaultAddress);
			request.getSession().setAttribute("addressList", addressList);
	
			response.sendRedirect(request.getContextPath() + "/buyNow");
			
			System.out.println("✅ defaultAddress = " + defaultAddress);
			System.out.println("✅ addressList = " + addressList);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/barofarm/error.jsp");
		}
	}

}
