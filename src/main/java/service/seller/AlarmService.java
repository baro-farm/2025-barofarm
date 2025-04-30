package service.seller;

import java.util.List;

import dto.buyer.KockFarm;
import dto.seller.Alarm;
import util.SearchDtoSoy;

public interface AlarmService {
	int sendKockFarmAlarm(Long cateNum, String cateName, Long buyerUserNum, Long kockNum) throws Exception;
	List<Alarm> recentAlarmList(Long seNum) throws Exception;
	boolean markAlarmAsRead(Long alarmNum) throws Exception;
	List<Alarm> selectAlarmBySearchDto(SearchDtoSoy dto) throws Exception;
	int countAlarmBySearchDto(SearchDtoSoy dto) throws Exception;
}
