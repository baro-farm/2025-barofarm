package controller.buyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.User;
import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import dto.seller.PackageProduct;
import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import service.buyer.PaymentService;
import service.buyer.PaymentServiceImpl;
import service.seller.PackageService;
import service.seller.PackageServiceImpl;
import util.MybatisSqlSessionFactory;

/**
 * Servlet implementation class PackageSubscribe
 */
@WebServlet("/packageSubscribe")
public class PackageSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackageSubscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}

		BufferedReader reader = request.getReader();
		SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false);
		
		ServletContext context = request.getServletContext();
	    Properties props = new Properties();
	    props.load(context.getResourceAsStream("/WEB-INF/config/config.properties"));

	    String apiKey = props.getProperty("portone.apiKey").trim();
	    String apiSecret = props.getProperty("portone.apiSecret").trim();
	    
		try {
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

			Long userNum = user.getUserNum();
			Long packageNum = json.get("packageNum").getAsLong();
			String impUid = json.get("imp_uid").getAsString();
			String merchantUid = json.get("merchant_uid").getAsString();
			int amount = json.get("amount").getAsInt();
			String rname = json.get("rname").getAsString();
			String rphone = json.get("rphone").getAsString();
			String addr = json.get("raddress").getAsString();
			System.out.println(rname);
			System.out.println(addr);
			// 서비스 초기화
			PaymentService paymentService = new PaymentServiceImpl(apiKey, apiSecret);
			PackOrderService packOrderService = new PackOrderServiceImpl();
			PackageService packageService = new PackageServiceImpl();
			
			// 결제 검증
			JsonObject jsonResponse = new JsonObject();
			var result = paymentService.verifyPayment(impUid, amount);
			if (!Boolean.parseBoolean(result.get("isVerified"))) {
				jsonResponse.addProperty("success", false);
				response.getWriter().write(jsonResponse.toString());
				return;
			}

			String transactionId = result.get("pg_tid");
			String verifiedMerchantUid = result.get("merchant_uid");
		
			// 패키지 정보
			PackageProduct packageProduct = packageService.selectPackageProduct(packageNum);
			
			// 1. 구독 정보 저장
			PackageSubscribe sub = new PackageSubscribe(userNum, packageNum, packageProduct.getSellerNum(), packageProduct.getStartDate(), packageProduct.getEndDate(), 1, 1, "월", "30", rname, rphone, addr);	
			packOrderService.insertSubscription(sqlSession, sub);
			Long subNum = sub.getSubNum();
			
			// 2. 주문/결제 정보 저장
			PackageOrder packOrder = new PackageOrder(packageNum, userNum, subNum, amount, "준비중");
			packOrderService.insertPackageOrder(sqlSession, packOrder);
			Long pkOrderNum = packOrder.getPkOrderNum();
			
			// 3. 결제 정보 저장		
			paymentService.insertPayment(sqlSession, null, pkOrderNum, amount,transactionId, "KCP-카드", impUid, verifiedMerchantUid);

			// 4. 재고/판매량 반영
			packageService.adjustStock(sqlSession, packageNum, -1);
			packageService.adjustSalesVolume(sqlSession, packageNum, 1);
			
			jsonResponse.addProperty("success", true);
			jsonResponse.addProperty("transactionId", transactionId);
			response.getWriter().write(jsonResponse.toString());
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
