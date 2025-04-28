package service.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ShoppingCartItem;

public interface ShoppingCartService {
	List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception;
	List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum, Long userNum) throws Exception;
	void updateCartQuantity(Long cartNum, Integer quantity) throws Exception;
	void addCartOption(Long userNum, Long optionNum, Integer quantity) throws Exception;
	void deleteCartOption(Long cartNum) throws Exception;
	List <ShoppingCartItem> selectCartByCartNums(List<Long> cartNums) throws Exception;
	void deleteCartItems(SqlSession sqlSession, List<Long> cartNums) throws Exception;
}
