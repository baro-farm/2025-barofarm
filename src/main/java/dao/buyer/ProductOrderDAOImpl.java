package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.ProdOrderVO;

public class ProductOrderDAOImpl implements ProductOrderDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProdOrderVO> selectProdOrderList(String userId) {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.prodorder.prodOrderList",userNum);
	}

	@Override
	public List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.prodorder.prodDetailOrderList",pdOrderNum);
	}

}
