package dao.seller;

import java.util.List;

import dto.seller.Alarm;
import vo.SellerVO;

public interface AlarmDAO {
	List<SellerVO> selectSellerListByCate(Long cateNum) throws Exception;
	void insertAlarm(Alarm alarm) throws Exception;
	List<Alarm> selectRecentAlarmList(Long seNum) throws Exception;
	int updateIsChecked(Long alarmNum) throws Exception;
}
