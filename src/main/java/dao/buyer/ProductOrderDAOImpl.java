package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import util.MybatisSqlSessionFactory;
import vo.ProdOrderVO;

public class ProductOrderDAOImpl implements ProductOrderDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<ProdOrderVO> selectProdOrderList(String userId) throws Exception{
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.prodOrder.prodOrderList",userNum);
	}

	@Override
	public List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) throws Exception{
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.prodOrder.prodDetailOrderList",pdOrderNum);
	}

	@Override
	public void updateDeliveryStatus(ProductOrder pdOrder) throws Exception {
		sqlSession.update("mapper.prodOrder.updatePdDeliveryStatus", pdOrder);
		sqlSession.commit();
	}

}
