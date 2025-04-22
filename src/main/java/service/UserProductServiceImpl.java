package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserProductDAO;
import dao.UserProductDAOImpl;
import util.PageInfo;
import vo.ProductVO;

public class UserProductServiceImpl implements UserProductService{
	private UserProductDAO userProductDao;
	
	public UserProductServiceImpl() {
		userProductDao = new UserProductDAOImpl();
	}

	@Override
	public List<ProductVO> ProductByCategory(PageInfo pageInfo,Integer cateNum,String sort) throws Exception {
		Integer adminQACnt = userProductDao.countProductByCategory(cateNum);
		Integer allPage = (int)Math.ceil((double)adminQACnt/10);
		//startPage : 1~10=<1, 11~20=>11
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; // 1,11,21,31 ...
		//endPage : 1~10=>10, 11~20=>20
		Integer endPage = startPage+10-1; // 10,20,30,40 ...
		if(endPage>allPage) endPage=allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		Integer start = (pageInfo.getCurPage()-1)*10+1;
		
		Map<String, Object> param = new HashMap<>();
		param.put("cateNum", cateNum);
	    param.put("start", start);
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
		Integer totalCount = userProductDao.countBestProducts();
		Integer allPage = (int)Math.ceil((double)totalCount / 20);

		Integer curPage = pageInfo.getCurPage();
		Integer startPage = ((curPage - 1) / 10) * 10 + 1;
		Integer endPage = startPage + 9;
		if (endPage > allPage) endPage = allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		int start = (curPage - 1) * 20;
		return userProductDao.selectBestProductsByPage(start);
	}

	@Override
	public List<ProductVO> NewProductByPage(PageInfo pageInfo) throws Exception {
		 int totalCount = userProductDao.countNewProducts();
		    int allPage = (int)Math.ceil((double)totalCount / 20);

		    int curPage = pageInfo.getCurPage();
		    int startPage = ((curPage - 1) / 10) * 10 + 1;
		    int endPage = startPage + 9;
		    if (endPage > allPage) endPage = allPage;

		    pageInfo.setAllPage(allPage);
		    pageInfo.setStartPage(startPage);
		    pageInfo.setEndPage(endPage);

		    int start = (curPage - 1) * 20;
		    return userProductDao.selectNewProductsByPage(start);
	}

	@Override
	public List<ProductVO> searchProducts(PageInfo pageInfo, String keyword, String sort) throws Exception {
		int totalCount = userProductDao.countProductsByKeyword(keyword);
	    int allPage = (int)Math.ceil((double) totalCount / 20);

	    int curPage = pageInfo.getCurPage();
	    int startPage = ((curPage - 1) / 10) * 10 + 1;
	    int endPage = startPage + 9;
	    if (endPage > allPage) endPage = allPage;

	    pageInfo.setAllPage(allPage);
	    pageInfo.setStartPage(startPage);
	    pageInfo.setEndPage(endPage);

	    int start = (curPage - 1) * 20;

	    Map<String, Object> param = new HashMap<>();
	    param.put("keyword", keyword);
	    param.put("start", start);
	    param.put("sort", sort);

	    return userProductDao.searchProducts(param);
	}

	

}
