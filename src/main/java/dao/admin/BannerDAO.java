package dao.admin;

import java.util.List;

import dto.admin.Banner;

public interface BannerDAO {
	void insertBannerByAdmin(Banner banner) throws Exception;
	List<Banner> adminBannerList() throws Exception;
	boolean updateBannerPosted(Long bannerNum) throws Exception;
	void insertBannerBySellerAds(Banner banner) throws Exception;
	int countSellerBanner() throws Exception;
	void updateExpiredBannerIsPosted() throws Exception;
	List<Banner> selectBannerByIsPosted() throws Exception;
}
