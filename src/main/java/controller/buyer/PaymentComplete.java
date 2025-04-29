package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.buyer.ProductOrder;
import dto.buyer.ShoppingCartItem;
import service.buyer.PaymentService;
import service.buyer.PaymentServiceImpl;
import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import util.MybatisSqlSessionFactory;

/**
 * Servlet implementation class PaymentComplete
 */
@WebServlet("/paymentComplete")
public class PaymentComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");

	    BufferedReader reader = request.getReader();
	    JsonObject jsonRequest = JsonParser.parseReader(reader).getAsJsonObject();
	    System.out.println("impUid: " + jsonRequest.get("imp_uid"));
	    System.out.println("amount: " + jsonRequest.get("amount"));
	    System.out.println("cartNums: " + jsonRequest.get("cartNums"));

	    String impUid = jsonRequest.get("imp_uid").getAsString();
	    int amount = jsonRequest.get("amount").getAsInt();
	    Gson gson = new Gson();
	    Long[] cartNums = gson.fromJson(jsonRequest.get("cartNums"), Long[].class);
	    List<Long> cartNumList = Arrays.asList(cartNums);
	  
	    System.out.println(impUid);
	    System.out.println(amount);
	    System.out.println("jsonRequest: " + jsonRequest);

	    
	    ServletContext context = request.getServletContext();
	    InputStream input = context.getResourceAsStream("/WEB-INF/config/config.properties");

	    Properties props = new Properties();
	    props.load(input);

	    String apiKey = props.getProperty("portone.apiKey");
	    String apiSecret = props.getProperty("portone.apiSecret");
	    System.out.println("apiKey: " + apiKey);
	    System.out.println("apiSecret: " + apiSecret);

	    System.out.println("apiKey: '" + apiKey + "'");
	    System.out.println("apiSecret: '" + apiSecret + "'");
	    System.out.println("apiKey length: " + apiKey.length());
	    System.out.println("apiSecret length: " + apiSecret.length());
	    
	    PaymentService paymentService = new PaymentServiceImpl(apiKey, apiSecret);
//		PaymentService paymentService = new PaymentServiceImpl();
		ProdOrderService prodOrderService = new ProdOrderServiceImpl();
		
		System.out.println("apiKey: " + apiKey);
    	System.out.println("apiSecret: " + apiSecret);
	    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false);
	    try {
	    	Map<String, String> paymentResult = paymentService.verifyPayment(impUid, amount);
	    	boolean isVerified = Boolean.parseBoolean(paymentResult.get("isVerified"));
	    	

//	    	String transactionId = "test_pg_tid";
//	    	String merchantUid = "test_merchant_uid";
	    	// 1. 포트원 서버에 결제 검증 요청
	        if (isVerified) {
		        String transactionId = (String) paymentResult.get("pg_tid");
		        String merchantUid = (String) paymentResult.get("merchant_uid");
	        	    
	            // ✅ 주문 처리 바로 여기!
	            ShoppingCartService cartService = new ShoppingCartServiceImpl();
	            List<ShoppingCartItem> cartItems = cartService.selectCartByCartNums(cartNumList);
	            System.out.println("cartNumList: " + cartNumList); 
	    	    System.out.println("cartItems: " + cartItems);
	    	    
	            // 총 금액 계산
	            int totalPrice = 0;
	            for (ShoppingCartItem item : cartItems) {
	                totalPrice += item.getTotalPrice();
	            }

	            // 2. 주문 테이블 insert (productorder)
	            HttpSession session = request.getSession();
	    	    User user = (User) session.getAttribute("user");
	            Long userNum = user.getUserNum();
	            String address = "임시 주소";
	            
	            ProductOrder productOrder = new ProductOrder(userNum, totalPrice, address, "배송준비", "결제완료");
	            prodOrderService.insertProductOrder(sqlSession, productOrder);
	            Long orderNum = productOrder.getPdOrderNum();
//	            Long orderNum = prodOrderService.insertProductOrder(sqlSession, userNum, totalPrice, address);
//	            Long orderNum = paymentService.insertProductOrder(userNum, impUid, totalPrice); // 주문번호 생성

	            // 3. 주문 상세 insert (productorderitem)
	            for (ShoppingCartItem item : cartItems) {
	            	prodOrderService.insertProductOrderItem(sqlSession, orderNum, item.getProductNum(), item.getOptionNum(), item.getQuantity(), item.getTotalPrice());
	            }

	            // 4. 재고 감소 / 판매량 증가
	            ProductService productService = new ProductServiceImpl();
	            for (ShoppingCartItem item : cartItems) {
	            	productService.adjustStock(sqlSession, item.getOptionNum(), item.getQuantity());
	            	productService.adjustSalesVolume(sqlSession, item.getProductNum(), item.getQuantity()); // 주문 시 증가
//	                productService.ad(sqlSession, item.getOptionNum(), item.getQuantity());
//	                productService.increaseSalesVolume(sqlSession, item.getProductNum(), item.getQuantity());

	            }

	            // 5. 장바구니 삭제
	            ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
	            shoppingCartService.deleteCartItems(sqlSession, cartNumList);

	            // 6. 결제 생성
//	            Map<String, String> paymentResult = paymentService.verifyPayment(impUid, amount);
	            
	     
	            paymentService.insertPayment(sqlSession, orderNum, amount, transactionId, "KCP-카드", impUid, merchantUid);
	            
	            // 응답
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("success", true);
	            response.getWriter().write(jsonResponse.toString());
	        } else {
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("success", false);
	            response.getWriter().write(jsonResponse.toString());
	        }
	        sqlSession.commit();
	    } catch (Exception e) {
	    	sqlSession.rollback();
	        e.printStackTrace();
	        JsonObject jsonResponse = new JsonObject();
	        jsonResponse.addProperty("success", false);
	        response.getWriter().write(jsonResponse.toString());
	    } finally {
	    	sqlSession.close();
	    }
	}

}
