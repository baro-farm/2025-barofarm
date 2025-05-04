package service.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.buyer.KockCommentDao;
import dao.buyer.KockCommentDaoImpl;
import dto.buyer.BabyComment;
import dto.buyer.KockComment;
import util.SearchDtoSoy;
import vo.KockCommentVO;

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
	public List<KockComment> kockCommentListWithBaby(Long kockNum) throws Exception {
		List<KockComment> commentList = kockCommentDao.kockCommentList(kockNum);
		for (KockComment kc : commentList) {
			List<BabyComment> babyList = kockCommentDao.babyCommentList(kc.getKcNum());
			kc.setBabyComments(babyList);
		}
		return commentList;
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
	
	@Override
	public List<KockCommentVO> selectUserMyCommentList(Long userNum, int pageSize, int offset) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("userNum", userNum);
	    param.put("pageSize", pageSize);
	    param.put("offset", offset);
	    return kockCommentDao.selectAllKockCommentList(param);
	    
	}

	@Override
	public Integer selectCountAllComment(Long userNum) throws Exception {
		return kockCommentDao.countAllComments(userNum);
	}
	@Override
	public List<KockCommentVO> getSellerComments(SearchDtoSoy dto) {
        return kockCommentDao.selectAllSellerComments(dto);
	}
	@Override
	public int countSellerComments(SearchDtoSoy dto) {
        return kockCommentDao.countAllSellerComments(dto);
	}

}
