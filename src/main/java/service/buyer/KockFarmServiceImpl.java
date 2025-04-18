package service.buyer;

import java.util.List;

import dao.buyer.KockFarmDao;
import dao.buyer.KockFarmDaoImpl;
import dto.buyer.KockFarm;
import dto.buyer.Matching;

public class KockFarmServiceImpl implements KockFarmService {
	private KockFarmDao kockFarmDao;
	
	public KockFarmServiceImpl() {
		kockFarmDao = new KockFarmDaoImpl();
	}
	
	@Override
	public void insertKockFarm(KockFarm kockFarm) throws Exception {
		kockFarmDao.insertKockFarm(kockFarm);
	}

	@Override
	public KockFarm selectKockFarm(Long kockNum) throws Exception {
		return kockFarmDao.selectKockFarm(kockNum);
	}

	@Override
	public KockFarm updateKockFarm(KockFarm kockFarm) throws Exception {
		KockFarm skock = kockFarmDao.selectKockFarm(kockFarm.getKockNum());
		if (kockFarm.getImgUrl()==null) {
			kockFarm.setImgUrl(skock.getImgUrl());
		}
		kockFarmDao.updateKockFarm(kockFarm);
		return kockFarm;
	}

	@Override
	public void deleteKockFarm(Long kockNum) throws Exception {
		kockFarmDao.deleteKockFarm(kockNum);
	}

	@Override
	public List<KockFarm> getKockFarmList() throws Exception {
		return kockFarmDao.getKockFarmList();
	}

	//매칭
	@Override
	public void insertMatching(Matching matching,Long kockNum) throws Exception {
		kockFarmDao.updateKockMatched(kockNum);
		kockFarmDao.insertMatching(matching);
	}
}
