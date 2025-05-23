package dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import vo.PackSubVO;
import vo.PackageVO;

public interface PackageDAO {
	void insertPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
	List<PackageVO> selectPackageList(Map<String, Object> param) throws Exception;
	Integer countPackageList(Map<String, Object> param) throws Exception;
	void updatePackageStock(Map<String, Object> param) throws Exception;
	void adjustStock(Map<String, Object> param) throws Exception;
	void adjustSalesVolume(Map<String, Object> param) throws Exception;
	List<PackSubVO> selectPackageSubList(Map<String, Object> param)throws Exception;
	Integer countPackageSubList(Long sellernum)throws Exception;
}
