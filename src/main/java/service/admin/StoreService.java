package service.admin;

import java.util.List;

import dto.admin.Store;

public interface StoreService {
	List<Store> allStore() throws Exception;
}
