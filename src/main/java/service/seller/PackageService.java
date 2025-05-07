package service.seller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.seller.PackageProduct;
import util.PageInfo;
import vo.PackSubVO;
import vo.PackageVO;


public interface PackageService {
	void addPackageProduct(PackageProduct packageProduct) throws Exception;
	PackageProduct selectPackageProduct(Long packageNum) throws Exception;
	void updatePackageProduct(PackageProduct packageProduct) throws Exception;
	List<PackageVO> selectSellerPackageList(Long sellerNum,int offset, int pageSize,String sort, String sellStat) throws Exception;
	Integer selectCountSellerPackageList(Long sellerNum,String sellStat) throws Exception;
	void updatePackageStock(Long packageNum, Integer stock) throws Exception;
	void adjustStock(SqlSession sqlSession, Long packageNum, int quantityDiff) throws Exception;
	void adjustSalesVolume(SqlSession sqlSession, Long packageNum, int quantityDiff) throws Exception;
	List<PackSubVO> selectPackageSubList(PageInfo pageInfo, Long sellerNum, String startDate, String endDate, 
			String searchType, String searchKeyword)throws Exception;
	Integer countPackageSubList(Long sellernum)throws Exception;
}
