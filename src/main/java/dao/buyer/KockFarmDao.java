package dao.buyer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import dto.buyer.KockFarm;
import dto.buyer.Matching;
import vo.KockCommentVO;
import vo.KockFarmVO;

public interface KockFarmDao {
	void insertKockFarm(KockFarm kock) throws Exception;
	KockFarm selectKockFarm(Long kockNum) throws Exception;
	void updateKockFarm(KockFarm kockFarm);
	void deleteKockFarm(Long kockNum) throws Exception;
	List<KockFarm> getKockFarmList() throws Exception;
	//매칭
	void insertMatching(Matching matching) throws Exception;
	void updateKockMatched(Long kockNum) throws Exception;
	//마이페이지
	List<KockFarmVO> selectKockFarmPostList(Long userNum ,LocalDate startDate,Boolean isMatched) throws Exception;
}
