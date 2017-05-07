package com.lkf.common.response;

import com.lkf.common.constant.ResponseResultConstant;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ClassName: ResponseResult
 * @Description:API统一返回结果
 * @Author: 刘凯峰
 * @Date: 2017年4月13日 上午10:46:19
 */
public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;/*返回码*/
	private String msg;/*返回信息*/
	private T data;/*返回数据对象*/

	public ResponseResult() {
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个成功的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午10:57:45
	 * @param:
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult success() {
		return success("", "", null);
	}


	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个成功的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午11:00:01
	 * @param: code 返回码
	 * @param: msg  返回提示信息
	 * @param: data 返回数据对象
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult success(String code, String msg, Object data) {
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(StringUtils.isEmpty(code) ? ResponseResultConstant.SYS_SUCCESS.getCode() : code);
		responseResult.setMsg(StringUtils.isEmpty(msg) ? ResponseResultConstant.SYS_SUCCESS.getMsg() : msg);
		responseResult.setData(data);
		return responseResult;
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个成功的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午11:18:24
	 * @param: data 返回数据对象
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult success(Object data) {
		return success("", "", data);
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个成功的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午11:18:24
	 * @param: code 返回编码
	 * @param: msg 返回提示信息
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult success(String code, String msg) {
		return success(code, msg, null);
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个失败的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午10:57:45
	 * @param:
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult failure() {
		return failure("", "", null);
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个失败的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午10:57:45
	 * @param:
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult failure(String msg) {
		return failure("", msg, null);
	}

	/**
	 * @method-name: failure
	 * @description: 返回一个失败的结果, 自定义编码和提示信息
	 * @author: 刘凯峰
	 * @date: 2017/4/27 9:02
	 * @param: [code, msg]
	 * @return: com.tcsl.slyun.common.model.ResponseResult
	 * @version V1.0
	 * <p>
	 * update-logs:方法变更说明
	 * <p>
	 * ****************************************************
	 * <p>
	 * name:
	 * <p>
	 * date:
	 * <p>
	 * description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult failure(String code, String msg) {
		return failure(code, msg, null);
	}

	/**
	 * @throws
	 * @Title: success
	 * @Description: 返回一个失败的结果
	 * @Author 刘凯峰
	 * @Date 2017年4月13日 上午11:00:01
	 * @param: code 返回码
	 * @param: msg  返回提示信息
	 * @param: data 返回数据对象
	 * @return: ResponseResult
	 * @Version V1.0
	 * <p>
	 * UpDate Logs:
	 * <p>
	 * ****************************************************
	 * <p>
	 * Name:
	 * <p>
	 * Date:
	 * <p>
	 * Description:
	 * <p>
	 * *****************************************************
	 */
	public static ResponseResult failure(String code, String msg, Object data) {
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(StringUtils.isEmpty(code) ? ResponseResultConstant.SYS_ERROR.getCode() : code);
		responseResult.setMsg(StringUtils.isEmpty(msg) ? ResponseResultConstant.SYS_ERROR.getMsg() : msg);
		responseResult.setData(data);
		return responseResult;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
