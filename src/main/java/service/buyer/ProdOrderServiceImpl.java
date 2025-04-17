package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.ProductOrderDAO;
import dao.buyer.ProductOrderDAOImpl;
import vo.ProdOrderVO;

public class ProdOrderServiceImpl implements ProdOrderService {
	
	private ProductOrderDAO prodOrderDao;
	
	public ProdOrderServiceImpl() {
		prodOrderDao =  new ProductOrderDAOImpl();
	}
	
	@Override
	public List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception {
		List<ProdOrderVO> prodOrderList = new ArrayList<>();
		prodOrderList = prodOrderDao.selectProdOrderList(userId);
		return prodOrderList;
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception {
		// TODO Auto-generated method stub
		List<ProdOrderVO> prodOrderList = new ArrayList<>();
		prodOrderList = prodOrderDao.selectProdDetailOrderList(pdOrderNum);
		return prodOrderList;
	}

}
