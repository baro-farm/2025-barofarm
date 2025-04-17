package dao.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Product;
import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;

public class ProductDAOImpl implements ProductDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public void insertProductWithOptions(Product product, List<ProductOption> optionList) throws Exception {
		SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false); // autoCommit = false

		try {
			// 상품 insert
			sqlSession.insert("mapper.product.insertProduct", product);

			// 옵션이 있으면 반복 insert
			if (optionList != null && !optionList.isEmpty()) {
				for (ProductOption opt : optionList) {
					opt.setProductNum(product.getProductNum()); // FK 설정
					sqlSession.insert("mapper.product.insertProductOption", opt);
				}
			}

			sqlSession.commit(); // ✅ 전체 성공하면 commit

		} catch (Exception e) {
			sqlSession.rollback(); // ❌ 실패 시 전체 rollback
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	
	@Override
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("mapper.product.insertProduct", product);
		sqlSession.commit();
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("mapper.product.updateProduct", product);
		sqlSession.commit();
	}

	@Override
	public void stopProduct(Product product) throws Exception {
		sqlSession.update("mapper.product.stopProduct", product);
		sqlSession.commit();
	}
	
	@Override
	public void insertProductOption(ProductOption productOption) throws Exception {
		sqlSession.insert("mapper.product.insertProductOption", productOption);
		sqlSession.commit();
	}
	
	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		sqlSession.update("mapper.product.updateProductOption", productOption);
		sqlSession.commit();		
	}
}
