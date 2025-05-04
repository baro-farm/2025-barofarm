package service.buyer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.buyer.KockFarmDao;
import dao.buyer.KockFarmDaoImpl;
import dto.buyer.KockFarm;
import dto.buyer.Matching;
import util.SearchDtoSoy;
import vo.KockCommentVO;
import vo.KockFarmVO;

public class KockFarmServiceImpl implements KockFarmService {
	private KockFarmDao kockFarmDao;
	
	public KockFarmServiceImpl() {
		kockFarmDao = new KockFarmDaoImpl();
	}
	
	@Override
	public Long insertKockFarm(KockFarm kockFarm) throws Exception {
		return kockFarmDao.insertKockFarm(kockFarm);
	}

	@Override
	public KockFarm selectKockFarm(Long kockNum) throws Exception {
		return kockFarmDao.selectKockFarm(kockNum);
	}

	@Override
	public KockFarm updateKockFarm(KockFarm kockFarm) throws Exception {
		KockFarm origin = kockFarmDao.selectKockFarm(kockFarm.getKockNum());
		String newImg = kockFarm.getImgUrl();
		if (newImg == null || newImg.trim().isEmpty()) {
			kockFarm.setImgUrl(origin.getImgUrl());
		}
		kockFarmDao.updateKockFarm(kockFarm);
		return kockFarm;
	}

	@Override
	public void deleteKockFarm(Long kockNum) throws Exception {
		kockFarmDao.deleteKockFarm(kockNum);
	}

	@Override
	public List<KockFarm> selectKFBySearchDto(SearchDtoSoy dto) throws Exception {
		return kockFarmDao.selectKFBySearchDto(dto);
	}
	
	@Override
	public int countKFBySearchDto(SearchDtoSoy dto) throws Exception {
		return kockFarmDao.countKFBySearchDto(dto);
	}
	
	//매칭
	@Override
	public void insertMatching(Matching matching,Long kockNum) throws Exception {
		kockFarmDao.updateKockMatched(kockNum);
		kockFarmDao.insertMatching(matching);
	}

	@Override
	public List<KockFarmVO> selectMyPostList(Long userNum, LocalDate startDate, Boolean isMatched, int pageSize, int offset) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("userNum", userNum);
	    param.put("startDate", startDate);
	    param.put("isMatched", isMatched);
	    param.put("pageSize", pageSize);
	    param.put("offset", offset);
		return kockFarmDao.selectKockFarmPostList(param);
	}

	@Override
	public Integer selectCountKockPost(Long userNum, LocalDate startDate, Boolean isMatched) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("userNum", userNum);
	    param.put("startDate", startDate);
	    param.put("isMatched", isMatched);
	    return kockFarmDao.selectCountUserKockPost(param);
	}

	@Override
	public Matching selectMatchingByKCNum(Long kockNum) throws Exception {
		return kockFarmDao.selectMatchingByKCNum(kockNum);
	}

}
