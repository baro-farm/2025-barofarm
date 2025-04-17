package dao.buyer;

import java.util.List;

import vo.ProdOrderVO;

public interface ProductOrderDAO {
	List<ProdOrderVO> selectProdOrderList(String userId);
	List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum);

}
