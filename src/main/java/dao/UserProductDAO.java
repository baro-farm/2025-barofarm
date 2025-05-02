package dao;

import java.util.List;
import java.util.Map;

import dto.seller.ProductOption;
import vo.PackReviewVO;
import vo.PackageVO;
import vo.ProdReviewVO;
import vo.ProductVO;
import vo.QuestionVO;

public interface UserProductDAO {
	List<ProductVO> selectProductByCategory(Map<String, Object> param) throws Exception;
	Integer countProductByCategory(Integer cateNum) throws Exception;
	
	List<ProductVO> selectBest5() throws Exception;
	List<ProductVO> selectNew5() throws Exception;
	
	List<ProductVO> selectBestProductByPage() throws Exception;
	List<ProductVO> selectNewProductByPage() throws Exception;
	
	List<ProductVO> searchProducts(Map<String, Object> param) throws Exception;
	Integer countProductsByKeyword(String keyword) throws Exception;

	List<ProdReviewVO> selectProdReview(Map<String, Object> param) throws Exception;
	Integer countProdReview(Long prodNum) throws Exception;
	
	ProductVO selectDetailProduct(Long productNum) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception;
	
	List<ProductVO> selectProductBySellerNum(Map<String, Object> param) throws Exception;
	Integer countProductBySellerNum(Long sellerNum) throws Exception;
	
	PackageVO selectDetailPackage(Long packageNum) throws Exception;
	List<PackageVO> selectPackageByCategory(Map<String, Object> param) throws Exception;
	Integer countPackageByCategory(Integer packNum) throws Exception;
	
	List<PackageVO> selectPackageByAll(Map<String, Object> param) throws Exception;
	Integer countPackageByAll() throws Exception;
	
	List<PackReviewVO> selectPackReview(Map<String, Object> param) throws Exception;
	Integer countPackReview(Long packNum) throws Exception;
	
	
}
