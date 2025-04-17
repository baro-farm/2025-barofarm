package service.buyer;

import java.util.ArrayList;
import java.util.List;

import dao.buyer.PackageOrderDAO;
import dao.buyer.PackageOrderDAOImpl;
import vo.PackOrderVO;

public class PackOrderServiceImpl implements PackOrderService {
	
	private PackageOrderDAO packOrderDao;
	
	public PackOrderServiceImpl() {
		packOrderDao = new PackageOrderDAOImpl();
	}
	
	@Override
	public List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception {
		List<PackOrderVO> packOrderList = new ArrayList<>();
		packOrderList = packOrderDao.selectPackOrderList(userId);
		return packOrderList;
	}

}
