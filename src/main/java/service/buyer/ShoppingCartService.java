package service.buyer;

import java.util.List;

import dto.buyer.ShoppingCartItem;

public interface ShoppingCartService {
	List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception;
}
