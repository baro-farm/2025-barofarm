package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.buyer.ProductOrder;
import dto.buyer.ProductOrderItem;
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
 * Servlet implementation class BuyNowComplete
 */
@WebServlet("/buyNowComplete")
public class BuyNowComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNowComplete() {
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

	    String impUid = jsonRequest.get("imp_uid").getAsString();
	    int amount = jsonRequest.get("amount").getAsInt();
	    String rName = jsonRequest.get("rName").getAsString();
	    String rPhone = jsonRequest.get("rPhone").getAsString();
	    String rAddress = jsonRequest.get("rAddress").getAsString();

	    Gson gson = new Gson();
	    Long[] optionNums = gson.fromJson(jsonRequest.get("optionNums"), Long[].class);
	    int[] quantities = gson.fromJson(jsonRequest.get("quantities"), int[].class);

	    // 환경변수에서 포트원 API 키 읽기
	    ServletContext context = request.getServletContext();
	    Properties props = new Properties();
	    props.load(context.getResourceAsStream("/WEB-INF/config/config.properties"));

	    String apiKey = props.getProperty("portone.apiKey").trim();
	    String apiSecret = props.getProperty("portone.apiSecret").trim();

	    PaymentService paymentService = new PaymentServiceImpl(apiKey, apiSecret);
	    ProdOrderService prodOrderService = new ProdOrderServiceImpl();
	    ProductService productService = new ProductServiceImpl();

	    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false);

	    try {
	        Map<String, String> paymentResult = paymentService.verifyPayment(impUid, amount);
	        boolean isVerified = Boolean.parseBoolean(paymentResult.get("isVerified"));

	        if (isVerified) {
	            String transactionId = paymentResult.get("pg_tid");
	            String merchantUid = paymentResult.get("merchant_uid");

	            // 주문자 정보
	            User user = (User) request.getSession().getAttribute("user");
	            Long userNum = user.getUserNum();

	            // 임시 상품 정보 생성
	            ShoppingCartService cartService = new ShoppingCartServiceImpl();
	            List<ShoppingCartItem> items = new ArrayList<>();
	            int totalPrice = 0;

	            for (int i = 0; i < optionNums.length; i++) {
	                ShoppingCartItem item = cartService.selectTempItem(optionNums[i], quantities[i]);
	                if (item != null) {
	                    items.add(item);
	                    totalPrice += item.getTotalPrice();
	                }
	            }

	            // 주문 테이블 insert
	            ProductOrder order = new ProductOrder(userNum, totalPrice, rAddress, "배송준비", "결제완료", rName, rPhone);
	            prodOrderService.insertProductOrder(sqlSession, order);
	            Long orderNum = order.getPdOrderNum();

	            // 주문 상세 insert
	            for (ShoppingCartItem item : items) {
	                ProductOrderItem poItem = new ProductOrderItem(orderNum, item.getProductNum(), item.getOptionNum(), item.getQuantity(), item.getTotalPrice());
//	                prodOrderService.insertProductOrderItem(sqlSession, poItem);
	                prodOrderService.insertProductOrderItem(sqlSession, orderNum, item.getProductNum(), item.getOptionNum(), item.getQuantity(), item.getTotalPrice());
	            }

	            // 재고 감소 / 판매량 증가
	            for (ShoppingCartItem item : items) {
	                productService.adjustStock(sqlSession, item.getOptionNum(), -item.getQuantity());
	                productService.adjustSalesVolume(sqlSession, item.getProductNum(), item.getQuantity());
	            }

	            // 결제 기록 저장
	            paymentService.insertPayment(sqlSession, orderNum, null, amount, transactionId, "KCP-카드", impUid, merchantUid);

	            // 응답
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("success", true);
	            jsonResponse.addProperty("transactionId", transactionId);
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
