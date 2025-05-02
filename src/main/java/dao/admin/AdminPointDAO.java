package dao.admin;

import java.util.List;

import dto.seller.Point;

public interface AdminPointDAO {
	List<Point> totalSalesPointList() throws Exception;
}
