package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.admin.Notice;
import util.MybatisSqlSessionFactory;

public class NoticeDAOImpl implements NoticeDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
	public List<Notice> selectNoticeList() throws Exception {
//		System.out.println(sqlSession.selectList("mapper.notice.selectNoticeList"));
		return sqlSession.selectList("mapper.notice.selectNoticeList");
	}

}
