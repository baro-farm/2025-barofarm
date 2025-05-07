package service.admin;

import java.util.List;

import dto.admin.Notice;
import util.PageInfo;

public interface NoticeService {
	List<Notice> allNotice(int offset, int pageSize) throws Exception;
	void writeNotice(Notice notice) throws Exception;
	void deleteNotice(Integer noticeNum) throws Exception;
	Notice selectNotice(Integer noticeNum) throws Exception;
	void updateNotice(Notice notice) throws Exception;
	List<Notice> recentNotices() throws Exception;
	List<Notice> NoticeListByPage(PageInfo pageInfo) throws Exception;
	List<Notice> getFixNoticeList() throws Exception;
	Integer getNoticeCount() throws Exception;
	Integer getNoticeCountAll() throws Exception;
}
