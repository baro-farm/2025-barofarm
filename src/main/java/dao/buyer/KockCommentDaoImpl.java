package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.MybatisSqlSessionFactory;

public class KockCommentDaoImpl implements KockCommentDao {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public boolean insertKockComment(KockComment kComment) throws Exception {
		int result = sqlSession.insert("mapper.kockFarm.insertKockComment",kComment);
		sqlSession.commit();
		return result >0;
	}

	@Override
	public List<KockComment> kockCommentList(Long kockNum) throws Exception {
		return sqlSession.selectList("mapper.kockFarm.kockCommentList",kockNum);
	}

	@Override
	public KockComment selectKCommentByKcNum(Long kcNum) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.selectKCommentByKcNum",kcNum);
	}

	@Override
	public boolean insertBabyComment(BabyComment bComment) throws Exception {
		int result = sqlSession.insert("mapper.kockFarm.insertBabyComment",bComment);
		sqlSession.commit();
		return result >0 ;
	}

	@Override
	public BabyComment selectBCommentByReNum(Long reNum) throws Exception {
		return sqlSession.selectOne("mapper.kockFarm.selectBCommentByReNum",reNum);
	}

}
