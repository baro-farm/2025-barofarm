package service.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.seller.PackageDAO;
import dao.seller.PackageDAOImpl;
import dto.seller.PackageProduct;
import vo.PackageVO;
import vo.ProductVO;

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
	
	@Override
	public List<PackageVO> selectSellerPackageList(Long sellerNum, int offset, int pageSize, String sort,
			String sellStat) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("sellerNum", sellerNum);
		param.put("offset", offset);
		param.put("pageSize", pageSize);
		param.put("sort",sort);
		param.put("sellStat",sellStat);
		return packageDao.selectPackageList(param);
	}

	@Override
	public Integer selectCountSellerPackageList(Long sellerNum, String sellStat) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("sellerNum", sellerNum);
		param.put("sellStat", sellStat);		
		return packageDao.countPackageList(param);
	}

	@Override
	public void updatePackageStock(Long packageNum, Integer stock) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("packageNum", packageNum);
		param.put("stock", stock);
		packageDao.updatePackageStock(param);
		
	}

	
}
