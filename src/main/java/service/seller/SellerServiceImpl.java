package service.seller;

import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;

public class SellerServiceImpl implements SellerService {
	private SellerDAO sellerDao;
	
	public SellerServiceImpl() {
		sellerDao = new SellerDAOImpl();
	}
	
	@Override
	public Long selectSellerNum(Long userNum) throws Exception {
		return sellerDao.selectSellerNum(userNum);
	}

}
