package controller.seller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
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
 * Servlet implementation class UpdatePackage
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
@WebServlet("/updatePackage")
public class UpdatePackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePackage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 해당 상품 정보를 가져오고 해당 상품의 판매자인지 판별

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}
		
		Long packageNum = Long.parseLong(request.getParameter("packageNum"));
		PackageService packageSerivce = new PackageServiceImpl();
		SellerService sellerService = new SellerServiceImpl();
		PackageProduct packageProduct = null;
		Long sellerNum = 0L;
		
		try {
			packageProduct = packageSerivce.selectPackageProduct(packageNum);
			sellerNum = sellerService.selectSellerNum(user.getUserNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (sellerNum == packageProduct.getSellerNum()) {
			request.setAttribute("packageProduct", packageProduct);
			request.getRequestDispatcher("/seller/updatePackage.jsp").forward(request, response);
		} else {
			response.sendRedirect("/barofarm/login");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
		    response.sendRedirect("/barofarm/updateProduct?error=unauthorized");
		    return;
		}
		
		Long packageNum = Long.parseLong(request.getParameter("packageNum"));
		Long userNum = user.getUserNum();
		SellerService sellerService = new SellerServiceImpl();
		
		String packageName = request.getParameter("product_name");
		String packageUnit = request.getParameter("package_unit");
		Integer packagePrice = Integer.parseInt(request.getParameter("package_price"));
		Integer stock = Integer.parseInt(request.getParameter("package_stock"));
		Integer cateNum = Integer.parseInt(request.getParameter("product_category"));
		Date startDate = Date.valueOf(request.getParameter("start_date"));
		Date endDate = Date.valueOf(request.getParameter("end_date"));
		String content = request.getParameter("update_package_content");
		
		PackageService packageService = new PackageServiceImpl();
		PackageProduct existingPackage = null;
		Long sellerNum = null;
		
		try {
			existingPackage = packageService.selectPackageProduct(packageNum);
			sellerNum = sellerService.selectSellerNum(userNum);
			
			if(existingPackage.getSellerNum() != sellerNum) {
				response.sendRedirect("/barofarm/updatePackage?error=unauthorized");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Part imagePart = request.getPart("product_image");
		String imageUrl = null;

		if (imagePart != null && imagePart.getSize() > 0) {
			String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
			String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

			String uploadPath = getServletContext().getRealPath("/upload/package");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();

			imagePart.write(uploadPath + File.separator + savedFileName);

			imageUrl = "/upload/package/" + savedFileName;

		} else {
			imageUrl = existingPackage.getImgUrl();
		}
		
		LocalDateTime currentDate = LocalDateTime.now();
		PackageProduct packageProduct = new PackageProduct(packageNum, cateNum, packageName, content, packagePrice, stock, startDate, endDate, packageUnit, currentDate, imageUrl);
		
		boolean result = false;
		try {
			packageService.updatePackageProduct(packageProduct);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result) {
			response.sendRedirect("/barofarm/detailPackage?packageNum=" + packageNum);
		} else {
//			response.sendRedirect("/barofarm/updatePackage?success=false");
		}
	}

}
