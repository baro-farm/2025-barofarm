package controller.seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dto.User;
import dto.seller.Product;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class UpdateProdStatus
 */
@WebServlet("/updateProdStatus")
public class UpdateProdStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProdStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		
    	SellerService sellerService = new SellerServiceImpl();
    	ProductService service = new ProductServiceImpl();
		
		BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        
        try {
        	User sessionUser = (User) request.getSession().getAttribute("user");
        	if(sessionUser == null) {
        		request.getRequestDispatcher("/login").forward(request, response);
        	}
        	
        	Long userNum = sessionUser.getUserNum();
        	Long sellerNum = sellerService.selectSellerNum(userNum);
        	
        	JSONParser parser = new JSONParser();
        	JSONArray jsonArray = (JSONArray) parser.parse(sb.toString());
        	
        	List<Map<String, Object>> productList = new ArrayList<>();
        	
        	for (Object obj : jsonArray) {
                 JSONObject json = (JSONObject) obj;
                 Long productNum = Long.parseLong(json.get("productNum").toString());
                 Boolean status = Boolean.parseBoolean(json.get("status").toString());

                 // ✅ 상품 판매자 검증
                 Product product = service.selectProduct(productNum);
                 if (!product.getSellerNum().equals(sellerNum)) {
                     return;
                 }
                 Map<String, Object> param = new HashMap<>();
                 
                 param.put("productNum", productNum);
                 param.put("status", status);
                 productList.add(param);
        	}
        	
        	service.updateSellerProductStatus(productList);
        	
        	response.getWriter().write("success");
        }catch(Exception e) {
        	e.printStackTrace();
        }

	}

}
