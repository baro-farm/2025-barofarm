package controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;
import dto.seller.Advertisement;
import service.admin.BannerService;
import service.admin.BannerServiceImpl;
import service.seller.AdsService;
import service.seller.AdsServiceImpl;
import util.PageInfoSoy;
import util.SearchDtoSoy;

/**
 * Servlet implementation class AdsList
 */
@WebServlet("/sellerAdsList")
public class AdsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user =  null;
		
		if(session != null) {
			user=(User)session.getAttribute("user");
		}
		if(user == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
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
		
		AdsService service = new AdsServiceImpl();
		try {
			List<Advertisement> adsList = service.selectAdsBySearchDto(dto);
			int bannerCnt = service.countAdsWithPosting();
			int cnt = service.countAdsBySearchDtoSoy(dto);
			PageInfoSoy pageInfo = new PageInfoSoy(dto.getPage(), cnt, 5, dto.getRecordSize());

			request.setAttribute("pi", pageInfo);
			request.setAttribute("adsList", adsList);
			request.setAttribute("bannerCnt", bannerCnt);
			request.getRequestDispatcher("/seller/adsList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "판매자 광고리스트 획득 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
