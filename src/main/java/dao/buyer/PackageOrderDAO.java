package dao.buyer;

import java.util.List;

import vo.PackOrderVO;

public interface PackageOrderDAO {
	List<PackOrderVO> selectPackOrderList(String userId);

}
