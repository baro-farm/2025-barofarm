package controller.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.UserProductService;
import service.UserProductServiceImpl;
import util.PageInfo;
import vo.QuestionVO;

/**
 * Servlet implementation class DetailProductQA
 */
@WebServlet("/detailProductQA")
public class DetailProductQA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProductQA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Long productNum = Long.parseLong(request.getParameter("productNum"));
	        String pageStr = request.getParameter("prodQAPage");
	        int curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);

	        UserProductService service = new UserProductServiceImpl();

	        try {
	            PageInfo pageInfo = new PageInfo(curPage, 5);
	            pageInfo.setTotalCount(service.CountProdQA(productNum));

	            List<QuestionVO> qaList = service.ProdQA(productNum, pageInfo);

	            request.setAttribute("qaList", qaList);
	            request.setAttribute("qaPageInfo", pageInfo);
	            
	            // ⭐ JSP forward
	            request.getRequestDispatcher("detailQASection.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("err", "Q&A 조회 실패");
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
