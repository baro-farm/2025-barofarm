package service.buyer;

import java.time.LocalDate;
import java.util.List;

import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.SearchDtoSoy;
import vo.KockCommentVO;
import vo.KockFarmVO;

public interface KockFarmService {
	Long insertKockFarm(KockFarm kockFarm) throws Exception;
	KockFarm selectKockFarm(Long kockNum) throws Exception;
	KockFarm updateKockFarm(KockFarm kockFarm) throws Exception;
	void deleteKockFarm(Long kockNum) throws Exception;
	List<KockFarm> selectKFBySearchDto(SearchDtoSoy dto) throws Exception;
	int countKFBySearchDto(SearchDtoSoy dto) throws Exception;
	//매칭
	void insertMatching(Matching matching,Long kockNum) throws Exception;
	//마이페이지 리스트
	List<KockFarmVO> selectMyPostList(Long userNum,LocalDate startDate, Boolean isMatched) throws Exception;
}
