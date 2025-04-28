package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.ShoppingCartDAO;
import dao.buyer.ShoppingCartDAOImpl;
import dto.buyer.ShoppingCartItem;

public class ShoppingCartServiceImpl implements ShoppingCartService {
	private ShoppingCartDAO shoppingCartDao;

	public ShoppingCartServiceImpl() {
		shoppingCartDao = new ShoppingCartDAOImpl();
	}

	@Override
	public List<ShoppingCartItem> selectCartByUser(Long userNum) throws Exception {
		return shoppingCartDao.selectCartByUser(userNum);
	}

	@Override
	public List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum, Long userNum) throws Exception {
		return shoppingCartDao.selectCartOptionsByProduct(productNum, userNum);
	}

	@Override
	public void updateCartQuantity(Long cartNum, Integer quantity) throws Exception {
		shoppingCartDao.updateCartQuantity(cartNum, quantity);
	}

	@Override
	public void addCartOption(Long userNum, Long optionNum, Integer quantity) throws Exception {
		shoppingCartDao.insertCartOption(userNum, optionNum, quantity);
	}

	@Override
	public void deleteCartOption(Long cartNum) throws Exception {
		shoppingCartDao.deleteCartOption(cartNum);
	}
	
	@Override
	public List<ShoppingCartItem> selectCartByCartNums(List<Long> cartNums) throws Exception {
		return shoppingCartDao.selectCartByCartNums(cartNums);
	}

	@Override
    public void deleteCartItems(SqlSession sqlSession, List<Long> cartNums) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("cartNums", cartNums);
        shoppingCartDao.deleteCartItems(sqlSession, param);
    }
	
	
}
