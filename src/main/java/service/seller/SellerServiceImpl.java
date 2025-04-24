package service.seller;

import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;
import vo.SellerVO;

public class SellerServiceImpl implements SellerService {
	private SellerDAO sellerDao;
	
	public SellerServiceImpl() {
		sellerDao = new SellerDAOImpl();
	}
	
	@Override
	public Long selectSellerNum(Long userNum) throws Exception {
		return sellerDao.selectSellerNum(userNum);
	}

	@Override
	public SellerVO getSerllerDetail(Long userNum) throws Exception {
		return sellerDao.selectSerllerDetail(userNum);
	}

	@Override
	public void changeIsAlarm(Long userNum) throws Exception {
		sellerDao.updateIsAlarm(userNum);
	}

}
