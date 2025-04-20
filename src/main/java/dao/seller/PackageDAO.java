package dao.seller;

import dto.seller.PackageProduct;

public interface PackageDAO {
	void insertPackageProduct(PackageProduct packageProduct) throws Exception;
}
