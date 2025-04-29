package service.seller;

import java.util.List;

import dao.seller.AlarmDAO;
import dao.seller.AlarmDAOImpl;
import dto.seller.Alarm;
import util.FcmUtil;
import vo.SellerVO;

public class AlarmServiceImpl implements AlarmService {
	private AlarmDAO alarmDAO;
	public AlarmServiceImpl() {
		this.alarmDAO = new AlarmDAOImpl();
	}
	@Override
	public int sendKockFarmAlarm(Long cateNum, String cateName, Long buyerUserNum) throws Exception {
		List<SellerVO> sellerList = alarmDAO.selectSellerListByCate(cateNum);
		
		int count = 0;
		
		for (SellerVO seller:sellerList) {
			String fcmToken = seller.getFcmToken();
			Long sellerUserNum = Long.parseLong(seller.getUserNum());
			
			if (fcmToken != null) {
				String title="콕팜 알림";
				String body="["+ cateName + "] 에 새로운 콕팜 요청이 등록되었습니다!";
				FcmUtil.sendMessageTo(fcmToken, title, body);
				count++;
				Alarm alarm = new Alarm(buyerUserNum, sellerUserNum, "새로운 콕팜 요청이 등록되었습니다!", cateName , title);
				alarmDAO.insertAlarm(alarm);
			}
			
		}
		
		return count;
	}

}
