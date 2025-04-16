package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.MybatisSqlSessionFactory;

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
	public List<KockFarm> getKockFarmList() throws Exception {
		return sqlSession.selectList("mapper.kockFarm.getKockFarmList");
	}

	//매칭
	@Override
	public void insertMatching(Matching matching) throws Exception {
		sqlSession.insert("mapper.kockFarm.insertMatching",matching);
		sqlSession.commit();
	}
}
