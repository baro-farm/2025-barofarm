package service.seller;

import dto.seller.Point;

public interface PointService {
	Point getPoint(Long userNum) throws Exception;
	boolean updatePoint(Integer Point, Long userNum) throws Exception;
}
