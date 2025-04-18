package dao.seller;

import java.util.List;

import dto.seller.Product;
import dto.seller.ProductOption;

public interface ProductDAO {
	void insertProduct(Product product) throws Exception;
	Product selectProduct(Long productNum) throws Exception;
	void updateProduct(Product product) throws Exception;
	void stopProduct(Product product) throws Exception;
	void insertProductOption(ProductOption productOption) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception; 
	void updateProductOption(ProductOption productOption) throws Exception;
}
