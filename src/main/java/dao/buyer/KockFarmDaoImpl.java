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
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertKockFarm(KockFarm kock) throws Exception {
		sqlSession.insert("mapper.kockFarm.insertKockFarm",kock);
		sqlSession.commit();
	}

	@Override
	public KockFarm selectKockFarm(Long kockNum) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.selectKockFarm", kockNum);
	}

	@Override
	public void updateKockFarm(KockFarm kockFarm) {
		sqlSession.update("mapper.kockFarm.updateKockFarm", kockFarm);
		sqlSession.commit();
	}

	@Override
	public void deleteKockFarm(Long kockNum) throws Exception {
		sqlSession.delete("mapper.kockFarm.deleteKockFarm", kockNum);
		sqlSession.commit();
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
		sqlSession.commit();
	}

	@Override
	public void updateKockMatched(Long kockNum) throws Exception {
		sqlSession.update("mapper.kockFarm.updateKockMatched", kockNum);
		sqlSession.commit();
	}

	@Override
	public List<KockFarmVO> selectKockFarmPostList(Long userNum,LocalDate startDate,Boolean isMatched) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("userNum", userNum);
		param.put("startDate", startDate);
		param.put("isMatched", isMatched);		
		return sqlSession.selectList("mapper.kockFarm.selectKockFarmPostList", param);
	}

}
