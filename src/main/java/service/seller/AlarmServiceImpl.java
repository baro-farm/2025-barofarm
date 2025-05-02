package service.seller;

import java.util.List;

import dao.seller.AlarmDAO;
import dao.seller.AlarmDAOImpl;
import dto.seller.Alarm;
import dto.seller.UsePoint;
import util.FcmUtil;
import util.SearchDtoSoy;
import vo.SellerVO;

public class AlarmServiceImpl implements AlarmService {
	private AlarmDAO alarmDAO;
	private PointService pointService;
	private UsePointService usePointService;
	public AlarmServiceImpl() {
		this.alarmDAO = new AlarmDAOImpl();
		this.pointService = new PointServiceImpl();
		this.usePointService = new UsePointServiceImpl();
	}
	@Override
	public int sendKockFarmAlarm(Long cateNum, String cateName, Long buyerUserNum, Long kockNum) throws Exception {
		List<SellerVO> sellerList = alarmDAO.selectSellerListByCate(cateNum);
		
		int count = 0;
		
		for (SellerVO seller:sellerList) {
			String fcmToken = seller.getFcmToken();
			Long sellerUserNum = Long.parseLong(seller.getUserNum());

			if (fcmToken != null) {
				String title="콕팜 알림";
				String body="["+ cateName + "] 에 새로운 콕팜 요청이 등록되었습니다!";
				try {
					System.out.println("=== sendKockFarmAlarm 호출됨 ===");
					FcmUtil.sendMessageTo(fcmToken, title, body);
					//알림 저장
					Alarm alarm = new Alarm(sellerUserNum, buyerUserNum, "새로운 콕팜 요청이 등록되었습니다!", cateName, title, kockNum);
					alarmDAO.insertAlarm(alarm);
					//포인트 차감
					pointService.updatePoint(-500, sellerUserNum);
					//포인트 사용내역 저장
					Integer currPoint = pointService.getPoint(sellerUserNum).getPoint();
					UsePoint usePoint = new UsePoint(sellerUserNum, -500, "콕팜알림", currPoint);
					usePointService.useByKockFarmAlarm(usePoint);
				
					count++;
				} catch (Exception e) {
					// FCM 실패 시 로그만 남기고 다음 유저로 진행
		            System.err.println("알림 전송 실패: " + sellerUserNum + " / 이유: " + e.getMessage());
		        
				}
				
			}
			
		}
		
		return count;
	}
	@Override
	public List<Alarm> recentAlarmList(Long reNum) throws Exception {
		return alarmDAO.selectRecentAlarmList(reNum);
	}
	@Override
	public boolean markAlarmAsRead(Long alarmNum) throws Exception {
		return alarmDAO.updateIsChecked(alarmNum) > 0 ;
	}
	@Override
	public List<Alarm> selectAlarmBySearchDto(SearchDtoSoy dto) throws Exception {
		return alarmDAO.selectAlarmBySearchDto(dto);
	}
	@Override
	public int countAlarmBySearchDto(SearchDtoSoy dto) throws Exception {
		return alarmDAO.countAlarmBySearchDto(dto);
	}
	@Override
	public int getUnreadAlarmCount(Long userNum) throws Exception {
        return alarmDAO.selectUnreadAlarmCount(userNum);
	}

}
