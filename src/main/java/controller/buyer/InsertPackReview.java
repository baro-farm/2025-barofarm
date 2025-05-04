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
import service.buyer.PackReviewSerivce;
import service.buyer.PackReviewServiceImpl;
import service.buyer.ProdReviewSerivce;
import service.buyer.ProdReviewServiceImpl;
import service.buyer.UserService;
import service.buyer.UserServiceImpl;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import vo.PackReviewVO;
import vo.ProdReviewVO;

/**
 * Servlet implementation class InsertProdReview
 */
@WebServlet("/insertPackReview")
public class InsertPackReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPackReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	    

	    System.out.println("userNum param: " + request.getParameter("userNum"));
	    System.out.println("productNum param: " + request.getParameter("packageNum"));

	    Long packageNum = Long.parseLong(request.getParameter("packageNum"));
	    Long pkOrderNum=Long.parseLong(request.getParameter("pkOrderNum"));
	    String storeName = request.getParameter("storeName");
	    String packageName = request.getParameter("packageName");
	    String imgUrl = request.getParameter("imgUrl");


	    try {
			request.setAttribute("packageNum", packageNum);
			request.setAttribute("packageName", packageName);
			request.setAttribute("storeName", storeName);
			request.setAttribute("imgUrl", imgUrl);
			request.setAttribute("pkOrderNum", pkOrderNum);
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
	    Long packageNum = Long.parseLong(multi.getParameter("packageNum"));
	    Long pkOrderNum = Long.parseLong(multi.getParameter("pkOrderNum"));
	    int pkRating = Integer.parseInt(multi.getParameter("pkRating"));
	    String pkReviewContent = multi.getParameter("pkReviewContent");
	    String imgUrl = multi.getFilesystemName("pkReviewImage");		
	    
	    
	    PackReviewVO pkReview = new PackReviewVO();
	    pkReview.setUserNum(userNum);
	    pkReview.setPackageNum(packageNum);
	    pkReview.setPkRating(pkRating);
	    pkReview.setPkReviewContent(pkReviewContent);
	    pkReview.setPkReviewImgUrl(imgUrl);
	    pkReview.setPkOrderNum(pkOrderNum);
	    
	    PackReviewSerivce service = new PackReviewServiceImpl();
	   
	    
	    try {
			service.insertUserPackReview(pkReview);
			 response.sendRedirect(request.getContextPath() + "/allWrittenReviewList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
