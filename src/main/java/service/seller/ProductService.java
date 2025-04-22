package service.seller;

import java.util.List;

import dto.seller.Product;
import dto.seller.ProductOption;

public interface ProductService {
	void addProduct(Product product, List<ProductOption> options) throws Exception;
	Product selectProduct(Long productNum) throws Exception;
	void updateProduct(Product product, List<ProductOption> options) throws Exception;
	void updateProductStatus(Product product) throws Exception;
	void addProductOption(ProductOption productOption) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception;
	void updateProductOption(ProductOption productOption) throws Exception;
	
	//product List
	List<Product> selectSellerProductList(Long sellerNum) throws Exception;
	void deleteProductOption(Long optionNum) throws Exception;
}
