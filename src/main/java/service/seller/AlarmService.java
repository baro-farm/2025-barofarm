package service.seller;

import java.util.List;

import dto.seller.Alarm;

public interface AlarmService {
	int sendKockFarmAlarm(Long cateNum, String cateName, Long buyerUserNum, Long kockNum) throws Exception;
	List<Alarm> recentAlarmList(Long seNum) throws Exception;
	boolean markAlarmAsRead(Long alarmNum) throws Exception;
}
