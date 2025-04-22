package service.admin;

import java.util.List;

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
		Integer allPage = (int)Math.ceil((double)noticeCnt/10);
		//startPage : 1~10=<1, 11~20=>11
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; // 1,11,21,31 ...
		//endPage : 1~10=>10, 11~20=>20
		Integer endPage = startPage+10-1; // 10,20,30,40 ...
		if(endPage>allPage) endPage=allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		Integer row = (pageInfo.getCurPage()-1)*10+1;
		List<Notice> noticeList = noticeDao.selectNoticeListByPage(row);
		
		return noticeList;
	}

	
}
