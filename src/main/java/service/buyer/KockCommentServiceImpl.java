package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.KockCommentDao;
import dao.buyer.KockCommentDaoImpl;
import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;
import vo.KockCommentVO;

public class KockCommentServiceImpl implements KockCommentService {

	public KockCommentServiceImpl() {

	}

	@Override
	public boolean insertKockComment(KockComment kComment) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
			boolean success = kockCommentDao.insertKockComment(kComment);
			sqlSession.commit();
			return success;
		}
	}

	@Override
	public List<KockComment> kockCommentListWithBaby(Long kockNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
			List<KockComment> commentList = kockCommentDao.kockCommentList(kockNum);
			for (KockComment kc : commentList) {
				List<BabyComment> babyList = kockCommentDao.babyCommentList(kc.getKcNum());
				kc.setBabyComments(babyList);
			}
			return commentList;
		}
	}

	@Override
	public KockComment selectKCommentByKcNum(Long kcNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		return kockCommentDao.selectKCommentByKcNum(kcNum);
	}}

	@Override
	public boolean insertBabyComment(BabyComment bComment) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
			boolean success = kockCommentDao.insertBabyComment(bComment);
			sqlSession.commit();
			return success;
		}
	}

	@Override
	public BabyComment selectBCommentByReNum(Long reNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		return kockCommentDao.selectBCommentByReNum(reNum);
	}}

	@Override
	public List<KockCommentVO> selectUserMyCommentList(Long userNum, int pageSize, int offset) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		Map<String, Object> param = new HashMap<>();
		param.put("userNum", userNum);
		param.put("pageSize", pageSize);
		param.put("offset", offset);
		return kockCommentDao.selectAllKockCommentList(param);
		}
	}

	@Override
	public Integer selectCountAllComment(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		return kockCommentDao.countAllComments(userNum);
	}}

	@Override
	public List<KockCommentVO> getSellerComments(SearchDtoSoy dto) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		return kockCommentDao.selectAllSellerComments(dto);
	}}

	@Override
	public int countSellerComments(SearchDtoSoy dto) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			KockCommentDao kockCommentDao = new KockCommentDaoImpl(sqlSession);
		return kockCommentDao.countAllSellerComments(dto);
	}}

}
