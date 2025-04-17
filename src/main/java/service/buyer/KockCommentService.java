package service.buyer;

import java.util.List;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;

public interface KockCommentService {
	boolean insertKockComment(KockComment kComment) throws Exception;
	List<KockComment> kockCommentListWithBaby(Long kockNum) throws Exception;
	KockComment selectKCommentByKcNum(Long kcNum) throws Exception;
	
	//아기 댓글
	boolean insertBabyComment(BabyComment bComment) throws Exception;
	BabyComment selectBCommentByReNum(Long reNum) throws Exception;
}
