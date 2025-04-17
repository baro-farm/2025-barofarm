package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.Matching;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;

/**
 * Servlet implementation class KockMatching
 */
@WebServlet("/kockMatching")
public class KockMatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KockMatching() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Long kockNum = Long.parseLong(request.getParameter("kockNum"));
		Long kcNum = Long.parseLong(request.getParameter("kcNum"));
		Long buyerNum = Long.parseLong(request.getParameter("buyerNum"));
		Long sellerNum = Long.parseLong(request.getParameter("sellerNum"));
		
		Matching matching = new Matching(kockNum, kcNum, buyerNum, sellerNum);
		KockFarmService service = new KockFarmServiceImpl();
		
		try {
			service.insertMatching(matching);
			response.sendRedirect(request.getContextPath()+"/kockFarmList");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "매칭 오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
