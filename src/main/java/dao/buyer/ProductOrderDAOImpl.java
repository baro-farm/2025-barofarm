package dao.buyer;

import java.util.List;
import java.util.Map;

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

	
	//seller list
	@Override
	public List<ProdOrderVO> selectSellerProductOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodOrder.selectSellerProdOrderList",param);
	}

	@Override
	public Integer sellectCountSellerProductOrderList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodOrder.sellectCountSellerProdOrderList",param);
	}
	
	public void updateProdTrackingNum(Map<String, Object> param) throws Exception{
		sqlSession.update("mapper.prodOrder.updatePdTrackingNum",param);
		sqlSession.commit();
		
	}
	

	@Override
	public Long insertProductOrder(SqlSession sqlSession, Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.prodOrder.insertProductOrder", param);
        return (Long) param.get("pdOrderNum");
	}
	
	@Override
	public void insertProductOrderItem(SqlSession sqlSession, Map<String, Object> param) throws Exception {
        sqlSession.insert("mapper.prodOrder.insertProductOrderItem", param);
		
	}

	@Override
	public Integer countSellerProductOrderList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum,
			int amount, int price) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
