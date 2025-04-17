package dao.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.Advertisement;
import util.MybatisSqlSessionFactory;

public class AdsDAOImpl implements AdsDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	@Override
	public boolean insertAds(Advertisement ads) throws Exception {
		int result = sqlSession.insert("mapper.advertisement.insertAds",ads);
		sqlSession.commit();
		return result==1;
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
		int result = sqlSession.update("mapper.advertisement.updateAdsStatus",param);
		sqlSession.commit();
		return result==1;
	}

}
