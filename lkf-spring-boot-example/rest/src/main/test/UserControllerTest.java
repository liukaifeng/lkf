import com.lkf.example.api.service.UserService;
import com.lkf.example.rest.App;
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

	@Test
	public void getAllUser(){
		System.err.println(userService.findAllUser());

	}
}
