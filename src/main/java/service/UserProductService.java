package service;

import java.util.List;

import dto.seller.ProductOption;
import util.PageInfo;
import vo.PackReviewVO;
import vo.PackageVO;
import vo.ProdReviewVO;
import vo.ProductVO;
import vo.QuestionVO;

public interface UserProductService {
	List<ProductVO> ProductByCategory(PageInfo pageInfo,Integer cateNum, String sort) throws Exception;
	Integer ProductCount(Integer cateNum) throws Exception;
	List<ProductVO> getBest5() throws Exception;
	List<ProductVO> getNew5() throws Exception;
	List<ProductVO> BestProductByPage(PageInfo pageInfo) throws Exception;
	List<ProductVO> NewProductByPage(PageInfo pageInfo) throws Exception;
	List<ProductVO> searchProducts(PageInfo pageInfo, String keyword, String sort) throws Exception;
	
	ProductVO DetailProduct(Long productNum) throws Exception;
	List<ProductOption> ProdOption(Long productNum) throws Exception;
	List<ProdReviewVO> ProdReview(Long prodNum, PageInfo pageInfo) throws Exception;
	Integer CountReview(Long prodNum) throws Exception;
	
	List<ProductVO> ProductBySellerNum(PageInfo pageInfo,Long sellerNum, String sort) throws Exception;
	Integer PackageCount(Integer cateNum) throws Exception;
	
	PackageVO DetailPackage(Long packageNum) throws Exception;
	List<PackReviewVO> PackReview(Long packNum, PageInfo pageInfo) throws Exception;
	Integer CountPackReview(Long packNum) throws Exception;
	List<PackageVO> PackageByCategory(PageInfo pageInfo,Integer cateNum, String sort) throws Exception;
	
	List<PackageVO> PackageByAll(PageInfo pageInfo, String sort) throws Exception;
	Integer countPackageByAll() throws Exception;
}
