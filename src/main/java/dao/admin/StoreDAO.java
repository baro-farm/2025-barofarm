package dao.admin;

import java.util.List;
import java.util.Map;

import dto.admin.Store;

public interface StoreDAO {
	List<Store> selectStoreList(Map<String, Object> param) throws Exception;
	int countStoreList(Map<String, Object> param) throws Exception;
}
