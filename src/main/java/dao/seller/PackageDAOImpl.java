package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import util.MybatisSqlSessionFactory;
import vo.PackageVO;

public class PackageDAOImpl implements PackageDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public void insertPackageProduct(PackageProduct packageProduct) throws Exception {
		sqlSession.insert("mapper.packageProduct.insertPackageProduct", packageProduct);
		sqlSession.commit();
	}

	@Override
	public PackageProduct selectPackageProduct(Long packageNum) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.selectPackageProduct", packageNum);	
	}
	
	@Override
	public void updatePackageProduct(PackageProduct packageProduct) throws Exception {
		sqlSession.update("mapper.packageProduct.updatePackageProduct", packageProduct);
		sqlSession.commit();
	}

	@Override
	public List<PackageVO> selectPackageList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packageProduct.selectPackageList",param);
	}
	
	@Override
	public Integer countPackageList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.countPackageList",param);
	}

	@Override
	public void updatePackageStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.packageProduct.updatePackageStock", param);
		sqlSession.commit();		
	}

	
}
