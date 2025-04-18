package service.seller;

import java.util.List;

import dto.seller.Product;
import dto.seller.ProductOption;

public interface ProductService {
	void addProductWithOptions(Product product, List<ProductOption> options) throws Exception;
	void addProduct(Product product) throws Exception;
	void updateProduct(Product product) throws Exception;
	void stopProduct(Product product) throws Exception;
	void addProductOption(ProductOption productOption) throws Exception;
	void updateProductOption(ProductOption productOption) throws Exception;
}
