package service.admin;

import java.util.List;

import dto.admin.Banner;

public interface BannerService {
	void insertBannerByAdmin(Banner banner) throws Exception;
	List<Banner> adminBannerList() throws Exception;
	boolean updateBannerPosted(Long bannerNum) throws Exception;
	void insertBannerBySellerAds(Banner banner) throws Exception;
	int countSellerBanner() throws Exception;
}
