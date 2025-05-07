package service.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.seller.PackageDAO;
import dao.seller.PackageDAOImpl;
import dto.seller.PackageProduct;
import util.MybatisSqlSessionFactory;
import util.PageInfo;
import vo.PackSubVO;
import vo.PackageVO;

public class PackageServiceImpl implements PackageService {

	private PackageDAO packageDao(SqlSession sqlSession) {
		return new PackageDAOImpl(sqlSession);
	}

	@Override
	public void addPackageProduct(PackageProduct packageProduct) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			packageDao(sqlSession).insertPackageProduct(packageProduct);
			sqlSession.commit();
		}
	}

	@Override
	public PackageProduct selectPackageProduct(Long packageNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return packageDao(sqlSession).selectPackageProduct(packageNum);
		}
	}

	@Override
	public void updatePackageProduct(PackageProduct packageProduct) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			packageDao(sqlSession).updatePackageProduct(packageProduct);
			sqlSession.commit();
		}
	}

	@Override
	public List<PackageVO> selectSellerPackageList(Long sellerNum, int offset, int pageSize, String sort,
			String sellStat) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("sort", sort);
			param.put("sellStat", sellStat);
			return packageDao(sqlSession).selectPackageList(param);
		}
	}

	@Override
	public Integer selectCountSellerPackageList(Long sellerNum, String sellStat) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("sellStat", sellStat);
			return packageDao(sqlSession).countPackageList(param);
		}
	}

	@Override
	public void updatePackageStock(Long packageNum, Integer stock) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			Map<String, Object> param = new HashMap<>();
			param.put("packageNum", packageNum);
			param.put("stock", stock);
			packageDao(sqlSession).updatePackageStock(param);
			sqlSession.commit();
		}
	}

	@Override
	public void adjustStock(SqlSession sqlSession, Long packageNum, int quantityDiff) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("packageNum", packageNum);
		param.put("quantityDiff", quantityDiff);
		packageDao(sqlSession).adjustStock(param);
	}


	@Override
	public void adjustSalesVolume(SqlSession sqlSession, Long packageNum, int quantityDiff) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("packageNum", packageNum);
		param.put("quantityDiff", quantityDiff);
		packageDao(sqlSession).adjustSalesVolume(param);

	}

	@Override
	public List<PackSubVO> selectPackageSubList(PageInfo pageInfo, Long sellerNum, String startDate, String endDate,
			String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = util.MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("start", pageInfo.getOffset());
			param.put("pageSize", pageInfo.getPageSize());
			param.put("sellerNum", sellerNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			return packageDao(sqlSession).selectPackageSubList(param);
		}
	}

	@Override
	public Integer countPackageSubList(Long sellernum) throws Exception {
		try (SqlSession sqlSession = util.MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return packageDao(sqlSession).countPackageSubList(sellernum);
		}
	}
}
