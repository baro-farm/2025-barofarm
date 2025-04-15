package controller.buyer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.buyer.KockFarm;
import service.buyer.KockFarmService;
import service.buyer.KockFarmServiceImpl;

/**
 * Servlet implementation class InsertKockFarm
 */
@WebServlet("/insertKockFarm")
public class InsertKockFarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertKockFarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/buyer/insertKockFarm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String path = request.getServletContext().getRealPath("upload");
		int size = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",
				new DefaultFileRenamePolicy());
		
		Long userNum = Long.parseLong(multi.getParameter("userNum"));
		Long cateNum = Long.parseLong(multi.getParameter("cateNum"));
		String title = multi.getParameter("title");
		Integer quantity = Integer.parseInt(multi.getParameter("quantity"));
		Integer price = Integer.parseInt(multi.getParameter("price"));
		
		DateTimeFormatter  fommatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate shipDate = LocalDate.parse(multi.getParameter("shipDate"),fommatter);
		
		String content = multi.getParameter("content");
		String imgUrl = multi.getFilesystemName("ifile");
				
		KockFarm kockfarm = new KockFarm(userNum, cateNum, title, quantity, price, shipDate, content, imgUrl);
		KockFarmService service = new KockFarmServiceImpl();
		try {
			service.insertKockFarm(kockfarm);
			request.setAttribute("kockfarm", kockfarm);
			response.sendRedirect(request.getContextPath()+"/detailKockFarm?kockNum="+kockfarm.getKockNum());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 작성시오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
