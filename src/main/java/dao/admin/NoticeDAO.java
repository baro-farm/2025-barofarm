package dao.admin;

import java.util.List;

import dto.admin.Notice;

public interface NoticeDAO {
	List <Notice> selectNoticeList() throws Exception;
}
