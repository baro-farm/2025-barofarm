package service.buyer;

import java.util.List;

import dto.buyer.KockFarm;
import dto.buyer.Matching;

public interface KockFarmService {
	void insertKockFarm(KockFarm kockFarm) throws Exception;
	KockFarm selectKockFarm(Long kockNum) throws Exception;
	KockFarm updateKockFarm(KockFarm kockFarm) throws Exception;
	void deleteKockFarm(Long kockNum) throws Exception;
	List<KockFarm> getKockFarmList() throws Exception;
	//매칭
	void insertMatching(Matching matching) throws Exception;
}
