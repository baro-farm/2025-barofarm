package service.seller;

import dao.seller.PackageDAO;
import dao.seller.PackageDAOImpl;
import dto.seller.PackageProduct;

public class PackageServiceImpl implements PackageService {
	private PackageDAO packageDao;
	
	public PackageServiceImpl() {
		packageDao = new PackageDAOImpl();
	}
	
	@Override
	public void addPackageProduct(PackageProduct packageProduct) throws Exception {
		packageDao.insertPackageProduct(packageProduct);
	}

	@Override
	public PackageProduct selectPackageProduct(Long packageNum) throws Exception {
		return packageDao.selectPackageProduct(packageNum);
	}
	
	@Override
	public void updatePackageProduct(PackageProduct packageProduct) throws Exception {
		packageDao.updatePackageProduct(packageProduct);		
	}
}
