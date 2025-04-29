package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}


		try {			
			BufferedReader reader = request.getReader();
			
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
	
			Long productNum = json.get("productNum").getAsLong();
			JsonArray optionArray = json.get("options").getAsJsonArray();
	
			List<ShoppingCartItem> tempOptions = new ArrayList<>();
			ShoppingCartService cartService = new ShoppingCartServiceImpl();
	
//			for (JsonElement e : optionArray) {
//				JsonObject obj = e.getAsJsonObject();
//				Long optionNum = obj.get("optionNum").getAsLong();
//				int quantity = obj.get("quantity").getAsInt();
//				System.out.println("💡 호출: optionNum=" + optionNum + ", quantity=" + quantity);
//	
//				ShoppingCartItem item = cartService.selectTempItem(productNum, optionNum, quantity);
//				tempOptions.add(item);
//			}
			for (JsonElement e : optionArray) {
				JsonObject obj = e.getAsJsonObject();
				Long optionNum = obj.get("optionNum").getAsLong();
				int quantity = obj.get("quantity").getAsInt();
				System.out.println("💡 호출: optionNum=" + optionNum + ", quantity=" + quantity);

				ShoppingCartItem item = cartService.selectTempItem(optionNum, quantity);
				
				if (item == null) {
					System.out.println("❌ item이 null! productNum=" + productNum + ", optionNum=" + optionNum);
					continue; // 또는 return;
				}

				tempOptions.add(item);
			}
		
			// 그룹 만들기
			CartProductGroup group = new CartProductGroup();
			group.setProductNum(productNum);
			group.setProductName(tempOptions.get(0).getProductName());
			group.setImgUrl(tempOptions.get(0).getImgUrl());
			group.setBasePrice(tempOptions.get(0).getPrice());
			group.setStoreName(tempOptions.get(0).getStoreName());
			group.setOptions(tempOptions);
	
			Map<String, List<CartProductGroup>> paymentCartMap = Map.of(
				group.getStoreName(), List.of(group)
			);
	
			request.setAttribute("paymentCartMap", paymentCartMap);
	
			System.out.println("tempOptions 사이즈: " + tempOptions.size());
			System.out.println("첫 번째 item: " + tempOptions.get(0));
			System.out.println("상품명: " + tempOptions.get(0).getProductName());

			
			// 주소 정보도 동일하게 조회
			UserService userService = new UserServiceImpl();
			Long userNum = user.getUserNum();
			Address defaultAddress = userService.selectDefaultAddress(userNum);
			List<Address> addressList = userService.selectUserAddressList(user.getUserId());
	
			request.setAttribute("defaultAddress", defaultAddress);
			request.setAttribute("addressList", addressList);
	
			request.setAttribute("isBuyNow", true);  // JSP에서 플래그 확인
//			request.getRequestDispatcher("/buyer/buyNow.jsp").forward(request, response);
			
			JsonObject result = new JsonObject();
			result.addProperty("success", true);
			result.addProperty("redirectUrl", request.getContextPath() + "/buyer/buyNow.jsp");

			response.setContentType("application/json");
			response.getWriter().write(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/barofarm/error.jsp");
		}
	}

}
