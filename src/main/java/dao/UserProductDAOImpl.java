package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.ProdReviewVO;
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
	public List<ProductVO> selectBestProductByPage() throws Exception {
		return sqlSession.selectList("mapper.product.selectBestProductsByPage");
	}

	@Override
	public List<ProductVO> selectNewProductByPage() throws Exception {
		return sqlSession.selectList("mapper.product.selectNewProductsByPage");
	}

	@Override
	public List<ProductVO> searchProducts(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.searchProduct", param);
	}

	@Override
	public Integer countProductsByKeyword(String keyword) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductsByKeyword", keyword);
	}

	@Override
	public List<ProdReviewVO> selectProdReview(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.productReview.selectProdReview", param);
	}

	@Override
	public Integer countProdReview(Integer prodNum) throws Exception {
		return sqlSession.selectOne("mapper.productReview.countProdReview", prodNum);
	}

	@Override
	public List<ProductVO> selectDetailProduct(Integer productNum) throws Exception {
		 return sqlSession.selectList("product.selectDetailProduct", productNum);
	}

	

}
