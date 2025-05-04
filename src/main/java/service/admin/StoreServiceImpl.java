package service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.admin.StoreDAO;
import dao.admin.StoreDAOImpl;
import dto.admin.Store;

public class StoreServiceImpl implements StoreService {
	private StoreDAO storeDao = new StoreDAOImpl();
	
	public StoreServiceImpl() {
		storeDao = new StoreDAOImpl();
	}
	
	@Override
	public List<Store> allStore(Map<String, Object> param) throws Exception {
		return storeDao.selectStoreList(param);
	}

	@Override
	public int getStoreCount(Map<String, Object> param) throws Exception {
		return storeDao.countStoreList(param);
	}
}
