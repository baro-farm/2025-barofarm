package controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.admin.CustomerService;
import service.admin.CSService;
import service.admin.CSServiceImpl;

/**
 * Servlet implementation class CustomerServiceList
 */
@WebServlet("/customerServiceList")
public class CustomerServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServiceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CSService service = new CSServiceImpl();
		try {
			int page = 1;
			int pageSize = 10;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if (page <= 0) page = 1;

			int offset = (page - 1) * pageSize;

			// 필터 조건 - 예: answerStatus (0: 미답변, 1: 답변완료)
			String answerStatusStr = request.getParameter("answerStatus");
			Integer answerStatus = null;
			if (answerStatusStr != null && !answerStatusStr.isEmpty()) {
				answerStatus = Integer.parseInt(answerStatusStr);
			}

			Map<String, Object> param = new HashMap<>();
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("answerStatus", answerStatus);

			List<CustomerService> list = service.allCustomerService(param);
			int totalCount = service.countCustomerService(param);
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);

			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			int jumpPrevPage = groupStartPage - 1 < 1 ? 1 : groupStartPage - 1;
			int jumpNextPage = groupEndPage + 1 > totalPages ? totalPages : groupEndPage + 1;

			request.setAttribute("customerServiceList", list);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("jumpPrevPage", jumpPrevPage);
			request.setAttribute("jumpNextPage", jumpNextPage);

			request.setAttribute("answerStatus", answerStatusStr); // 필터 상태 유지

			request.getRequestDispatcher("/admin/customerServiceList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "문의글 목록 조회 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
