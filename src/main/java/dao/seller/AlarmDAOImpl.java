package dao.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Alarm;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;
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
	public List<Alarm> selectRecentAlarmList(Long reNum) throws Exception {
		return sqlSession.selectList("mapper.alarm.selectRecentAlarmList", reNum);
	}

	@Override
	public int updateIsChecked(Long alarmNum) throws Exception {
		int num = sqlSession.update("mapper.alarm.updateIsChecked",alarmNum);
		sqlSession.commit();
		return num;
	}

	@Override
	public List<Alarm> selectAlarmBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectList("mapper.alarm.selectAlarmBySearchDto",dto);
	}

	@Override
	public int countAlarmBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectOne("mapper.alarm.countAlarmBySearchDto",dto);
	}

	@Override
	public int selectUnreadAlarmCount(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.alarm.selectUnreadAlarmCount",userNum);
	}
}
