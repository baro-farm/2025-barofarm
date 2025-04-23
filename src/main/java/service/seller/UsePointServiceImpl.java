package service.seller;

import java.util.List;

import dao.seller.PointDAOImpl;
import dao.seller.UsePointDAO;
import dao.seller.UsePointDAOImpl;
import dto.seller.UsePoint;
import util.SearchDtoSoy;

public class UsePointServiceImpl implements UsePointService {
	private UsePointDAO usePointDAO;
	
	public UsePointServiceImpl() {
		usePointDAO = new UsePointDAOImpl();
	}
	@Override
	public void insertUsePoint(UsePoint usePoint) throws Exception {
		usePointDAO.insertUsePoint(usePoint);
	}
	@Override
	public List<UsePoint> selectUsePointListBySearchDto(SearchDtoSoy dto) throws Exception {
		return usePointDAO.selectUsePointListBySearchDto(dto);
	}
	@Override
	public int countUsePointBySearchDto(SearchDtoSoy dto) throws Exception {
		return usePointDAO.countUsePointBySearchDto(dto);
	}

}
