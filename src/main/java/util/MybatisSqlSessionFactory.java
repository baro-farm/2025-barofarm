package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "resource/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			// SqlSessionFactoryBuilder 생성
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			// SqlSessionFactory 생성
			sqlSessionFactory = builder.build(inputStream);
			
		} catch (IOException e) {
			throw new RuntimeException("MyBatis 설정 오류", e);
		}
 	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}