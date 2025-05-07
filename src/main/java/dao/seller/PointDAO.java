package dao.seller;

import dto.seller.Point;

public interface PointDAO {
	Point getPoint(Long userNum) throws Exception;
	void updatePoint(Integer Point, Long userNum) throws Exception;
	void insertPoint(Point point) throws Exception;
}
