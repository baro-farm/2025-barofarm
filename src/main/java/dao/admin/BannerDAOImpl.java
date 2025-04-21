package dao.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.admin.Banner;
import util.MybatisSqlSessionFactory;

public class BannerDAOImpl implements BannerDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertBannerByAdmin(Banner banner) throws Exception {
		sqlSession.insert("mapper.banner.insertBannerByAdmin",banner);
		sqlSession.commit();
	}

	@Override
	public List<Banner> adminBannerList() throws Exception {
		return sqlSession.selectList("mapper.banner.adminBannerList");
	}

	@Override
	public boolean updateBannerPosted(Long bannerNum) throws Exception {
		int result =  sqlSession.update("mapper.banner.updateBannerPosted",bannerNum);
		sqlSession.commit();
		return result == 1;
	}

	@Override
	public void insertBannerBySellerAds(Banner banner) throws Exception {
		sqlSession.insert("mapper.banner.insertBannerBySellerAds",banner);
		sqlSession.commit();
	}

}
