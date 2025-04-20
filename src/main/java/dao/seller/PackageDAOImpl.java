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

}
