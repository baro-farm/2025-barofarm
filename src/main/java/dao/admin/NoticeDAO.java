package dao.admin;

import java.util.List;
import java.util.Map;

import dto.admin.Notice;

public interface NoticeDAO {
	List <Notice> selectNoticeList() throws Exception;
	void insertNotice(Notice notice) throws Exception;
	Notice selectNotice(Integer noticeNum) throws Exception;
	void deleteNotice(Integer noticeNum) throws Exception;
	void updateNotice(Notice notice) throws Exception;
	List<Notice> selectRecentNotices() throws Exception;
	List<Notice> selectNoticeListByPage(Map<String, Object> param) throws Exception;
	Integer selectNoticeCount() throws Exception;
	List<Notice> selectFixNoticeList() throws Exception;
}