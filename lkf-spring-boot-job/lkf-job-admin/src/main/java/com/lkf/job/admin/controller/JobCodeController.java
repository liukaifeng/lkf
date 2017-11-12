package com.lkf.job.admin.controller;

import com.lkf.job.admin.core.model.JobInfo;
import com.lkf.job.admin.core.model.JobLogGlue;
import com.lkf.job.admin.dao.JobInfoDao;
import com.lkf.job.admin.dao.JobLogGlueDao;

import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.glue.GlueTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * job code controller
 * @author lkf
 */
@Controller
@RequestMapping("/jobcode")
public class JobCodeController {
	
	@Resource
	private JobInfoDao xxlJobInfoDao;
	@Resource
	private JobLogGlueDao xxlJobLogGlueDao;

	@RequestMapping
	public String index(Model model, int jobId) {
		JobInfo jobInfo = xxlJobInfoDao.loadById(jobId);
		List<JobLogGlue> jobLogGlues = xxlJobLogGlueDao.findByJobId(jobId);

		if (jobInfo == null) {
			throw new RuntimeException("抱歉，任务不存在.");
		}
		if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType())) {
			throw new RuntimeException("该任务非GLUE模式.");
		}

		// Glue类型-字典
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());

		model.addAttribute("jobInfo", jobInfo);
		model.addAttribute("jobLogGlues", jobLogGlues);
		return "jobcode/jobcode.index";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ReturnT<String> save(Model model, int id, String glueSource, String glueRemark) {
		// valid
		if (glueRemark==null) {
			return new ReturnT<String>(500, "请输入备注");
		}
		if (glueRemark.length()<4 || glueRemark.length()>100) {
			return new ReturnT<String>(500, "备注长度应该在4至100之间");
		}
		JobInfo exists_jobInfo = xxlJobInfoDao.loadById(id);
		if (exists_jobInfo == null) {
			return new ReturnT<String>(500, "参数异常");
		}
		
		// update new code
		exists_jobInfo.setGlueSource(glueSource);
		exists_jobInfo.setGlueRemark(glueRemark);
		exists_jobInfo.setGlueUpdatetime(new Date());
		xxlJobInfoDao.update(exists_jobInfo);

		// log old code
		JobLogGlue JobLogGlue = new JobLogGlue();
		JobLogGlue.setJobId(exists_jobInfo.getId());
		JobLogGlue.setGlueType(exists_jobInfo.getGlueType());
		JobLogGlue.setGlueSource(glueSource);
		JobLogGlue.setGlueRemark(glueRemark);
		xxlJobLogGlueDao.save(JobLogGlue);

		// remove code backup more than 30
		xxlJobLogGlueDao.removeOld(exists_jobInfo.getId(), 30);

		return ReturnT.SUCCESS;
	}
	
}
