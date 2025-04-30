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
		sqlSession.insert("mapper.alarm.insertAlarm",alarm);
		sqlSession.commit();
	}

	@Override
	public List<Alarm> selectRecentAlarmList(Long seNum) throws Exception {
		return sqlSession.selectList("mapper.alarm.selectRecentAlarmList", seNum);
	}

	@Override
	public int updateIsChecked(Long alarmNum) throws Exception {
		int num = sqlSession.update("mapper.alarm.updateIsChecked",alarmNum);
		sqlSession.commit();
		return num;
	}
}
