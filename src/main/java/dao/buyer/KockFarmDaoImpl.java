package dao.buyer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;
import vo.KockCommentVO;
import vo.KockFarmVO;

public class KockFarmDaoImpl implements KockFarmDao {
	private SqlSession sqlSession;

	public KockFarmDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public Long insertKockFarm(KockFarm kock) throws Exception {
		Long num = (long) sqlSession.insert("mapper.kockFarm.insertKockFarm",kock);
		return num;
	}

	@Override
	public KockFarm selectKockFarm(Long kockNum) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.selectKockFarm", kockNum);
	}

	@Override
	public void updateKockFarm(KockFarm kockFarm) {
		sqlSession.update("mapper.kockFarm.updateKockFarm", kockFarm);
	}

	@Override
	public void deleteKockFarm(Long kockNum) throws Exception {
		sqlSession.delete("mapper.kockFarm.deleteKockFarm", kockNum);
	}

	@Override
	public List<KockFarm> selectKFBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectList("mapper.kockFarm.selectKFBySearchDto",dto);
	}

	@Override
	public int countKFBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.countKFBySearchDto",dto);
	}
	
	//매칭
	@Override
	public void insertMatching(Matching matching) throws Exception {
		sqlSession.insert("mapper.kockFarm.insertMatching",matching);
	}

	@Override
	public void updateKockMatched(Long kockNum) throws Exception {
		sqlSession.update("mapper.kockFarm.updateKockMatched", kockNum);
	}

	@Override
	public List<KockFarmVO> selectKockFarmPostList(Map<String, Object> param) throws Exception {

		return sqlSession.selectList("mapper.kockFarm.selectKockFarmPostList", param);
	}

	@Override
	public Integer selectCountUserKockPost(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.kockFarm.selectCountUserKockPost",param);
	}

	@Override
	public Matching selectMatchingByKCNum(Long kockNum) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.selectMatchingByKCNum",kockNum);
	}

}
