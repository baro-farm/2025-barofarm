package dao.buyer;

import java.util.List;

import dto.buyer.ShoppingCartItem;

public interface ShoppingCartDAO {
	List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception;
	public List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum) throws Exception;
	void updateCartQuantity(Long cartNum, Integer quantity) throws Exception;
	void insertCartOption(Long userNum, Long optionNum, Integer quantity) throws Exception;
	void deleteCartOption(Long cartNum) throws Exception;
}
