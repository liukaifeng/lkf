package com.lkf.job.admin.service;


import com.lkf.job.admin.core.model.JobInfo;
import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.biz.model.ReturnT;

import java.util.Map;

/**
 * core job action for lkf-job
 * 
 * @author lkf 2016-5-28 15:30:33
 */
public interface JobService {
	
	public Map<String, Object> pageList(int start, int length, int jobGroup, String executorHandler, String filterTime);
	
	public ReturnT<String> add(JobInfo jobInfo);
	
	public ReturnT<String> reschedule(JobInfo jobInfo);
	
	public ReturnT<String> remove(int id);
	
	public ReturnT<String> pause(int id);
	
	public ReturnT<String> resume(int id);
	
	public ReturnT<String> triggerJob(int id);

	public Map<String,Object> dashboardInfo();

	public ReturnT<Map<String, Object>> triggerChartDate();

}
