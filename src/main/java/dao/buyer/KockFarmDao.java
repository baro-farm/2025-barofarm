package dao.buyer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.SearchDtoSoy;
import vo.KockCommentVO;
import vo.KockFarmVO;

public interface KockFarmDao {
	Long insertKockFarm(KockFarm kock) throws Exception;
	KockFarm selectKockFarm(Long kockNum) throws Exception;
	void updateKockFarm(KockFarm kockFarm);
	void deleteKockFarm(Long kockNum) throws Exception;
	List<KockFarm> selectKFBySearchDto(SearchDtoSoy dto) throws Exception;
	int countKFBySearchDto(SearchDtoSoy dto) throws Exception;
	//매칭
	void insertMatching(Matching matching) throws Exception;
	void updateKockMatched(Long kockNum) throws Exception;
	Matching selectMatchingByKCNum(Long kockNum) throws Exception;
	//마이페이지
	List<KockFarmVO> selectKockFarmPostList(Map<String, Object> param) throws Exception;
	Integer selectCountUserKockPost(Map<String,Object> param) throws Exception;
}
