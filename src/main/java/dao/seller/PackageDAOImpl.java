package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import util.MybatisSqlSessionFactory;
import vo.PackSubVO;
import vo.PackageVO;

public class PackageDAOImpl implements PackageDAO {
	private final SqlSession sqlSession;

	public PackageDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertPackageProduct(PackageProduct packageProduct) throws Exception {
		sqlSession.insert("mapper.packageProduct.insertPackageProduct", packageProduct);
	}

	@Override
	public PackageProduct selectPackageProduct(Long packageNum) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.selectPackageProduct", packageNum);
	}

	@Override
	public void updatePackageProduct(PackageProduct packageProduct) throws Exception {
		sqlSession.update("mapper.packageProduct.updatePackageProduct", packageProduct);
	}

	@Override
	public List<PackageVO> selectPackageList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packageProduct.selectPackageList", param);
	}

	@Override
	public Integer countPackageList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packageProduct.countPackageList", param);
	}

	@Override
	public void updatePackageStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.packageProduct.updatePackageStock", param);
	}

	@Override
	public void adjustStock(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.packageProduct.adjustStock", param);
	}

	@Override
	public void adjustSalesVolume(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.packageProduct.adjustSalesVolume", param);
	}

	@Override
	public List<PackSubVO> selectPackageSubList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packageSub.packageSubList", param);
	}

	@Override
	public Integer countPackageSubList(Long sellernum) throws Exception {
		return sqlSession.selectOne("mapper.packageSub.countPackageSubList", sellernum);
	}
}
