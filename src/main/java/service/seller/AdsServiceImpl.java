package service.seller;

import java.util.List;

import dao.seller.AdsDAO;
import dao.seller.AdsDAOImpl;
import dao.seller.PointDAO;
import dao.seller.PointDAOImpl;
import dto.seller.Advertisement;
import dto.seller.Point;

public class AdsServiceImpl implements AdsService {
	private AdsDAO adsDAO;
	private PointDAO pointDAO;
	
	public AdsServiceImpl() {
		adsDAO = new AdsDAOImpl();
		pointDAO = new PointDAOImpl();
	}
	@Override
	public void insertAds(Advertisement ads) throws Exception {
		//1.광고 등록
		boolean addAds = adsDAO.insertAds(ads);
		//2.point 테이블에서 포인트 차감
		boolean usePoint = pointDAO.updatePoint(-20000, ads.getUserNum());
		//3.usePoint에서 포인트 사용 내역 저장
		Integer currPoint = pointDAO.getPoint(ads.getUserNum()).getPoint();
		boolean insertUse;
		
	}
	@Override
	public List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception {
		return adsDAO.selectAdsByUserNum(userNum);
	}
	@Override
	public boolean cancelAdsAndRefund(Long adsNum, String status) throws Exception {
		//1.광고 취소로 update
		boolean canceled = adsDAO.updateAdsStatus(adsNum, status);
		//2.point에서 포인트 반환
		//3.usePoint에서 포인트 반환 내역 저장
		return false;
	}

}
