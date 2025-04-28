package dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.admin.Notice;
import util.MybatisSqlSessionFactory;

public class NoticeDAOImpl implements NoticeDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<Notice> selectNoticeList() throws Exception {
		return sqlSession.selectList("mapper.notice.selectNoticeList");
	}

	@Override
	public Notice selectNotice(Integer noticeNum) throws Exception {
		return sqlSession.selectOne("mapper.notice.selectNotice", noticeNum);
	}
	
	@Override
	public void insertNotice(Notice notice) throws Exception {
		sqlSession.insert("mapper.notice.insertNotice", notice);
		sqlSession.commit();
	}
	
	public void deleteNotice(Integer noticeNum) throws Exception {
		sqlSession.delete("mapper.notice.deleteNotice", noticeNum);
		sqlSession.commit();
	}
	
	@Override
	public void updateNotice(Notice notice) throws Exception {
		sqlSession.update("mapper.notice.updateNotice", notice);
		sqlSession.commit();		
	}

	@Override
	public List<Notice> selectRecentNotices() throws Exception {
		return sqlSession.selectList("mapper.notice.selectRecentNotices");
	}
	
	@Override
	public List<Notice> selectNoticeListByPage(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.notice.selectNoticeListByPage", param);
	}

	@Override
	public Integer selectNoticeCount() throws Exception {
		return sqlSession.selectOne("mapper.notice.selectNoticeCount");
	}

	@Override
	public List<Notice> selectFixNoticeList() throws Exception {
		return sqlSession.selectList("mapper.notice.selectFixNoticeList");
	}
	
}