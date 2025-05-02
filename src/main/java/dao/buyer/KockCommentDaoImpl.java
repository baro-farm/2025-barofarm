package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;
import vo.KockCommentVO;

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

	@Override
	public List<BabyComment> babyCommentList(Long kcNum) throws Exception {
		return sqlSession.selectList("mapper.kockFarm.babyCommentList",kcNum);
	}
	
	@Override
	public List<KockCommentVO> selectAllKockCommentList(Map<String,Object>param) throws Exception {
		return sqlSession.selectList("mapper.kockFarm.selectAllKockCommentList",param);
	}

	@Override
	public Integer countAllComments(Long userNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.kockFarm.countAllComments",userNum);
	}

	@Override
	public List<KockCommentVO> selectAllSellerComments(SearchDtoSoy dto) {
        return sqlSession.selectList("mapper.kockFarm.selectAllSellerComments", dto);
	}

	@Override
	public int countAllSellerComments(SearchDtoSoy dto) {
        return sqlSession.selectOne("mapper.kockFarm.countAllSellerComments", dto);
	}
}
