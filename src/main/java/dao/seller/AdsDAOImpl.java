package dao.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Advertisement;
import util.MybatisSqlSessionFactory;
import util.SearchDtoSoy;

public class AdsDAOImpl implements AdsDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	@Override
	public boolean insertAds(Advertisement ads) throws Exception {
		int result = sqlSession.insert("mapper.advertisement.insertAds",ads);
		if (result==1) {
			sqlSession.commit();
			return result==1;
		} else {
			sqlSession.rollback();
			return result!=1;
		}
	}
	@Override
	public List<Advertisement> selectAdsByUserNum(Long userNum) throws Exception {
		return sqlSession.selectList("mapper.advertisement.selectAdsByUserNum",userNum);
	}
	@Override
	public boolean updateAdsStatus(Long adsNum, String status) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("adsNum", adsNum);
		param.put("status", status);
		int result =  sqlSession.update("mapper.advertisement.updateAdsStatus",param);
		sqlSession.commit();
		return result ==1;
	}
	@Override
	public Advertisement selectAdsByAdsNum(Long adsNum) throws Exception {
		return sqlSession.selectOne("mapper.advertisement.selectAdsByAdsNum", adsNum);
	}
	@Override
	public int countAdsWithPosting() throws Exception {
		return sqlSession.selectOne("mapper.advertisement.countAdsWithPosting");
	}
	@Override
	public void updateAds(Advertisement ads) throws Exception {
		sqlSession.update("mapper.advertisement.updateAds",ads);
		sqlSession.commit();		
	}
	
	//관리자
	@Override
	public List<Advertisement> selectAdsWithPosting() throws Exception {
		return sqlSession.selectList("mapper.advertisement.selectAdsWithPosting");
	}
	@Override
	public List<Advertisement> selectAdsBySearchDto(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectList("mapper.advertisement.selectAdsBySearchDto",dto);
	}
	@Override
	public int countAdsBySearchDtoSoy(SearchDtoSoy dto) throws Exception {
		return sqlSession.selectOne("mapper.advertisement.countAdsBySearchDto", dto);
	}
	@Override
	public void updateExpiredAdsStatus() throws Exception {
		sqlSession.update("mapper.advertisement.updateExpiredAdsStatus");
		sqlSession.commit();
	}
}
