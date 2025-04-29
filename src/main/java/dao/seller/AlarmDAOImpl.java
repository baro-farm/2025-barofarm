package dao.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Alarm;
import util.MybatisSqlSessionFactory;
import vo.SellerVO;

public class AlarmDAOImpl implements AlarmDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<SellerVO> selectSellerListByCate(Long cateNum) throws Exception {
		return sqlSession.selectList("mapper.alarm.selectSellerListByCate", cateNum);
	}

	@Override
	public void insertAlarm(Alarm alarm) throws Exception {
		sqlSession.insert("mappe.alarm.insertAlarm",alarm);
	}
}
