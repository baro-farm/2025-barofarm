package dao.buyer;

import java.util.List;

import dto.buyer.ShoppingCartItem;

public interface ShoppingCartDAO {
	List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception;
}
