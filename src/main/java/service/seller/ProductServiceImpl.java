package service.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import dao.seller.ProductDAO;
import dao.seller.ProductDAOImpl;
import dao.seller.SellerDAO;
import dto.seller.Product;
import dto.seller.ProductOption;
import util.MybatisSqlSessionFactory;
import vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDao(SqlSession sqlSession) {
		return new ProductDAOImpl(sqlSession);
	}

	@Override
	public void addProduct(Product product, List<ProductOption> options) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			ProductDAO dao = productDao(sqlSession);
			dao.insertProduct(product);
			for (ProductOption option : options) {
				option.setProductNum(product.getProductNum());
				dao.insertProductOption(option);
			}
			sqlSession.commit();
		}
	}

	@Override
	public Product selectProduct(Long productNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return productDao(sqlSession).selectProduct(productNum);
		}
	}

	@Override
	public void updateProduct(Product product, List<ProductOption> options) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			ProductDAO dao = productDao(sqlSession);
			dao.updateProduct(product);
			List<ProductOption> oldOptions = dao.selectProductOption(product.getProductNum());

			// 현재 넘어온 옵션들의 번호만 추출
			List<Long> newOptionNums = options.stream().map(ProductOption::getOptionNum).filter(o -> o != null)
					.collect(Collectors.toList());

			// 1. 삭제할 옵션
			for (ProductOption oldOpt : oldOptions) {
				if (!newOptionNums.contains(oldOpt.getOptionNum())) {
					dao.deleteProductOption(oldOpt.getOptionNum());
				}
			}

			// 2. 추가/수정 처리
			for (ProductOption opt : options) {
				if (opt.getOptionNum() == null) {
					opt.setProductNum(product.getProductNum());
					dao.insertProductOption(opt);
				} else {
					dao.updateProductOption(opt);
				}
			}
			sqlSession.commit();
		}
	}

	@Override
	public void updateProductStatus(Product product) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			productDao(sqlSession).updateProductStatus(product);
			sqlSession.commit();
		}
	}

	@Override
	public void addProductOption(ProductOption productOption) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			productDao(sqlSession).insertProductOption(productOption);
			sqlSession.commit();
		}
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return productDao(sqlSession).selectProductOption(productNum);
		}
	}

	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			productDao(sqlSession).updateProductOption(productOption);
			sqlSession.commit();
		}
	}

	@Override
	public void deleteProductOption(Long optionNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			productDao(sqlSession).deleteProductOption(optionNum);
			sqlSession.commit();
		}
	}

	@Override
	public List<ProductVO> selectSellerProductList(Long sellerNum, int offset, int pageSize, String sort,
			String sellStat) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("sort", sort);
			param.put("sellStat", sellStat);
			return productDao(sqlSession).selectProductList(param);

		}
	}

	@Override
	public void updateProductStock(Long optionNum, Integer stock) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			Map<String, Object> param = new HashMap<>();
			param.put("optionNum", optionNum);
			param.put("stock", stock);
			productDao(sqlSession).updateProductStock(param);
			sqlSession.commit();
		}
	}

	@Override
	public void updateSellerProductStatus(List<Map<String, Object>> productList) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			productDao(sqlSession).updateProductStatusBatch(productList);
			sqlSession.commit();
		}
	}

	@Override
	public Integer selectCountSellerProductList(Long sellerNum, String sellStat) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("sellStat", sellStat);
			return productDao(sqlSession).countProductList(param);
		}
	}

	@Override
	public void adjustStock(SqlSession sqlSession, Long optionNum, int quantityDiff) throws Exception {

		Map<String, Object> param = new HashMap<>();
		param.put("optionNum", optionNum);
		param.put("quantityDiff", quantityDiff);
		productDao(sqlSession).adjustStock(param);
	}

	@Override
	public void adjustSalesVolume(SqlSession sqlSession, Long productNum, int quantityDiff) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("productNum", productNum);
		param.put("quantityDiff", quantityDiff);
		productDao(sqlSession).adjustSalesVolume(param);
	}
}
