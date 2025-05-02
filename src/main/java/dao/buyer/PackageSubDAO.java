package dao.buyer;

import java.util.List;
import java.util.Map;

import vo.PackSubVO;

public interface PackageSubDAO {
	List<PackSubVO> selectUserPackSubList(Map<String, Object> param) throws Exception;
	Integer selectUserPackSubCount(Map<String, Object> param) throws Exception;
}
