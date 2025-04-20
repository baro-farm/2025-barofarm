package controller.seller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.User;
import dto.seller.Product;
import dto.seller.ProductOption;
import service.seller.ProductService;
import service.seller.ProductServiceImpl;
import service.seller.SellerService;
import service.seller.SellerServiceImpl;

/**
 * Servlet implementation class UpdateProduct
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 해당 상품 정보를 가져오고 해당 상품의 판매자인지 판별

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/barofarm/login");
			return;
		}

		// 양배추 상품 번호 임시로 정함
//		Long productNum = 160L;
		Long productNum = 3L;
		ProductService productService = new ProductServiceImpl();
		SellerService sellerService = new SellerServiceImpl();
		Product product = null;
		List<ProductOption> productOption = null;
		Long sellerNum = 0L;

		try {
			product = productService.selectProduct(productNum);
			productOption = productService.selectProductOption(productNum);
			sellerNum = sellerService.selectSellerNum(user.getUserNum());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sellerNum == product.getSellerNum()) {
			request.setAttribute("product", product);
			request.setAttribute("productOption", productOption);
			request.getRequestDispatcher("/seller/updateProduct.jsp").forward(request, response);
		} else {
			response.sendRedirect("/barofarm/login");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		User user = (User) request.getSession().getAttribute("user");
//		if (user == null) {
//			response.sendRedirect("/barofarm/login");
//			return;
//		}
		
		if (user == null) {
		    response.sendRedirect("/barofarm/updateProduct?error=unauthorized");
		    return;
		}

		// 옵션 제외한 정보들
		Long productNum = 3L; // 임시값
		Long userNum = user.getUserNum();
		SellerService sellerService = new SellerServiceImpl();

		String productName = request.getParameter("product_name");
		Integer price = Integer.parseInt(request.getParameter("product_price"));
		Integer stock = Integer.parseInt(request.getParameter("product_stock"));
		Integer cateNum = Integer.parseInt(request.getParameter("product_category"));
		String content = request.getParameter("product_content");

		// 기존 상품 조회 (기존 이미지 경로를 유지하기 위함)
		ProductService productService = new ProductServiceImpl();
		Product existingProduct = null;
		Long sellerNum = null;
		
		try {
			existingProduct = productService.selectProduct(productNum);
			sellerNum = sellerService.selectSellerNum(userNum);
			
			if (existingProduct.getSellerNum() != sellerNum) {
//				response.sendRedirect("/barofarm/login");
				response.sendRedirect("/barofarm/updateProduct?error=unauthorized");
//			    return;
		        return; // 권한 없는 사용자 접근 방지
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 이미지 처리

//		String oldImagePath = getServletContext().getRealPath(product.getImgUrl());
//		File oldFile = new File(oldImagePath);
//		if (oldFile.exists()) {
//		    oldFile.delete();  // 실제 파일 삭제
//		}

		Part imagePart = request.getPart("product_image");
		String imageUrl = null;

		if (imagePart != null && imagePart.getSize() > 0) {
			String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
			String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

			String uploadPath = getServletContext().getRealPath("/upload/product");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();

			imagePart.write(uploadPath + File.separator + savedFileName);

			imageUrl = "/upload/product/" + savedFileName;

		} else {
			imageUrl = existingProduct.getImgUrl();
		}

		LocalDateTime currentDate = LocalDateTime.now();
		Product product = new Product(productNum, sellerNum, cateNum, productName, content, stock, price, imageUrl,
				true, currentDate);

		String[] optionNames = request.getParameterValues("option_name");
		String[] optionPrices = request.getParameterValues("option_price");
		String[] optionNums = request.getParameterValues("option_num");

		List<ProductOption> optionList = new ArrayList<>();
		for (int i = 0; i < optionNames.length; i++) {
		    Long optionNum = null;
		    if (optionNums != null && optionNums.length > i && optionNums[i] != null && !optionNums[i].isEmpty()) {
		        optionNum = Long.parseLong(optionNums[i]);
		    }
		    ProductOption opt = new ProductOption(optionNum, optionNames[i], Integer.parseInt(optionPrices[i]));
		    optionList.add(opt);
		}

		boolean result = false;
		try {
			productService.updateProduct(product, optionList);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result) {
			response.sendRedirect("/barofarm/updateProduct?success=true");
		} else {
			response.sendRedirect("/barofarm/updateProduct?success=false");
		}
	}

}
