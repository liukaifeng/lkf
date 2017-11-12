package com.lkf.job.executor.service.jobhandler;

import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.handler.IJobHandler;
import com.lkf.job.core.handler.annotation.JobHandler;
import com.lkf.job.core.log.JobLogger;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * 任务Handler的一个Demo（Bean模式）
 *
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "JobLogger.log" 打印执行日志；
 *
 * @author lkf 2015-12-19 19:43:36
 */
@JobHandler(value="demoJobHandler")
@Service
public class DemoJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		JobLogger.log("lkf-JOB, Hello World.");

		for (int i = 0; i < 5; i++) {
			JobLogger.log("beat at:" + i);
			TimeUnit.SECONDS.sleep(2);
		}
		return ReturnT.SUCCESS;
	}

}
