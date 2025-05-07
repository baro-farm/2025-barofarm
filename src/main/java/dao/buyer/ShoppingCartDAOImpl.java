package dao.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ShoppingCartItem;
import util.MybatisSqlSessionFactory;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception {
		return sqlSession.selectList("mapper.shoppingCart.selectCartByUser", userNum);
	}

	@Override
	public List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum, Long userNum) throws Exception {
		Map<String, Object> params = new HashMap();
		params.put("productNum", productNum);
		params.put("userNum", userNum);
		return sqlSession.selectList("mapper.shoppingCart.selectCartOptionsByProduct", params);
	}

	@Override
	public void updateCartQuantity(Long cartNum, Integer quantity) throws Exception {
		sqlSession.update("mapper.shoppingCart.updateCartQuantity", Map.of("cartNum", cartNum, "quantity", quantity));
		sqlSession.commit();
	}

	@Override
	public void insertCartOption(Long userNum, Long optionNum, Integer quantity) throws Exception {
		sqlSession.insert("mapper.shoppingCart.insertCartOption",
				Map.of("userNum", userNum, "optionNum", optionNum, "quantity", quantity));
		sqlSession.commit();
	}

	@Override
	public void deleteCartOption(Long cartNum) throws Exception {
		sqlSession.delete("mapper.shoppingCart.deleteCartOption", cartNum);
		sqlSession.commit();
	}

	public List<ShoppingCartItem> selectCartByCartNums(List<Long> cartNums) throws Exception {
		return sqlSession.selectList("mapper.shoppingCart.selectCartByCartNums", cartNums);
	}
	
	@Override
    public void deleteCartItems(SqlSession sqlSession, Map<String, Object> param) throws Exception {
//        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        sqlSession.delete("mapper.shoppingCart.deleteCartItems", param);
    }
	
	@Override
	public Long getCartNum(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.shoppingCart.getCartNum", param);
	}
	
	@Override
	public void insertCart(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.shoppingCart.insertCart", param);
		sqlSession.commit();
	}
	
	@Override
	public Boolean isProductInCart(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.shoppingCart.isProductInCart", param);
	}
	
	@Override
	public void updateCartQuantityIncrease(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.shoppingCart.updateCartQuantityIncrease", param);
		sqlSession.commit();
	}
	
	@Override
    public ShoppingCartItem selectTempItem(Map<String, Object> param) throws Exception {
        return sqlSession.selectOne("mapper.shoppingCart.selectTempItem", param);
	}
}
