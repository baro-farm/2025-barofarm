package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Product;
import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;
import vo.ProductVO;

public class ProductDAOImpl implements ProductDAO {
	
    private final SqlSession sqlSession;

    public ProductDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
	@Override
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("mapper.product.insertProduct", product);
	}

	@Override
	public Product selectProduct(Long productNum) throws Exception {
		return sqlSession.selectOne("mapper.product.selectProduct", productNum);
	}

	@Override
	public void updateProduct(Product product) throws Exception {

		System.out.println("====== Product 업데이트 시도 ======");
		System.out.println("product.getSalesVolume() = " + product.getSalesVolume());

		sqlSession.update("mapper.product.updateProduct", product);
	}

	@Override
	public void updateProductStatus(Product product) throws Exception {
		sqlSession.update("mapper.product.stopProduct", product);
	}

	@Override
	public void insertProductOption(ProductOption productOption) throws Exception {
		sqlSession.insert("mapper.product.insertProductOption", productOption);
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductOption", productNum);
	}

	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		sqlSession.update("mapper.product.updateProductOption", productOption);
	}

	@Override
	public void deleteProductOption(Long optionNum) throws Exception {
		sqlSession.delete("mapper.product.deleteProductOption", optionNum);
	}


	//seller product List
	
	@Override
	public List<ProductVO> selectProductList(Map<String,Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductList",param);
	}

	@Override
	public void updateProductStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.product.updateProductStock",param);
	}

	@Override
	public void updateProductStatusBatch(List<Map<String, Object>> productList) throws Exception {
		sqlSession.update("mapper.product.updateProductStatusBatch", productList);
	}

	@Override
	public Integer countProductList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductList",param);
	}
	
	@Override
	public void adjustStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.product.adjustStock", param);
	}
	
	@Override
    public void adjustSalesVolume( Map<String, Object> param) throws Exception {
        sqlSession.update("mapper.product.adjustSalesVolume", param);
    }
}
