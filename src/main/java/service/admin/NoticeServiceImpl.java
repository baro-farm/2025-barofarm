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

}
