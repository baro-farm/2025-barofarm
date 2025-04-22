package dao.buyer;

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
	public List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum) throws Exception {
		return sqlSession.selectList("mapper.shoppingCart.selectCartOptionsByProduct", productNum);
	}
	
	 @Override
	  public void updateCartQuantity(Long cartNum, Integer quantity) throws Exception {
	    sqlSession.update("mapper.shoppingCart.updateCartQuantity", 
	                      Map.of("cartNum", cartNum, "quantity", quantity));
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
}
