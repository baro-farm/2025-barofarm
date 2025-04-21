package controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.buyer.ShoppingCartItem;
import service.buyer.ShoppingCartService;
import service.buyer.ShoppingCartServiceImpl;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/shoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/barofarm/login");
            return;
        }
        
        Long userNum = user.getUserNum();
        ShoppingCartService cartService = new ShoppingCartServiceImpl();

        try {
            List<ShoppingCartItem> cartList = cartService.selectCartByUser(userNum);
//            request.setAttribute("cartList", cartList);
            
            Map<String, List<ShoppingCartItem>> cartMap = new LinkedHashMap<>();

            for (ShoppingCartItem item : cartList) {
                String store = item.getStoreName();
                cartMap.computeIfAbsent(store, k -> new ArrayList<>()).add(item);
            }
            request.setAttribute("cartMap", cartMap);

            request.getRequestDispatcher("/buyer/shoppingCart.jsp").forward(request, response);
            System.out.println(cartList);
        } catch (Exception e) {
            e.printStackTrace();
//            response.sendRedirect("/barofarm/error.jsp");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
