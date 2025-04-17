package controller.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.buyer.KockComment;
import dto.buyer.KockFarm;
import service.buyer.KockCommentService;
import service.buyer.KockCommentServiceImpl;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;

/**
 * Servlet implementation class detailKockFarm
 */
@WebServlet("/detailKockFarm")
public class DetailKockFarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailKockFarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Long kockNum = Long.parseLong(request.getParameter("kockNum"));

		KockFarmService service = new KockFarmServiceImpl();
		KockCommentService kcService = new KockCommentServiceImpl();
		try {
			KockFarm kock = service.selectKockFarm(kockNum);
			List<KockComment> commentList = kcService.kockCommentListWithBaby(kockNum);
			request.setAttribute("kock", kock);
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/common/detailKockFarm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글을 찾아올 수 없습니다.");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
