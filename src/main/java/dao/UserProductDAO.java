package dao;

import java.util.List;
import java.util.Map;

import vo.ProdReviewVO;
import vo.ProductVO;

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
	Integer countProdReview(Integer prodNum) throws Exception;
	
	List<ProductVO> selectDetailProduct(Integer productNum) throws Exception;

}
