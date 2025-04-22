package service.seller;

import dto.seller.Point;

public interface PointService {
	Point getPoint(Long userNum) throws Exception;
	void updatePoint(Integer point, Long userNum) throws Exception;
}
