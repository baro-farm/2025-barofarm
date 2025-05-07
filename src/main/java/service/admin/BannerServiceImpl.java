package service.admin;

import java.util.List;

import dao.admin.BannerDAO;
import dao.admin.BannerDAOImpl;
import dto.admin.Banner;

public class BannerServiceImpl implements BannerService {
	private BannerDAO bannerDAO;
	
	public BannerServiceImpl() {
		bannerDAO = new BannerDAOImpl();
	}
	@Override
	public void insertBannerByAdmin(Banner banner) throws Exception {
		bannerDAO.insertBannerByAdmin(banner);
	}
	@Override
	public List<Banner> adminBannerList() throws Exception {
		return bannerDAO.adminBannerList();
	}
	@Override
	public boolean updateBannerPosted(Long bannerNum) throws Exception {
		return bannerDAO.updateBannerPosted(bannerNum);
	}
	@Override
	public void insertBannerBySellerAds(Banner banner) throws Exception {
		bannerDAO.insertBannerBySellerAds(banner);
	}
	@Override
	public int countSellerBanner() throws Exception {
		return bannerDAO.countSellerBanner();
	}
	@Override
	public void updateExpiredBannerIsPosted() throws Exception {
		bannerDAO.updateExpiredBannerIsPosted();
	}
	@Override
	public List<Banner> selectBannerByIsPosted() throws Exception {
		return bannerDAO.selectBannerByIsPosted();
	}
}
