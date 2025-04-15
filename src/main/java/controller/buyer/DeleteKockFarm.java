package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;

/**
 * Servlet implementation class DeleteKockFarm
 */
@WebServlet("/deleteKockFarm")
public class DeleteKockFarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteKockFarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long kockNum = Long.parseLong(request.getParameter("kockNum"));
		KockFarmService service = new KockFarmServiceImpl();
		try {
			service.deleteKockFarm(kockNum);
			response.sendRedirect(request.getContextPath()+"/kockFarmList");		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 수정에 오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
