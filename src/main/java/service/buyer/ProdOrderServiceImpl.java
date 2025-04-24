package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.ProductOrderDAO;
import dao.buyer.ProductOrderDAOImpl;
import dto.buyer.PackageOrder;
import dto.buyer.ProductOrder;
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

	@Override
	public void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception {
		ProductOrder pdOrder = new ProductOrder();
		pdOrder.setPdOrderNum(pdOrderNum);
		pdOrder.setDeleveryStatus(deliveryStatus);
		
		prodOrderDao.updateDeliveryStatus(pdOrder);		
	}

	@Override
	public Integer selectProductOrderCount(Long sellerNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
