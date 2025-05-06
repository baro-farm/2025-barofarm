package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import vo.PackOrderVO;
import vo.ProdOrderVO;

/**
 * Servlet implementation class DetailOrderInfo
 */
@WebServlet("/detailPackOrderInfo")
public class DetailPackOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPackOrderInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PackOrderService service = new PackOrderServiceImpl();
		List<PackOrderVO> packOrderList = null;
		Long pkOrderNum = Long.parseLong(request.getParameter("pkOrderNum"));
		System.out.println(pkOrderNum);
		try {
			packOrderList = service.selectUserPackOrderDetailList(pkOrderNum);
			System.out.println(packOrderList);
			request.setAttribute("packOrderList", packOrderList);
			request.getRequestDispatcher("/buyer/detailPackOrderInfo.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
