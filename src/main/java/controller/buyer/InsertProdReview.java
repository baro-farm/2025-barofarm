package controller.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.User;
import dto.buyer.ProdReview;
import dto.seller.Product;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import vo.ProdReviewVO;

/**
 * Servlet implementation class InsertProdReview
 */
@WebServlet("/insertProdReview")
public class InsertProdReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProdReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	    

	    System.out.println("userNum param: " + request.getParameter("userNum"));
	    System.out.println("productNum param: " + request.getParameter("productNum"));

	    Long userNum = Long.parseLong(request.getParameter("userNum"));
	    Long productNum = Long.parseLong(request.getParameter("productNum"));
	    Long pdOrderNum=Long.parseLong(request.getParameter("pdOrderNum"));
	    String storeName = request.getParameter("storeName");
	    String productName = request.getParameter("productName");
	    String imgUrl = request.getParameter("imgUrl");


	    try {
			request.setAttribute("userNum", userNum);
			request.setAttribute("productNum", productNum);
			request.setAttribute("productName", productName);
			request.setAttribute("storeName", storeName);
			request.setAttribute("imgUrl", imgUrl);
			request.setAttribute("pdOrderNum", pdOrderNum);
		    request.getRequestDispatcher("/buyer/insertProdReview.jsp").forward(request, response);

			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int size = 10*1024*1024;
		String path = request.getServletContext().getRealPath("upload");
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",
				new DefaultFileRenamePolicy());
	    Long userNum = Long.parseLong(multi.getParameter("userNum"));
	    Long productNum = Long.parseLong(multi.getParameter("productNum"));
	    Long pdOrderNum = Long.parseLong(multi.getParameter("pdOrderNum"));
	    int pdRating = Integer.parseInt(multi.getParameter("pdRating"));
	    String pdReviewContent = multi.getParameter("pdReviewContent");
	    String imgUrl = multi.getFilesystemName("reviewImage");		
	    
	    
	    ProdReview pdReview = new ProdReview();
	    pdReview.setUserNum(userNum);
	    pdReview.setProductNum(productNum);
	    pdReview.setPdRating(pdRating);
	    pdReview.setPdReviewContent(pdReviewContent);
	    pdReview.setPdReviewImgUrl(imgUrl);
	    pdReview.setPdOrderNum(pdOrderNum);
	    
	    ProdReviewSerivce service = new ProdReviewServiceImpl();
	   
	    
	    try {
			service.insertUserProdReview(pdReview);
			 response.sendRedirect(request.getContextPath() + "/allWrittenReviewList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
