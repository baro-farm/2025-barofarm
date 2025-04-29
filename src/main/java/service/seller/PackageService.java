package service.seller;

import java.util.List;

import dto.seller.PackageProduct;
import vo.PackageVO;


public interface PackageService {
	void addPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
	List<PackageVO> selectSellerPackageList(Long sellerNum,int offset, int pageSize,String sort, String sellStat) throws Exception;
	Integer selectCountSellerPackageList(Long sellerNum,String sellStat) throws Exception;
}
