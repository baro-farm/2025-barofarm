package controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
		String userNum = multi.getParameter("userNum");
		String cateNum = multi.getParameter("cateNum");
		String title = multi.getParameter("title");
		String quantity = multi.getParameter("quantity");
		String price = multi.getParameter("price");
		String shipDate = multi.getParameter("shipDate");
		String content = multi.getParameter("content");
		String imgUrl = multi.getParameter("ifile");
		
		
	}

}
