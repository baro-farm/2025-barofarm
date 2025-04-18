package controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.seller.Advertisement;
import service.seller.AdsService;
import service.seller.AdsServiceImpl;

/**
 * Servlet implementation class AdsList
 */
@WebServlet("/sellerAdsList")
public class AdsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		AdsService service = new AdsServiceImpl();
		
		try {
			List<Advertisement> adsList = service.selectAdsByUserNum(user.getUserNum());
			request.setAttribute("adsList", adsList);
			request.getRequestDispatcher("/seller/adsList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "판매자 광고리스트 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
