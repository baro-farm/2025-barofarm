package dao.buyer;

import java.util.List;

import dto.buyer.BabyComment;
import dto.buyer.KockComment;

public interface KockCommentDao {
	boolean insertKockComment(KockComment kComment) throws Exception;
	List<KockComment> kockCommentList(Long kockNum) throws Exception;
	KockComment selectKCommentByKcNum(Long kcNum) throws Exception;
	
	//아기 댓글
	boolean insertBabyComment(BabyComment bComment) throws Exception;
	List<BabyComment> babyCommentList(Long kcNum) throws Exception;
	BabyComment selectBCommentByReNum(Long reNum) throws Exception;
}
