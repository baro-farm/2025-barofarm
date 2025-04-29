package controller.seller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.seller.Point;
import dto.seller.UsePoint;
import service.seller.PointService;
import service.seller.PointServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;
import service.seller.UsePointService;
import service.seller.UsePointServiceImpl;
import util.PageInfoSoy;
import util.SearchDtoSoy;
import vo.SellerVO;

/**
 * Servlet implementation class FarmPointList
 */
@WebServlet("/farmPointList")
public class FarmPointList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FarmPointList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//searchDTO 세팅
		SearchDtoSoy dto = new SearchDtoSoy();
		String pageParam = request.getParameter("page");
		int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
		dto.setPage(page); // ← 무조건 setPage() 사용!
		dto.setKeyword(request.getParameter("keyword"));
		dto.setSearchType(request.getParameter("searchType"));
		dto.setStartDateFrom(request.getParameter("startDateFrom"));
		dto.setStartDateTo(request.getParameter("startDateTo"));
		dto.setUserNum(user.getUserNum());

		UsePointService uservice = new UsePointServiceImpl();
		PointService pservice = new PointServiceImpl();
		SellerService sservice = new SellerServiceImpl();
		Properties props = new Properties();
		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config/config.properties");
		if (input == null) {
		    System.out.println("properties 파일 경로 이상");
		} else {
		    props.load(input);
		}
		String impKey = props.getProperty("imp.key.soy");

		try {
			Point point = pservice.getPoint(user.getUserNum());
			List<UsePoint> usePointList = uservice.selectUsePointListBySearchDto(dto);
			SellerVO sellerDetail = sservice.getSerllerDetail(user.getUserNum());
			request.setAttribute("impKey", impKey);

			for (UsePoint u:usePointList) {
				LocalDateTime ldt = u.getCreatedAt();
				Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
				u.setCreatedAtDate(date);
			}
			
			int cnt = uservice.countUsePointBySearchDto(dto);
			PageInfoSoy pageInfo = new PageInfoSoy(dto.getPage(), cnt, 5, dto.getRecordSize());

			request.setAttribute("pi", pageInfo);
			request.setAttribute("usePointList", usePointList);
			request.setAttribute("isAlarm", sellerDetail.isAlarm());
			request.setAttribute("userName", sellerDetail.getUserName());
			request.setAttribute("phone", sellerDetail.getPhone());
			
			if (point==null) {
				Integer zero =0;
				request.setAttribute("point", zero);
			} else {
				request.setAttribute("point", point.getPoint());
			}
			request.getRequestDispatcher("/seller/farmPointList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "판매자의 포인트리스트 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
