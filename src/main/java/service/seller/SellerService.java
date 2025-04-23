package service.seller;

import vo.SellerVO;

public interface SellerService {
	Long selectSellerNum(Long userNum) throws Exception;
	SellerVO getSerllerDetail(Long userNum) throws Exception;
	void changeIsAlarm(Long userNum) throws Exception;
}
