package com.lkf.job.admin.dao;

import com.lkf.job.admin.core.model.JobLogGlue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 * @author lkf 2016-5-19 18:04:56
 */
public interface JobLogGlueDao {
	
	public int save(JobLogGlue JobLogGlue);
	
	public List<JobLogGlue> findByJobId(@Param("jobId") int jobId);

	public int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

	public int deleteByJobId(@Param("jobId") int jobId);
	
}
