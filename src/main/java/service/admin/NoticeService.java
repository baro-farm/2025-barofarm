package service.admin;

import java.util.List;

import dto.admin.Notice;

public interface NoticeService {
	List<Notice> allNotice() throws Exception;
	void writeNotice(Notice notice) throws Exception;
}
