package service.admin;

import java.util.List;
import java.util.Map;

import dto.admin.Store;

public interface StoreService {
	List<Store> allStore(Map<String, Object> param) throws Exception;
	int getStoreCount(Map<String, Object> param) throws Exception;
}
