package service.seller;

import dto.seller.PackageProduct;

public interface PackageService {
	void addPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
}
