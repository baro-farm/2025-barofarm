package dao.buyer;

import java.util.List;

import vo.ProdOrderVO;

public interface ProductOrder {
	List<ProdOrderVO> selectProdOrderList(Long userNum);
}
