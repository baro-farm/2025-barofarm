package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.ProdReviewVO;

public class ProdReviewDAOImple implements ProdReviewDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProdReviewVO> selectWritableReview(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.prodReview.selectWritableReview",userNum);
	}

}
