package dao.buyer;

import java.util.List;

import dto.buyer.KockFarm;
import dto.buyer.Matching;

public interface KockFarmDao {
	void insertKockFarm(KockFarm kock) throws Exception;
	KockFarm selectKockFarm(Long kockNum) throws Exception;
	void updateKockFarm(KockFarm kockFarm);
	void deleteKockFarm(Long kockNum) throws Exception;
	List<KockFarm> getKockFarmList() throws Exception;
	//매칭
	void insertMatching(Matching matching) throws Exception;
	void updateKockMatched(Long kockNum) throws Exception;
}
