package service.admin;

import java.util.List;

import dao.admin.NoticeDAO;
import dao.admin.NoticeDAOImpl;
import dto.admin.Notice;

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
}
