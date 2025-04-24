package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Product;
import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;
import vo.ProductVO;

public class ProductDAOImpl implements ProductDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("mapper.product.insertProduct", product);
		sqlSession.commit();
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
		sqlSession.commit();
	}

	@Override
	public void updateProductStatus(Product product) throws Exception {
		sqlSession.update("mapper.product.stopProduct", product);
		sqlSession.commit();
	}

	@Override
	public void insertProductOption(ProductOption productOption) throws Exception {
		sqlSession.insert("mapper.product.insertProductOption", productOption);
		sqlSession.commit();
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductOption", productNum);
	}

	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		sqlSession.update("mapper.product.updateProductOption", productOption);
		sqlSession.commit();
	}

	@Override
	public void deleteProductOption(Long optionNum) throws Exception {
		sqlSession.delete("mapper.product.deleteProductOption", optionNum);
		sqlSession.commit();
	}


	//seller product List
	
	@Override
	public List<ProductVO> selectProductList(Map<String,Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductList",param);
	}

	@Override
	public void updateProductStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.product.updateProductStock",param);
		sqlSession.commit();
	}

	@Override
	public void updateProductStatusBatch(List<Map<String, Object>> productList) throws Exception {
		sqlSession.update("mapper.product.updateProductStatusBatch", productList);
		sqlSession.commit();
	}

	@Override
	public Integer countProductList(Long sellerNum) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductList",sellerNum);
	}
}
