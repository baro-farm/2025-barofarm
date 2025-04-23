package service.buyer;

import java.util.List;

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
	public List<ShoppingCartItem> selectCartOptionsByProduct(Long productNum) throws Exception {
		return shoppingCartDao.selectCartOptionsByProduct(productNum);
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

}
