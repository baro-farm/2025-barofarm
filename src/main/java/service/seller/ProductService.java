package service.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Product;
import dto.seller.ProductOption;
import vo.ProductVO;

public interface ProductService {
	void addProduct(Product product, List<ProductOption> options) throws Exception;
	Product selectProduct(Long productNum) throws Exception;
	void updateProduct(Product product, List<ProductOption> options) throws Exception;
	void updateProductStatus(Product product) throws Exception;
	void addProductOption(ProductOption productOption) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception;
	void updateProductOption(ProductOption productOption) throws Exception;

	//product List
	List<ProductVO> selectSellerProductList(Long sellerNum,int offset, int pageSize,String sort, String sellStat) throws Exception;
	void updateProductStock(Long productNum, Integer stock) throws Exception;
	void updateSellerProductStatus(List<Map<String, Object>> productList) throws Exception;
	Integer selectCountSellerProductList(Long sellerNum,String sellStat) throws Exception;
	
	void deleteProductOption(Long optionNum) throws Exception;
	void adjustStock(SqlSession sqlSession, Long optionNum, int quantityDiff) throws Exception;
	void adjustSalesVolume(SqlSession sqlSession, Long productNum, int quantityDiff) throws Exception;

}
