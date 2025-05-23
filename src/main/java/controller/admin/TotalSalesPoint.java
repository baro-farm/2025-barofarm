package controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import service.admin.AdminPointService;
import service.admin.AdminPointServiceImpl;
import util.PageInfo;
import vo.PointVO;

/**
 * Servlet implementation class TotalSalesPoint
 */
@WebServlet("/totalSalesPoint")
public class TotalSalesPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalSalesPoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageStr = request.getParameter("page");
		Integer curPage = (pageStr == null || pageStr.trim().equals("")) ? 1 : Integer.parseInt(pageStr);
		String sort = null;
		AdminPointService service = new AdminPointServiceImpl()	;
		
		try {
			sort = request.getParameter("sort");
		    if(sort == null) {
		    	sort = "all";
		    }
		    System.out.println(sort);
			Integer totalCount = service.countTotalSalesPoint();
			PageInfo pageInfo = new PageInfo(curPage, 13, totalCount);
			
			List<PointVO> pointList = service.totalSalesPointList(pageInfo, sort);
			List<PointVO> stats = service.getMonthlyPoint();
			PointVO currentMonthPoint = service.currentMonthPoint();
			
			Gson gson = new GsonBuilder()
				    .registerTypeAdapter(LocalDate.class, 
				        (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
				            new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
				    .create();
			String statsJson = gson.toJson(stats);

			request.setAttribute("statsJson", statsJson);
			request.setAttribute("stats", stats);
			request.setAttribute("sort", sort);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("curPoint", currentMonthPoint);
			request.setAttribute("pointList", pointList);
			request.getRequestDispatcher("/admin/totalSalesPoint.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
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
