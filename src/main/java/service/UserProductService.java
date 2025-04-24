package service;

import java.util.List;

import util.PageInfo;
import vo.ProdReviewVO;
import vo.ProductVO;

public interface UserProductService {
	List<ProductVO> ProductByCategory(PageInfo pageInfo,Integer cateNum, String sort) throws Exception;
	List<ProductVO> getBest5() throws Exception;
	List<ProductVO> getNew5() throws Exception;
	List<ProductVO> BestProductByPage(PageInfo pageInfo) throws Exception;
	List<ProductVO> NewProductByPage(PageInfo pageInfo) throws Exception;
	List<ProductVO> searchProducts(PageInfo pageInfo, String keyword, String sort) throws Exception;
	
	List<ProductVO> selectDetailProduct(Integer productNum) throws Exception;
	List<ProdReviewVO> getProdReview(Integer prodNum, PageInfo pageInfo) throws Exception;
}
