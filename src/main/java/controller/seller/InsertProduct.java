package controller.seller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

/**
 * Servlet implementation class InsertProduct
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 10 * 1024 * 1024,
	    maxRequestSize = 50 * 1024 * 1024
	)
@WebServlet("/insertProduct")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/seller/insertProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
//		System.out.println("옵션명들: " + Arrays.toString(request.getParameterValues("option_name")));

		// 옵션 제외한 정보들
		String productName = request.getParameter("product_name");
		Integer price = Integer.parseInt(request.getParameter("product_price"));
		Integer stock = Integer.parseInt(request.getParameter("product_stock"));
		Integer cateNum = Integer.parseInt(request.getParameter("product_category"));
		String content = request.getParameter("product_content");
		
		// 이미지 처리
		Part imagePart = request.getPart("product_image");

        String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String savedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        String uploadPath = getServletContext().getRealPath("/upload/product");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        imagePart.write(uploadPath + File.separator + savedFileName);

        String imageUrl = "/upload/product/" + savedFileName;
        
        Long userNum = user.getUserNum();
        Product product = new Product(userNum, cateNum, productName, content, stock, price, imageUrl, true);
        
               
        String[] optionNames = request.getParameterValues("option_name");
        String[] optionPrices = request.getParameterValues("option_price");
        Long productNum = product.getProductNum();
        
        List<ProductOption> optionList = new ArrayList<>();
        if (optionNames != null && optionPrices != null) {
        	for (int i = 0; i < optionNames.length; i++) {
        		ProductOption opt = new ProductOption(productNum, optionNames[i], Integer.parseInt(optionPrices[i]));
        		optionList.add(opt);
        	}
        }
       
        try {
        	ProductService service = new ProductServiceImpl();
        	service.addProductWithOptions(product, optionList);        	
        } catch (Exception e) {
        	e.printStackTrace();
        }

        System.out.println("상품명: " + request.getParameter("product_name"));
        System.out.println("가격: " + request.getParameter("product_price"));
        System.out.println("재고: " + request.getParameter("product_stock"));
        System.out.println("카테고리: " + request.getParameter("product_category"));
        System.out.println("상세설명: " + request.getParameter("product_content"));
        System.out.println("userNum: "+ userNum);
        
        System.out.println("상품명: " + productName);
        System.out.println("상품 등록 결과 productNum = " + product.getProductNum());
        System.out.println("옵션 수 = " + optionNames.length);

	}

}
