package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.ProductVO;

public class UserProductDAOImpl implements UserProductDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProductVO> selectProductByCategory(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductByCategory", param);
	}

	@Override
	public Integer countProductByCategory(Integer cateNum) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductByCategory", cateNum);
	}

	@Override
	public List<ProductVO> selectBest5() throws Exception {
		return sqlSession.selectList("mapper.product.selectBest5");
	}

	@Override
	public List<ProductVO> selectNew5() throws Exception {
		return sqlSession.selectList("mapper.product.selectNew5");
	}

	@Override
	public List<ProductVO> selectBestProductsByPage(Integer start) throws Exception {
		return sqlSession.selectList("mapper.product.selectBestProductsByPage", start);
	}

	@Override
	public Integer countBestProducts() throws Exception {
		return sqlSession.selectOne("mapper.product.countBestProducts");
	}

	@Override
	public List<ProductVO> selectNewProductsByPage(Integer start) throws Exception {
		return sqlSession.selectList("mapper.product.selectNewProductsByPage", start);
	}

	@Override
	public Integer countNewProducts() throws Exception {
		return sqlSession.selectOne("mapper.product.countNewProducts");
	}

	@Override
	public List<ProductVO> searchProducts(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.searchProduct", param);
	}

	@Override
	public Integer countProductsByKeyword(String keyword) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductsByKeyword", keyword);
	}

	

}
