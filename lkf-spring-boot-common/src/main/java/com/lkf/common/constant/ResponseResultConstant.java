package com.lkf.common.constant;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * Created by kaifeng on 2017/5/7.
 */
@JSONType(serializeEnumAsJavaBean = true)
public enum ResponseResultConstant {

	SYS_SUCCESS("0", "成功"), SYS_ERROR("-10001", "系统异常"),
	SYS_ERROR_DATA_STATE_CHANGED("-10002", "系统状态已改变"),
	SYS_USERNAME_OR_PASSWORD("-10003", "用户名或密码错误"),
	SYS_NO_LOGIN("-10004", "未登录"),SYS_NO_DATA("-10005","数据错误"),AUTH_TOKEN_EXPIRE("-10006","登录令牌已过期"),
	NO_QUALIFIED_DATA("-10007","无符合条件数据"),OPERATION_FAILURE("-10008","操作失败");

	private String code;
	private String msg;

	ResponseResultConstant(String code, String msg) {
		this.code = code;
		this.msg = msg;
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

	public String jsonStr() {
		return JSONObject.toJSONString(this);
	}
}