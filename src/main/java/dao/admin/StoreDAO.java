package dao.admin;

import java.util.List;

import dto.admin.Store;

public interface StoreDAO {
	List<Store> selectStoreList() throws Exception;
}
