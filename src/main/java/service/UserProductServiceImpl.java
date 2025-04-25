package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserProductDAO;
import dao.UserProductDAOImpl;
import util.PageInfo;
import vo.PackageVO;
import vo.ProdReviewVO;
import vo.ProductVO;

public class UserProductServiceImpl implements UserProductService{
	private UserProductDAO userProductDao;
	
	public UserProductServiceImpl() {
		userProductDao = new UserProductDAOImpl();
	}

	@Override
	public List<ProductVO> ProductByCategory(PageInfo pageInfo,Integer cateNum,String sort) throws Exception {
		// 1. 전체 상품 수
		Integer productCnt = userProductDao.countProductByCategory(cateNum);
		Integer allPage = (int)Math.ceil((double)productCnt/pageInfo.getPageSize());
		// 2. 페이지 네비게이션 계산
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; // 1,11,21,31 ...
		Integer endPage = startPage+10-1; 
		if(endPage>allPage) endPage=allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		Map<String, Object> param = new HashMap<>();
		param.put("cateNum", cateNum);
		param.put("start", pageInfo.getOffset());
        param.put("pageSize", pageInfo.getPageSize());
	    param.put("sort", sort);
		
		return userProductDao.selectProductByCategory(param);
	}

	@Override
	public List<ProductVO> getBest5() throws Exception {
		return userProductDao.selectBest5();
	}

	@Override
	public List<ProductVO> getNew5() throws Exception {
		return userProductDao.selectNew5();
	}

	@Override
	public List<ProductVO> BestProductByPage(PageInfo pageInfo) throws Exception {
		List<ProductVO> allProducts = userProductDao.selectBestProductByPage();
		Integer productCnt = allProducts.size();
		Integer allPage = (int)Math.ceil((double)productCnt/pageInfo.getPageSize());

		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
		Integer endPage = startPage+10-1;
		if(endPage>allPage) endPage=allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		// 5. 페이징 (Java에서 OFFSET 계산해서 자름)
		Integer offset = pageInfo.getOffset();
		Integer end = Math.min(offset + pageInfo.getPageSize(), productCnt);
	    return allProducts.subList(offset, end);
	}

	@Override
	public List<ProductVO> NewProductByPage(PageInfo pageInfo) throws Exception {
		List<ProductVO> allProducts = userProductDao.selectNewProductByPage();
		Integer productCnt = allProducts.size();
		Integer allPage = (int)Math.ceil((double)productCnt/pageInfo.getPageSize());

		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
		Integer endPage = startPage+10-1;
		if(endPage>allPage) endPage=allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		// 5. 페이징 (Java에서 OFFSET 계산해서 자름)
		Integer offset = pageInfo.getOffset();
		Integer end = Math.min(offset + pageInfo.getPageSize(), productCnt);
	    return allProducts.subList(offset, end);
	}

	@Override
	public List<ProductVO> searchProducts(PageInfo pageInfo, String keyword, String sort) throws Exception {
		Integer productCnt = userProductDao.countProductsByKeyword(keyword);
		Integer allPage = (int)Math.ceil((double) productCnt / 20);

		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
	    Integer endPage = startPage+10-1;
	    if(endPage>allPage) endPage=allPage;

	    pageInfo.setAllPage(allPage);
	    pageInfo.setStartPage(startPage);
	    pageInfo.setEndPage(endPage);

	    Map<String, Object> param = new HashMap<>();
	    param.put("keyword", keyword);
	    param.put("start", pageInfo.getOffset());
	    param.put("pageSize", pageInfo.getPageSize());
	    param.put("sort", sort);

	    return userProductDao.searchProducts(param);
	}

	@Override
	public ProductVO DetailProduct(Long productNum) throws Exception {
		return userProductDao.selectDetailProduct(productNum);
	}
	
	@Override
	public List<dto.seller.ProductOption> ProdOption(Long productNum) throws Exception {
		return userProductDao.selectProductOption(productNum);
	}
	
	@Override
	public List<ProdReviewVO> ProdReview(Long prodNum, PageInfo pageInfo) throws Exception {
		Integer prodCnt = userProductDao.countProdReview(prodNum);
		Integer allPage = (int)Math.ceil((double) prodCnt / 3);

		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
	    Integer endPage = startPage+10-1;
	    if(endPage>allPage) endPage=allPage;

	    pageInfo.setAllPage(allPage);
	    pageInfo.setStartPage(startPage);
	    pageInfo.setEndPage(endPage);

	    Map<String, Object> param = new HashMap<>();
	    param.put("productNum", prodNum);
	    param.put("start", pageInfo.getOffset());
	    param.put("pageSize", pageInfo.getPageSize());

	    return userProductDao.selectProdReview(param);
	}

	@Override
	public List<ProductVO> ProductBySellerNum(PageInfo pageInfo, Long sellerNum, String sort) throws Exception {
		// 1. 전체 상품 수
		Integer productCnt = userProductDao.countProductBySellerNum(sellerNum);
		Integer allPage = (int)Math.ceil((double)productCnt/pageInfo.getPageSize());
		// 2. 페이지 네비게이션 계산
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; // 1,11,21,31 ...
		Integer endPage = startPage+10-1; 
		if(endPage>allPage) endPage=allPage;
				
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
				
		Map<String, Object> param = new HashMap<>();
		param.put("sellerNum", sellerNum);
		param.put("start", pageInfo.getOffset());
		param.put("pageSize", pageInfo.getPageSize());
		param.put("sort", sort);
				
		return userProductDao.selectProductBySellerNum(param);
	}

	@Override
	public PackageVO DetailPackage(Long packageNum) throws Exception {
		return userProductDao.selectDetailPackage(packageNum);
	}

	@Override
	public List<PackageVO> PackageByCategory(PageInfo pageInfo, Integer cateNum, String sort) throws Exception {
		// 1. 전체 상품 수
		Integer packageCnt = userProductDao.countPackageByCategory(cateNum);
		Integer allPage = (int)Math.ceil((double)packageCnt/pageInfo.getPageSize());
		// 2. 페이지 네비게이션 계산
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; // 1,11,21,31 ...
		Integer endPage = startPage+10-1; 
		if(endPage>allPage) endPage=allPage;
				
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
				
		Map<String, Object> param = new HashMap<>();
		param.put("cateNum", cateNum);
		param.put("start", pageInfo.getOffset());
		param.put("pageSize", pageInfo.getPageSize());
		param.put("sort", sort);
				
		return userProductDao.selectPackageByCategory(param);
	}

	

	

}
