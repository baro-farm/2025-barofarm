package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;
import vo.PackReviewVO;
import vo.PackageVO;
import vo.ProdReviewVO;
import vo.ProductVO;
import vo.QuestionVO;

public class UserProductDAOImpl implements UserProductDAO{

	@Override
	public List<ProductVO> selectProductByCategory(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.product.selectProductByCategory", param);
        }
	}

	@Override
	public Integer countProductByCategory(Integer cateNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectOne("mapper.product.countProductByCategory", cateNum);
        }
	}

	@Override
	public List<ProductVO> selectBest5() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectBest5");
		}
	}

	@Override
	public List<ProductVO> selectNew5() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectNew5");
		}
	}

	@Override
	public List<ProductVO> selectBestProductByPage() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectBestProductsByPage");
		}
	}

	@Override
	public List<ProductVO> selectNewProductByPage() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectNewProductsByPage");
		}
	}

	@Override
	public List<ProductVO> searchProducts(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.searchProduct", param);
		}
	}

	@Override
	public Integer countProductsByKeyword(String keyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.product.countProductsByKeyword", keyword);
		}
	}

	@Override
	public List<ProdReviewVO> selectProdReview(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.prodReview.selectProdReview", param);
		}
	}

	@Override
	public Integer countProdReview(Long prodNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.prodReview.countProdReview", prodNum);
		}
	}

	@Override
	public ProductVO selectDetailProduct(Long productNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.product.selectDetailProduct", productNum);
		}
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectProductOption", productNum);
		}
	}

	@Override
	public List<ProductVO> selectProductBySellerNum(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.product.selectProductBySellerNum", param);
		}
	}

	@Override
	public Integer countProductBySellerNum(Long sellerNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.product.countProductBySellerNum", sellerNum);
		}
	}

	@Override
	public PackageVO selectDetailPackage(Long packageNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.packageProduct.selectDetailPackage", packageNum);
		}
	}

	@Override
	public List<PackageVO> selectPackageByCategory(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.packageProduct.selectPackageByCategory", param);
		}
	}

	@Override
	public Integer countPackageByCategory(Integer packNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.packageProduct.countPackageByCategory", packNum);
		}
	}

	@Override
	public List<PackageVO> selectPackageByAll(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.packageProduct.selectPackageByAll", param);
		}
	}

	@Override
	public Integer countPackageByAll() throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.packageProduct.countPackageByAll");
		}
	}

	@Override
	public List<PackReviewVO> selectPackReview(Map<String, Object> param) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectList("mapper.packReview.selectPackReview", param);
		}
	}

	@Override
	public Integer countPackReview(Long packNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sqlSession.selectOne("mapper.packReview.countPackReview", packNum);
		}
	}

	

}
