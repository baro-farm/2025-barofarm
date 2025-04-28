package service.seller;

import java.util.List;

import dao.seller.AdsDAO;
import dao.seller.AdsDAOImpl;
import dto.admin.Banner;
import dto.seller.Advertisement;
import dto.seller.UsePoint;
import service.admin.BannerService;
import service.admin.BannerServiceImpl;
import util.SearchDtoSoy;

public class AdsServiceImpl implements AdsService {
	private AdsDAO adsDAO;
	private PointService pointService;
	private UsePointService usePointService;
	private BannerService bannerService;
	
	public AdsServiceImpl() {
		adsDAO = new AdsDAOImpl();
	    pointService = new PointServiceImpl();
	    usePointService = new UsePointServiceImpl();
	    bannerService = new BannerServiceImpl();
	}
	@Override
	public void insertAds(Advertisement ads) throws Exception {
		//1.광고 등록
		adsDAO.insertAds(ads);
		//2.point 테이블에서 포인트 차감
		pointService.updatePoint(-20000, ads.getUserNum());
		//3.usePoint에서 포인트 사용 내역 저장
		Integer currPoint = pointService.getPoint(ads.getUserNum()).getPoint();
		UsePoint usePoint = new UsePoint(ads.getUserNum(), -20000, "광고등록", currPoint);
		usePointService.handlePointByAds(usePoint);
	}
	@Override
	public List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception {
		return adsDAO.selectAdsByUserNum(userNum);
	}
	@Override
	public void cancelAdsAndRefund(Long adsNum, String status) throws Exception {
		//1.광고 취소로 update
		adsDAO.updateAdsStatus(adsNum, status);
		//2.point에서 포인트 반환
		Advertisement ads = adsDAO.selectAdsByAdsNum(adsNum);
		Long userNum = ads.getUserNum();
		pointService.updatePoint(+20000, userNum);
		//3.usePoint에서 포인트 반환 내역 저장
		Integer currPoint = pointService.getPoint(userNum).getPoint();
		UsePoint usePoint = new UsePoint(userNum, +20000, "광고취소", currPoint);
		usePointService.handlePointByAds(usePoint);
	}
	
	@Override
	public Advertisement selectAdsByAdsNum(Long adsNum) throws Exception {
		return adsDAO.selectAdsByAdsNum(adsNum);
	}
	@Override
	public void updateAds(Advertisement ads) throws Exception {
		adsDAO.updateAds(ads);
	}
	
	//관리자
	@Override
	public List<Advertisement> selectAdsWithPosting() throws Exception {
		return adsDAO.selectAdsWithPosting();
	}
	@Override
	public int countAdsWithPosting() throws Exception {
		return adsDAO.countAdsWithPosting();
	}
	@Override
	public List<Advertisement> selectAdsBySearchDto(SearchDtoSoy dto) throws Exception {
		return adsDAO.selectAdsBySearchDto(dto);
	}
	@Override
	public int countAdsBySearchDtoSoy(SearchDtoSoy dto) throws Exception {
		return adsDAO.countAdsBySearchDtoSoy(dto);
	}
	
	@Override
	public boolean updateAdsStatusByAdmin(Long adsNum, String status) throws Exception {
		boolean updated = adsDAO.updateAdsStatus(adsNum,status);
		if (!updated) return false;
		Advertisement ads = adsDAO.selectAdsByAdsNum(adsNum);
		Long userNum = ads.getUserNum();
		if ("이미지부적격".equals(status) || "상품링크오류".equals(status)) {
			// 포인트 반환
			pointService.updatePoint(+20000, userNum);
			//포인트 사용 내역
			Integer currPoint = pointService.getPoint(userNum).getPoint();
			UsePoint usePoint = new UsePoint(userNum, +20000, "광고반려", currPoint);
			usePointService.handlePointByAds(usePoint);
		} else if ("승인".equals(status)) {
			//배너 삽입
			Banner banner = new Banner(null, adsNum, ads.getTitle(), ads.getImgUrl(), ads.getProductUrl(), null, null, true, ads.getUserNum());
			bannerService.insertBannerBySellerAds(banner);
		}
		return true;
	}
}
