package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ShoppingCartItem;

public interface ShoppingCartDAO {
	List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception;
	List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum, Long userNum) throws Exception;
	void updateCartQuantity(Long cartNum, Integer quantity) throws Exception;
	void insertCartOption(Long userNum, Long optionNum, Integer quantity) throws Exception;
	void deleteCartOption(Long cartNum) throws Exception;
	List<ShoppingCartItem> selectCartByCartNums(List<Long> cartNums) throws Exception;
	void deleteCartItems(SqlSession sqlSession, Map<String, Object> param) throws Exception;
	Long getCartNum(Map<String, Object> param) throws Exception;
	void insertCart(Map<String, Object> param) throws Exception;
	Boolean isProductInCart(Map<String, Object> param) throws Exception;
	void updateCartQuantityIncrease(Map<String, Object> param) throws Exception;
}
