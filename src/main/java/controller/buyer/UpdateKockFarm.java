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
 * Servlet implementation class UpdateKockFarm
 */
@WebServlet("/updateKockFarm")
public class UpdateKockFarm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKockFarm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Long kockNum = Long.parseLong(request.getParameter("kockNum"));

		KockFarmService service = new KockFarmServiceImpl();
		
		try {
			KockFarm kock = service.selectKockFarm(kockNum);
			request.setAttribute("kock", kock);
			request.getRequestDispatcher("/buyer/updateKockFarm.jsp?kockNum="+kockNum).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글을 찾아올 수 없습니다.");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
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
		
		KockFarm kockFarm = new KockFarm();
		kockFarm.setKockNum(Long.parseLong(multi.getParameter("kockNum")));
		kockFarm.setTitle(multi.getParameter("title"));
		kockFarm.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
		kockFarm.setPrice(Integer.parseInt(multi.getParameter("price")));
		
		DateTimeFormatter fommatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		kockFarm.setShipDate(LocalDate.parse(multi.getParameter("shipDate"),fommatter));
		
		kockFarm.setContent(multi.getParameter("content"));
		kockFarm.setImgUrl(multi.getFilesystemName("ifile"));
		
		
		KockFarmService service = new KockFarmServiceImpl();
		try {
			service.updateKockFarm(kockFarm);
			request.setAttribute("kockfarm", kockFarm);
			response.sendRedirect(request.getContextPath()+"/detailKockFarm?kockNum="+kockFarm.getKockNum());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 작성시오류가 발생했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
