package dao.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.PackageOrder;
import util.MybatisSqlSessionFactory;
import vo.PackOrderVO;

public class PackageOrderDAOImpl implements PackageOrderDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PackOrderVO> selectPackOrderList(String userId) throws Exception{
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.packOrder.packOrderList",userNum);
	}

	@Override
	public void updateDeliveryStatus(PackageOrder pkOrder) throws Exception {
		sqlSession.update("mapper.packOrder.updatePkDeliveryStatus", pkOrder);
		sqlSession.commit();
		
	}

}
