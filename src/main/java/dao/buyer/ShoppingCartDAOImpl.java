package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ShoppingCartItem;
import util.MybatisSqlSessionFactory;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception {
		return sqlSession.selectList("mapper.shoppingCart.selectCartByUser", userNum);
	}

}
