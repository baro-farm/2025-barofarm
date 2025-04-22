package controller.seller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.User;
import dto.seller.PackageProduct;
import service.seller.PackageService;
import service.seller.PackageServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class InsertPackage
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
@WebServlet("/insertPackage")
public class InsertPackage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/seller/insertPackage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		String packageName = request.getParameter("product_name");
		String packageUnit = request.getParameter("package_unit");
		Integer packagePrice = Integer.parseInt(request.getParameter("package_price"));
		Integer stock = Integer.parseInt(request.getParameter("package_stock"));
		Integer cateNum = Integer.parseInt(request.getParameter("product_category"));
		Date startDate = Date.valueOf(request.getParameter("start_date"));
		Date endDate = Date.valueOf(request.getParameter("end_date"));
		String content = request.getParameter("package_content");
		System.out.println(request.getParameter("package_content"));
		
		// 이미지 처리
		Part imagePart = request.getPart("product_image");

        String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        String uploadPath = getServletContext().getRealPath("/upload/package");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        imagePart.write(uploadPath + File.separator + savedFileName);

        String imageUrl = "/upload/package/" + savedFileName;
        
        SellerService sellerService = new SellerServiceImpl();
        Long userNum = user.getUserNum();
        Long sellerNum = null;
        try {
        	sellerNum = sellerService.selectSellerNum(userNum); 
        } catch (Exception e) {
        	e.printStackTrace();
        }
 
        PackageProduct packageProduct = new PackageProduct(sellerNum, cateNum, packageName, content, packagePrice, stock, startDate, endDate, packageUnit, imageUrl);
        
        boolean result = false;
        
        try {
        	PackageService service = new PackageServiceImpl();
        	service.addPackageProduct(packageProduct);
        	result = true;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        if (result) {
            response.sendRedirect("/barofarm/insertPackage?success=true");
        } else {
            response.sendRedirect("/barofarm/insertPackage?success=false");
        }
	}

}
