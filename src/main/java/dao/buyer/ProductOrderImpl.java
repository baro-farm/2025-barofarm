package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.ProdOrderVO;

public class ProductOrderImpl implements ProductOrder{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProdOrderVO> selectProdOrderList(Long userNum) {
		return sqlSession.selectList("mapper.prodorder.prodOrderList",userNum);
	}

}
