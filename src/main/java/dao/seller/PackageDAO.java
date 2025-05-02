package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import vo.PackageVO;

public interface PackageDAO {
	void insertPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
	List<PackageVO> selectPackageList(Map<String, Object> param) throws Exception;
	Integer countPackageList(Map<String, Object> param) throws Exception;
	void updatePackageStock(Map<String, Object> param) throws Exception;
	void adjustStock(SqlSession sqlSession, Map<String, Object> param) throws Exception;
	void adjustSalesVolume(SqlSession sqlSession, Map<String, Object> param) throws Exception;
}
