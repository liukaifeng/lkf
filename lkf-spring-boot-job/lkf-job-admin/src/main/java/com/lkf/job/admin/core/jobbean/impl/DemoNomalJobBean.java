package com.lkf.job.admin.core.jobbean.impl;
//package com.lkf.job.action.job.impl;
//
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.lkf.job.action.job.LocalNomalJobBean;
//
///**
// * demo job bean for concurrent
// * @author lkf 2016-3-12 14:25:57
// */
//@Deprecated
//public class DemoNomalJobBean extends LocalNomalJobBean {
//	private static Logger Logger = LoggerFactory.getLogger(DemoNomalJobBean.class);
//	
//	@Override
//	public Object handle(String... param) {
//		Logger.info("DemoNomalJobBean run :" + param);
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
