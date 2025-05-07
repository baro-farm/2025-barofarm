package service.buyer;

import java.util.List;

import vo.PackSubVO;

public interface PackSubService {
	Integer selectPackSubsCount(Long userNum, String startDate, String endDate, String deliveryStatus) throws Exception;
	List<PackSubVO> selectPackSubscribeList(Long userNum, String startDate, String endDate, String deliveryStatus, int offset, int pageSize) throws Exception;

}
