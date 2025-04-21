package dao.seller;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import util.MybatisSqlSessionFactory;

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
}
