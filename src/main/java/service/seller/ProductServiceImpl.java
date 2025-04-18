package service.seller;

import java.util.List;

import dao.seller.ProductDAO;
import dao.seller.ProductDAOImpl;
import dto.seller.Product;
import dto.seller.ProductOption;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao;

	public ProductServiceImpl() {
		productDao = new ProductDAOImpl();
	}
	
	@Override
	public void addProduct(Product product, List<ProductOption> options) throws Exception {
		productDao.insertProduct(product);
		
		for (ProductOption option: options) {
			option.setProductNum(product.getProductNum());
			productDao.insertProductOption(option);
		}
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
	
	@Override
	public void stopProduct(Product product) throws Exception {
		productDao.stopProduct(product);		
	}
	
	@Override
	public void addProductOption(ProductOption productOption) throws Exception {
		productDao.insertProductOption(productOption);		
	}
	
	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		productDao.updateProductOption(productOption);		
	}
}
