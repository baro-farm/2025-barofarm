package service.admin;

import java.util.List;

import dao.admin.StoreDAO;
import dao.admin.StoreDAOImpl;
import dto.admin.Store;

public class StoreServiceImpl implements StoreService {
	private StoreDAO storeDao = new StoreDAOImpl();
	
	public StoreServiceImpl() {
		storeDao = new StoreDAOImpl();
	}
	
	@Override
	public List<Store> allStore() throws Exception {
		return storeDao.selectStoreList();
	}

}
