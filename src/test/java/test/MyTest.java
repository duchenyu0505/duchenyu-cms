package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.duchenyu.dao.UserDao;
import com.duchenyu.pojo.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class MyTest {

	@Autowired
	private UserDao userDao;
	@Test
	public void myTest() {
		List<User> list = userDao.select(null);
		System.out.println(list);
	}
}
