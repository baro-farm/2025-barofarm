package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.PackageOrderDAO;
import dao.buyer.PackageOrderDAOImpl;
import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import util.MybatisSqlSessionFactory;
import vo.AdminPackOrderVO;
import vo.PackOrderVO;

public class PackOrderServiceImpl implements PackOrderService {

	public PackOrderServiceImpl() {

	}

	@Override
	public List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception {
		List<PackOrderVO> packOrderList = new ArrayList<>();
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			packOrderList = packOrderDao.selectPackOrderList(userId);
			return packOrderList;
		}
	}

	@Override
	public void updateUserPackDeliveryStatus(Long pkOrderNum, String deliveryStatus) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			PackageOrder pkOrder = new PackageOrder();
			pkOrder.setPkOrderNum(pkOrderNum);
			pkOrder.setDeleveryStatus(deliveryStatus);

			packOrderDao.updateDeliveryStatus(pkOrder);
			sqlSession.commit();
		}
	}

	// seller List
	@Override
	public List<PackOrderVO> selectSellerPackOrderList(Long sellerNum, int offset, int pageSize, String dateType,
			String startDate, String endDate, String deliveryDay, String searchType, String searchKeyword)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryDay", deliveryDay);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);

			return packOrderDao.selectSellerPackOrderList(param);
		}
	}

	@Override
	public Integer selectCountSellerPackOrderList(Long sellerNum, String dateType, String startDate, String endDate,
			String deliveryDay, String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryDay", deliveryDay);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);

			return packOrderDao.selectCountSellerPackOrderList(param);
		}
	}

	@Override
	public void updateSellerPackTrackingNum(Long pkOrderNum, Integer trackingNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("pkOrderNum", pkOrderNum);
			param.put("trackingNum", trackingNum);

			packOrderDao.updatePackTrackingNum(param);
			sqlSession.commit();
		}
	}

	@Override
	public Integer selectUserPackOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deleveryStatus", deliveryStatus);
			return packOrderDao.selectUserPackOrderCount(param);
		}
	}

	@Override
	public List<PackOrderVO> selectUserPackOrderList(Long userNum, String startDate, String endDate,
			String deliveryStatus, int offset, int limit) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryStatus", deliveryStatus);
			param.put("offset", offset);
			param.put("limit", limit);
			System.out.println(param);
			return packOrderDao.selectUserPackOrderList(param);
		}
	}

	@Override
	public void insertPackageOrder(SqlSession sqlSession, PackageOrder packOrder) throws Exception {
		PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
		packOrderDao.insertPackageOrder(packOrder);

	}

	@Override
	public void insertSubscription(SqlSession sqlSession, PackageSubscribe sub) throws Exception {
		PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
		packOrderDao.insertSubscription(sub);

	}

	@Override
	public int countAdminPackOrderList(String startDate, String endDate, String searchType, String searchKeyword)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("searchType", searchType);
			paramMap.put("searchKeyword", searchKeyword);
			return packOrderDao.countAdminPackOrderList(paramMap);
		}
	}

	@Override
	public List<AdminPackOrderVO> selectAdminPackOrderList(int offset, int pageSize, String startDate, String endDate,
			String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("offset", offset);
			paramMap.put("pageSize", pageSize);
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("searchType", searchType);
			paramMap.put("searchKeyword", searchKeyword);
			return packOrderDao.selectAdminPackOrderList(paramMap);
		}

	}

	@Override
	public List<PackOrderVO> selectUserPackOrderDetailList(Long pkOrderNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageOrderDAO packOrderDao = new PackageOrderDAOImpl(sqlSession);
			return packOrderDao.selectPackDetailOrderList(pkOrderNum);
		}
	}
}
