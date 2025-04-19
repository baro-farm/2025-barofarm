package service.seller;

import java.util.List;

import dto.seller.Product;
import dto.seller.ProductOption;

public interface ProductService {
	void addProduct(Product product, List<ProductOption> options) throws Exception;
	Product selectProduct(Long productNum) throws Exception;
	void updateProduct(Product product, List<ProductOption> options) throws Exception;
	void stopProduct(Product product) throws Exception;
	void addProductOption(ProductOption productOption) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception;
	void updateProductOption(ProductOption productOption) throws Exception;
	void deleteProductOption(Long optionNum) throws Exception;
}
