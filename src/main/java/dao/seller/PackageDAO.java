package dao.seller;

import dto.seller.PackageProduct;

public interface PackageDAO {
	void insertPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
}
