package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.PackageSubDAO;
import dao.buyer.PackageSubDAOImpl;
import util.MybatisSqlSessionFactory;
import vo.PackSubVO;

public class PackSubServiceImpl implements PackSubService {

	public PackSubServiceImpl() {
	}

	@Override
	public Integer selectPackSubsCount(Long userNum, String startDate, String endDate, String deliveryStatus)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageSubDAO pkSubDao = new PackageSubDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();

			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryStatus", deliveryStatus);

			return pkSubDao.selectUserPackSubCount(param);
		}
	}

	@Override
	public List<PackSubVO> selectPackSubscribeList(Long userNum, String startDate, String endDate,
			String deliveryStatus, int offset, int pageSize) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			PackageSubDAO pkSubDao = new PackageSubDAOImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();

			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryStatus", deliveryStatus);
			param.put("offset", offset);
			param.put("pageSize", pageSize);

			return pkSubDao.selectUserPackSubList(param);
		}
	}

}
