package controller.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import service.buyer.ProdQuestionService;
import service.buyer.ProdQuestionServiceImpl;
import vo.ProdOrderVO;

/**
 * Servlet implementation class ProdOrderList
 */
@WebServlet("/prodOrderList")
public class ProdOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdOrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ProdOrderService service = new ProdOrderServiceImpl();
		List<ProdOrderVO> prodOrderList = null;
		try {
			prodOrderList = service.selectUserProdOrderList("hong12");
			request.setAttribute("prodOrderList", prodOrderList);
			request.getRequestDispatcher("/buyer/productOrderList.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
