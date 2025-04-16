package service.buyer;

import java.util.List;

import dao.buyer.ProductOrder;
import dao.buyer.ProductOrderImpl;
import vo.ProdOrderVO;

public class ProdOrderServiceImpl implements ProdOrderService {
	private ProductOrder prodOrderDao;
	public ProdOrderServiceImpl() {
		prodOrderDao =  new ProductOrderImpl();
	}
	
	@Override
	public List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
