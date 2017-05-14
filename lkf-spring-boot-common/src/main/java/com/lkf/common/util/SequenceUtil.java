package com.lkf.common.util;

import com.lkf.common.model.constant.ParamConstant;

/**
 * Created by kaifeng on 2017/5/13.
 */
public class SequenceUtil {
	/**
	 * @MethodName: Next
	 * @Description: 生成序列标识
	 * @Author: 刘凯峰
	 * @Date: 2017/5/14 12:17
	 * @Param: [prefix, sequenceNo]
	 * @Return: java.lang.String
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public static String Next(String prefix, Long sequenceNo) {
		String result = "0";
		if (String.valueOf(sequenceNo).length() < 6)
			result =String.format("%06d", sequenceNo);
		else
			result = sequenceNo.toString();
		return prefix+result;
	}

	public static void main(String[] args) {
		new Thread(new ThreadTest(ParamConstant.user_code_prefix,1002L)).start();
		new Thread(new ThreadTest(ParamConstant.group_code_prefix,1010L)).start();
		new Thread(new ThreadTest(ParamConstant.store_code_prefix,1020L)).start();
	}

	static class ThreadTest implements Runnable {

		private String prefix;
		private Long sequenceNo;
		ThreadTest(String prefix, Long sequenceNo){
			this.prefix=prefix;
			this.sequenceNo=sequenceNo;
		}
		@Override
		public void run() {
			System.out.println(Next(prefix,sequenceNo));

		}
	}
}
