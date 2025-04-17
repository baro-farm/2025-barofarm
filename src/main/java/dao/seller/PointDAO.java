package dao.seller;

import dto.seller.Point;

public interface PointDAO {
	Point getPoint(Long userNum) throws Exception;
	boolean updatePoint(Integer Point, Long userNum) throws Exception;
}
