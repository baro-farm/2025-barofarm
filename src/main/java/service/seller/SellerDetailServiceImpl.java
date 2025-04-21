package service.seller;

import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;

public class SellerDetailServiceImpl implements SellerDetailService{
	
	private SellerDAO sellerDao;
	
	public SellerDetailServiceImpl(){
		sellerDao = new SellerDAOImpl();
	}
	
	@Override
	public Long selectSellerNumById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sellerDao.selectSellerNumByUserID(userId);
	}

}
