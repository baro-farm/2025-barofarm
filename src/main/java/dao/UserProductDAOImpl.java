package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;
import vo.PackageVO;
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
		return sqlSession.selectList("mapper.prodReview.selectProdReview", param);
	}

	@Override
	public Integer countProdReview(Long prodNum) throws Exception {
		return sqlSession.selectOne("mapper.prodReview.countProdReview", prodNum);
	}

	@Override
	public ProductVO selectDetailProduct(Long productNum) throws Exception {
		 return sqlSession.selectOne("mapper.product.selectDetailProduct", productNum);
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductOption", productNum);
	}

	@Override
	public List<ProductVO> selectProductBySellerNum(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.product.selectProductBySellerNum", param);
	}

	@Override
	public Integer countProductBySellerNum(Long sellerNum) throws Exception {
		return sqlSession.selectOne("mapper.product.countProductBySellerNum", sellerNum);
	}

	@Override
	public PackageVO selectDetailPackage(Long packageNum) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.selectDetailPackage", packageNum);
	}

	@Override
	public List<PackageVO> selectPackageByCategory(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packageProduct.selectPackageByCategory", param);
	}

	@Override
	public Integer countPackageByCategory(Integer packNum) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.countPackageByCategory", packNum);
	}

	

}
