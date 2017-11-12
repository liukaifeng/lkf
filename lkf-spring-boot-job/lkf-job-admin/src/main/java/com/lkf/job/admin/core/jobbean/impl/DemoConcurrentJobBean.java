package com.lkf.job.admin.core.jobbean.impl;
//package com.lkf.job.action.job.impl;
//
//import java.util.concurrent.TimeUnit;
//
//import org.quartz.DisallowConcurrentExecution;
//
//import com.lkf.job.action.job.LocalNomalJobBean;
//
///**
// * demo job bean for no-concurrent
// * @author lkf 2016-3-12 14:25:14
// */
//@Deprecated
//@DisallowConcurrentExecution	// 串行；线程数要多配置几个，否则不生效；
//public class DemoConcurrentJobBean extends LocalNomalJobBean {
//
//	@Override
//	public Object handle(String... param) {
//		
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			logger.error(e.getMessage(), e);
//		}
//		
//		return false;
//	}
//
//}
