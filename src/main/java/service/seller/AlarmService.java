package service.seller;

public interface AlarmService {
	int sendKockFarmAlarm(Long cateNum, String cateName, Long buyerUserNum) throws Exception;
}
