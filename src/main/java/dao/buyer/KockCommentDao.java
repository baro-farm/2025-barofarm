package dao.buyer;

import java.util.List;
import java.util.Map;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.SearchDtoSoy;
import vo.KockCommentVO;

public interface KockCommentDao {
	boolean insertKockComment(KockComment kComment) throws Exception;
	List<KockComment> kockCommentList(Long kockNum) throws Exception;
	KockComment selectKCommentByKcNum(Long kcNum) throws Exception;
	
	//아기 댓글
	boolean insertBabyComment(BabyComment bComment) throws Exception;
	List<BabyComment> babyCommentList(Long kcNum) throws Exception;
	BabyComment selectBCommentByReNum(Long reNum) throws Exception;
	
	//마이페이지 댓글 리스트
	List<KockCommentVO> selectAllKockCommentList(Map<String,Object>param) throws Exception;
	Integer countAllComments(Long userNum) throws Exception;

	//마이스토어 댓글
    List<KockCommentVO> selectAllSellerComments(SearchDtoSoy dto);
    int countAllSellerComments(SearchDtoSoy dto);
}
