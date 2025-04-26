package service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.admin.NoticeDAO;
import dao.admin.NoticeDAOImpl;
import dto.admin.Notice;
import util.PageInfo;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDAO noticeDao;
	
	public NoticeServiceImpl() {
		noticeDao = new NoticeDAOImpl();
	}
	
	@Override
	public List<Notice> allNotice() throws Exception {
		return noticeDao.selectNoticeList();
	}
 
	@Override
	public void writeNotice(Notice notice) throws Exception {
		noticeDao.insertNotice(notice);
	}
	
	@Override
	public void deleteNotice(Integer noticeNum) throws Exception {
		noticeDao.deleteNotice(noticeNum);
	}
	
	@Override
	public Notice selectNotice(Integer noticeNum) throws Exception {
		return noticeDao.selectNotice(noticeNum);
	}
	
	@Override
	public void updateNotice(Notice notice) throws Exception {
		noticeDao.updateNotice(notice);
	}
	
	@Override
	public List<Notice> recentNotices() throws Exception {
		return noticeDao.selectRecentNotices();
	}
	
	@Override
	public List<Notice> NoticeListByPage(PageInfo pageInfo) throws Exception {
		Integer noticeCnt = noticeDao.selectNoticeCount();
		Integer allPage = (int)Math.ceil((double)noticeCnt/pageInfo.getPageSize());
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
		Integer endPage = startPage+10-1;
		if(endPage>allPage) endPage=allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		Map<String, Object> param = new HashMap<>();
		param.put("start", pageInfo.getOffset());
        param.put("pageSize", pageInfo.getPageSize());
        
		return noticeDao.selectNoticeListByPage(param);
	}

	@Override
	public List<Notice> getFixNoticeList() throws Exception {
		return noticeDao.selectFixNoticeList();
	}

	@Override
	public Integer getNoticeCount() throws Exception {
		return noticeDao.selectNoticeCount();
	}

	
}
