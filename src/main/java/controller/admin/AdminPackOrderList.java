package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.PackOrderService;
import service.buyer.PackOrderServiceImpl;
import vo.AdminPackOrderVO;

/**
 * Servlet implementation class AdminPackOrderList
 */
@WebServlet("/adminPackOrderList")
public class AdminPackOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPackOrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String searchType = request.getParameter("searchType");
        String searchKeyword = request.getParameter("searchKeyword");

        int offset = (page - 1) * pageSize;

        PackOrderService service = new PackOrderServiceImpl();

        try {
            int totalCount = service.countAdminPackOrderList(startDate, endDate, searchType, searchKeyword);
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);

            int pageGroupSize = 5;
            int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
            int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
            int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

            List<AdminPackOrderVO> list = service.selectAdminPackOrderList(offset, pageSize, startDate, endDate, searchType, searchKeyword);

            request.setAttribute("packOrderList", list);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);
            request.setAttribute("pageGroupSize", pageGroupSize);

            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("searchType", searchType);
            request.setAttribute("searchKeyword", searchKeyword);

            request.getRequestDispatcher("/admin/adminPackOrderList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "패키지 주문 조회 중 오류 발생");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

}
