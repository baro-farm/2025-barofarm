package service.buyer;

import java.util.List;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.SearchDtoSoy;
import vo.KockCommentVO;

public interface KockCommentService {
	boolean insertKockComment(KockComment kComment) throws Exception;
	List<KockComment> kockCommentListWithBaby(Long kockNum) throws Exception;
	KockComment selectKCommentByKcNum(Long kcNum) throws Exception;
	
	//아기 댓글
	boolean insertBabyComment(BabyComment bComment) throws Exception;
	BabyComment selectBCommentByReNum(Long reNum) throws Exception;
	
	//댓글 리스트
	List<KockCommentVO> selectUserMyCommentList(Long userNum, int pageSize, int offset) throws Exception;
	Integer selectCountAllComment(Long userNum) throws Exception;
	
	//마이스토어 댓글
    List<KockCommentVO> getSellerComments(SearchDtoSoy dto);
    int countSellerComments(SearchDtoSoy dto);
}
