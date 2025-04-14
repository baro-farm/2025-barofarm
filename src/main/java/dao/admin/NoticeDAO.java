package dao.admin;

import java.util.List;

import dto.admin.Notice;

public interface NoticeDAO {
	List <Notice> selectNoticeList() throws Exception;
	void insertNotice(Notice notice) throws Exception;
	Notice selectNotice(Integer noticeNum) throws Exception;
	void deleteNotice(Integer noticeNum) throws Exception;
}
