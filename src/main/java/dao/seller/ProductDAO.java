package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Product;
import dto.seller.ProductOption;
import vo.ProductVO;

public interface ProductDAO {
	void insertProduct(Product product) throws Exception;
	Product selectProduct(Long productNum) throws Exception;
	void updateProduct(Product product) throws Exception;
	void updateProductStatus(Product product) throws Exception;
	void insertProductOption(ProductOption productOption) throws Exception;
	List<ProductOption> selectProductOption(Long productNum) throws Exception; 
	void updateProductOption(ProductOption productOption) throws Exception;	
	void deleteProductOption(Long optionNum) throws Exception;
	
	//product List
	List<ProductVO> selectProductList(Map<String, Object> param) throws Exception;
	void updateProductStock(Map<String, Object> param) throws Exception;
	void updateProductStatusBatch(List<Map<String, Object>> productList) throws Exception;
	Integer countProductList(Map<String, Object> param) throws Exception;
	void adjustStock(SqlSession sqlSession, Map<String, Object> param) throws Exception;
	void adjustSalesVolume(SqlSession sqlSession, Map<String, Object> param) throws Exception;
}
