package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.User;
import dto.buyer.ShoppingCartItem;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;

/**
 * Servlet implementation class ProductOptions
 */
@WebServlet("/getCartOptions")
public class GetCartOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCartOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long productNum = Long.parseLong(request.getParameter("productNum"));
		User user = (User) request.getSession().getAttribute("user");
		Long userNum = user.getUserNum();

		// 옵션 목록 가져오기
	    ShoppingCartService service = new ShoppingCartServiceImpl();
	    
	    response.setContentType("application/json;charset=UTF-8");
	    try {
	    	List<ShoppingCartItem> options = service.selectCartOptionsByProduct(productNum, userNum);
	    	new Gson().toJson(options, response.getWriter());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
