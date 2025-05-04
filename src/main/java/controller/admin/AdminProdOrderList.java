package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.buyer.ProdOrderService;
import service.buyer.ProdOrderServiceImpl;
import vo.AdminProdOrderVO;

/**
 * Servlet implementation class AdminProdOrderList
 */
@WebServlet(name = "adminProdOrderList", urlPatterns = { "/adminProdOrderList" })
public class AdminProdOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProdOrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdOrderService prodOrderService = new ProdOrderServiceImpl();

		try {
            request.setCharacterEncoding("UTF-8");

            // 1. 기본값 설정
            int page = 1;
            int pageSize = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int offset = (page - 1) * pageSize;

            // 2. 검색 조건 파라미터 받기
            String dateType = request.getParameter("dateType");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String searchType = request.getParameter("searchType");
            String searchKeyword = request.getParameter("searchKeyword");

            // 3. 주문 목록 조회
            List<AdminProdOrderVO> orderList = prodOrderService.selectAdminProductOrderList(
                    null, offset, pageSize, dateType, startDate, endDate, searchType, searchKeyword);

            int totalCount = prodOrderService.countAdminOrderList(dateType, startDate, endDate, searchType, searchKeyword);
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);
            if (page > totalPages && totalPages > 0) page = totalPages;
            if (page <= 0) page = 1;

            int pageGroupSize = 5;
            int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
            int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
            int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);

            // 4. request에 담기
            request.setAttribute("orderList", orderList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("groupStartPage", groupStartPage);
            request.setAttribute("groupEndPage", groupEndPage);
            request.setAttribute("pageGroupSize", pageGroupSize);

            // 검색 조건도 다시 넘겨줘야 검색 폼이 유지됨
            request.setAttribute("dateType", dateType);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("searchType", searchType);
            request.setAttribute("searchKeyword", searchKeyword);

            // 5. 포워딩
            request.getRequestDispatcher("/admin/adminProdOrderList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "주문 목록을 불러오는 중 오류가 발생했습니다.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

}
