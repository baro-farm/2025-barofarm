package dao.buyer;

import java.util.List;

import dto.buyer.PackageOrder;
import vo.PackOrderVO;

public interface PackageOrderDAO {
	List<PackOrderVO> selectPackOrderList(String userId) throws Exception;
	void updateDeliveryStatus(PackageOrder pkOrder) throws Exception;

}
