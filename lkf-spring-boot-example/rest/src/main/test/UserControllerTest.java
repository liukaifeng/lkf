import com.alibaba.fastjson.JSONObject;
import com.lkf.example.api.service.UserService;
import com.lkf.example.model.entity.User;
import com.lkf.example.model.entity.UserPO;
import com.lkf.example.rest.App;
import com.lkf.redis.RedisUtilService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by kaifeng on 2017/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UserControllerTest {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtilService redisUtilService;
	@Test
	public void getAllUser(){
		System.err.println(JSONObject.toJSONString(userService.findAllUser()));
	}

	@Test
	public void addUser() throws Exception {
		UserPO userPO=new UserPO();
		userPO.setName("郑多燕");
		userPO.setPassword("123456");
		userPO.setAge(56);
		userPO.setEmail("88qq.com");
		userService.addUser(userPO);
		System.err.println("用户添加完成");
	}
	@Test
	public void addUserMongodb() throws Exception {
		User userPO=new User(10L,"lkf",5);

		userService.addUserMongodb(userPO);
	}
	@Test
	public void test() throws Exception {
//		System.out.println(redisUtilService.incr("kaifeng",5));
		for (int i = 0; i <10; i++) {
			System.err.println("kaifeng"+i+"=="+redisUtilService.incr("kaifeng"+i));
		}


	}
}
