import com.alibaba.fastjson.JSONObject;
import com.lkf.example.rest.App;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @package: com.lkf.example.biz
 * @project-name: sly
 * @description: 单元测试基类
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-05 10-29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class BaseTest {
	public MockMvc mvc;/*API单元测试工具*/

	/**
	 * @method-name: MockMvcPost
	 * @description: POST请求
	 * @author: 刘凯峰
	 * @date: 2017/5/5 11:37
	 * @param: [postUri, postParam] 请求地址 和 请求参数
	 * @return: void
	 * @version V1.0
	 * update-logs:方法变更说明
	 * ****************************************************
	 * name:
	 * date:
	 * description:
	 * *****************************************************
	 */
	public void MockMvcPost(String postUri, Object postParam) throws Exception {
		String result = mvc.perform(
				MockMvcRequestBuilders.post(postUri)
						.content(JSONObject.toJSONString(postParam))/*请求参数*/
						.contentType(MediaType.APPLICATION_JSON_UTF8)/*参数类型*/
						.accept(MediaType.APPLICATION_JSON_UTF8))/*返回的结果类型*/
				.andDo(print())/*打印出请求和相应相关信息*/
				.andReturn().getResponse().getContentAsString();/*将返回结果转换成字符串*/
		printOutResult(result);
	}

	/**
	 * @method-name: MockMvcGet
	 * @description: GET请求
	 * @author: 刘凯峰
	 * @date: 2017/5/5 11:40
	 * @param: [getUri] 请求完整地址包括参数
	 * @return: void
	 * @version V1.0
	 * update-logs:方法变更说明
	 * ****************************************************
	 * name:
	 * date:
	 * description:
	 * *****************************************************
	 */
	public void MockMvcGet(String getUri) throws Exception {
		String result = mvc.perform(
				MockMvcRequestBuilders.get(getUri)
						.contentType(MediaType.APPLICATION_JSON_UTF8)/*参数类型*/
						.accept(MediaType.APPLICATION_JSON_UTF8))/*返回的结果类型*/
				.andDo(print())/*打印出请求和相应相关信息*/
				.andReturn().getResponse().getContentAsString();/*将返回结果转换成字符串*/
		printOutResult(result);
	}

	/**
	 * @method-name: MockMvcPut
	 * @description: PUT请求
	 * @author: 刘凯峰
	 * @date: 2017/5/5 11:44
	 * @param: [putUri, putParam] 请求地址和body中提交的参数
	 * @return: void
	 * @version V1.0
	 * update-logs:方法变更说明
	 * ****************************************************
	 * name:
	 * date:
	 * description:
	 * *****************************************************
	 */
	public void MockMvcPut(String putUri, Object putParam) throws Exception {
		String result = null;
		if (putParam == null) {
			result = mvc.perform(
					MockMvcRequestBuilders.put(putUri)
							.contentType(MediaType.APPLICATION_JSON_VALUE)/*参数类型*/
							.accept(MediaType.APPLICATION_JSON_VALUE))/*返回的结果类型*/
					.andDo(print())/*打印出请求和相应相关信息*/
					.andReturn().getResponse().getContentAsString();/*将返回结果转换成字符串*/
		} else {
			result = mvc.perform(
					MockMvcRequestBuilders.put(putUri).content(JSONObject.toJSONString(putParam))
							.contentType(MediaType.APPLICATION_JSON_VALUE)/*参数类型*/
							.accept(MediaType.APPLICATION_JSON_VALUE))/*返回的结果类型*/
					.andDo(print())/*打印出请求和相应相关信息*/
					.andReturn().getResponse().getContentAsString();/*将返回结果转换成字符串*/
		}

		printOutResult(result);
	}

	/**
	 * @method-name: MockMvcDelete
	 * @description: DELETE请求
	 * @author: 刘凯峰
	 * @date: 2017/5/5 11:46
	 * @param: [putUri]
	 * @return: void
	 * @version V1.0
	 * update-logs:方法变更说明
	 * ****************************************************
	 * name:
	 * date:
	 * description:
	 * *****************************************************
	 */
	public void MockMvcDelete(String putUri) throws Exception {
		String result = mvc.perform(
				MockMvcRequestBuilders.delete(putUri)
						.contentType(MediaType.APPLICATION_JSON_UTF8)/*参数类型*/
						.accept(MediaType.APPLICATION_JSON_UTF8))/*返回的结果类型*/
				.andDo(print())/*打印出请求和相应相关信息*/
				.andReturn().getResponse().getContentAsString();/*将返回结果转换成字符串*/

		printOutResult(result);
	}

	/**
	 * @method-name: printOutResult
	 * @description: 打印输出访问接口的结果信息
	 * @author: 刘凯峰
	 * @date: 2017/5/5 11:29
	 * @param: [object] 接口返回结果信息
	 * @return: void
	 * @version V1.0
	 * update-logs:方法变更说明
	 * ****************************************************
	 * name:
	 * date:
	 * description:
	 * *****************************************************
	 */
	public void printOutResult(Object object) {
		System.err.println(object);
	}
}