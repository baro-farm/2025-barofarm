package service.buyer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.KockFarmDao;
import dao.buyer.KockFarmDaoImpl;
import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;
import vo.KockCommentVO;
import vo.KockFarmVO;

public class KockFarmServiceImpl implements KockFarmService {

	public KockFarmServiceImpl() {
	}

	@Override
	public Long insertKockFarm(KockFarm kockFarm) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			kockFarmDao.insertKockFarm(kockFarm);
			sqlSession.commit();
			return kockFarm.getKockNum();
		}
	}

	@Override
	public KockFarm selectKockFarm(Long kockNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			return kockFarmDao.selectKockFarm(kockNum);
		}
	}

	@Override
	public KockFarm updateKockFarm(KockFarm kockFarm) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			KockFarm origin = kockFarmDao.selectKockFarm(kockFarm.getKockNum());
			String newImg = kockFarm.getImgUrl();
			if (newImg == null || newImg.trim().isEmpty()) {
				kockFarm.setImgUrl(origin.getImgUrl());
			}
			kockFarmDao.updateKockFarm(kockFarm);
			sqlSession.commit();
			return kockFarm;
		}
	}

	@Override
	public void deleteKockFarm(Long kockNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			kockFarmDao.deleteKockFarm(kockNum);
			sqlSession.commit();
		}
	}

	@Override
	public List<KockFarm> selectKFBySearchDto(SearchDtoSoy dto) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			return kockFarmDao.selectKFBySearchDto(dto);
		}
	}

	@Override
	public int countKFBySearchDto(SearchDtoSoy dto) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			return kockFarmDao.countKFBySearchDto(dto);
		}
	}

	// 매칭
	@Override
	public void insertMatching(Matching matching, Long kockNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			kockFarmDao.updateKockMatched(kockNum);
			kockFarmDao.insertMatching(matching);
			sqlSession.commit();
		}
	}

	@Override
	public List<KockFarmVO> selectMyPostList(Long userNum, LocalDate startDate, Boolean isMatched, int pageSize,
			int offset) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("isMatched", isMatched);
			param.put("pageSize", pageSize);
			param.put("offset", offset);
			return kockFarmDao.selectKockFarmPostList(param);
		}
	}

	@Override
	public Integer selectCountKockPost(Long userNum, LocalDate startDate, Boolean isMatched) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("isMatched", isMatched);
			return kockFarmDao.selectCountUserKockPost(param);
		}
	}

	@Override
	public Matching selectMatchingByKCNum(Long kockNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockFarmDao kockFarmDao = new KockFarmDaoImpl(sqlSession);
			return kockFarmDao.selectMatchingByKCNum(kockNum);
		}
	}

}
