

import com.lkf.activiti.rest.ActivitiController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * @package: com.lkf.activiti.rest
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-25 16-30
 */
public class ActivitiControllerTest extends BaseTest {

    @Autowired
    private ActivitiController activitiController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(activitiController).build();
    }

    @Test
    public void processDeploy() throws Exception {
        MockMvcGet("/api/activiti/process/deploy");
    }

}