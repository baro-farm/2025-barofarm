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

import dto.User;
import service.admin.UserService;
import service.admin.UserServiceImpl;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/userList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserServiceImpl();

		try {
			int page = 1;
			int pageSize = 10;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if (page <= 0) page = 1;

			int offset = (page - 1) * pageSize;
			
			String searchType = request.getParameter("searchType");
            String keyword = request.getParameter("keyword");
            String isSellerStr = request.getParameter("isSeller");
            Integer isSeller = null;
            if (isSellerStr != null && !isSellerStr.isEmpty()) {
                isSeller = Integer.parseInt(isSellerStr);
            }

			Map<String, Object> param = new HashMap<>();
            param.put("offset", offset);
            param.put("pageSize", pageSize);
            param.put("searchType", searchType);
            param.put("keyword", keyword);
            param.put("isSeller", isSeller);

			List<User> userList = service.allUser(param);
			int totalCount = service.countUser(param);
			int totalPages = (int) Math.ceil((double) totalCount / pageSize);

			// 페이징 그룹 계산
			int pageGroupSize = 5;
			int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
			int groupStartPage = (currentGroup - 1) * pageGroupSize + 1;
			int groupEndPage = Math.min(groupStartPage + pageGroupSize - 1, totalPages);
			int jumpPrevPage = groupStartPage - 1 < 1 ? 1 : groupStartPage - 1;
			int jumpNextPage = groupEndPage + 1 > totalPages ? totalPages : groupEndPage + 1;
			
			request.setAttribute("userList", userList);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("groupStartPage", groupStartPage);
			request.setAttribute("groupEndPage", groupEndPage);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("jumpPrevPage", jumpPrevPage);
			request.setAttribute("jumpNextPage", jumpNextPage);
			
			request.setAttribute("searchType", searchType);
            request.setAttribute("keyword", keyword);
            request.setAttribute("isSeller", isSellerStr);

			request.getRequestDispatcher("/admin/userList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "회원 목록 조회 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
	}

}
