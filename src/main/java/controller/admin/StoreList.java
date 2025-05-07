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

import dto.admin.Store;
import service.admin.StoreService;
import service.admin.StoreServiceImpl;

/**
 * Servlet implementation class StoreList
 */
@WebServlet("/storeList")
public class StoreList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreService service = new StoreServiceImpl();
		
		try {
			int page = 1;
			int pageSize = 10;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int offset = (page - 1) * pageSize;
			
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");

			Map<String, Object> param = new HashMap<>();
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("searchType", searchType);
			param.put("keyword", keyword);
			
			String isAlarmStr = request.getParameter("isAlarm");
			Integer isAlarm = null;
			if (isAlarmStr != null && !isAlarmStr.isEmpty()) {
			    isAlarm = Integer.parseInt(isAlarmStr);
			    param.put("isAlarm", isAlarm);
			}
			request.setAttribute("isAlarm", isAlarmStr);
			System.out.println("isAlarmStr = " + isAlarmStr);  // 추가
			System.out.println("isAlarm = " + isAlarm);        // 추가


			List<Store> storeList = service.allStore(param);
			int totalCount = service.getStoreCount(param);
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);
			if (page <= 0) page = 1;
			if (page > totalPages && totalPages > 0) page = totalPages;

			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			int jumpPrevPage = Math.max(groupStartPage - pageGroupSize, 1);
			int jumpNextPage = Math.min(groupStartPage + pageGroupSize, totalPages);

			request.setAttribute("storeList", storeList);
			request.setAttribute("searchType", searchType);
			request.setAttribute("keyword", keyword);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("jumpPrevPage", jumpPrevPage);
			request.setAttribute("jumpNextPage", jumpNextPage);

			request.getRequestDispatcher("/admin/storeList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "판매자 목록 조회 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
