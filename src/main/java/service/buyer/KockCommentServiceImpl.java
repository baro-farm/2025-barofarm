package service.buyer;

import java.util.List;

import dao.buyer.KockCommentDao;
import dao.buyer.KockCommentDaoImpl;
import dto.buyer.BabyComment;
import dto.buyer.KockComment;

public class KockCommentServiceImpl implements KockCommentService {
	private KockCommentDao kockCommentDao;
	
	public KockCommentServiceImpl() {
		kockCommentDao = new KockCommentDaoImpl();
	}
	@Override
	public boolean insertKockComment(KockComment kComment) throws Exception {
		return kockCommentDao.insertKockComment(kComment);
	}
	@Override
	public List<KockComment> kockCommentList(Long kockNum) throws Exception {
		return kockCommentDao.kockCommentList(kockNum);
	}
	@Override
	public KockComment selectKCommentByKcNum(Long kcNum) throws Exception {
		return kockCommentDao.selectKCommentByKcNum(kcNum);
	}
	@Override
	public boolean insertBabyComment(BabyComment bComment) throws Exception {
		return kockCommentDao.insertBabyComment(bComment);
	}
	@Override
	public BabyComment selectBCommentByReNum(Long reNum) throws Exception {
		return kockCommentDao.selectBCommentByReNum(reNum);
	}

}
