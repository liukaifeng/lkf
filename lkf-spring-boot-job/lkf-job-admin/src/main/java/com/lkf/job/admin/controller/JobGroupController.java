package com.lkf.job.admin.controller;

import com.lkf.job.admin.core.model.JobGroup;
import com.lkf.job.admin.dao.JobGroupDao;
import com.lkf.job.admin.dao.JobInfoDao;
import com.lkf.job.core.biz.model.ReturnT;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * job group controller
 * @author lkf 2016-10-02 20:52:56
 */
@Controller
@RequestMapping("/jobgroup")
public class JobGroupController {

	@Resource
	public JobInfoDao xxlJobInfoDao;
	@Resource
	public JobGroupDao jobGroupDao;

	@RequestMapping
	public String index(Model model) {

		// job group (executor)
		List<JobGroup> list = jobGroupDao.findAll();

		model.addAttribute("list", list);
		return "jobgroup/jobgroup.index";
	}

	@RequestMapping("/save")
	@ResponseBody
	public ReturnT<String> save(JobGroup jobGroup){

		// valid
		if (jobGroup.getAppName()==null || StringUtils.isBlank(jobGroup.getAppName())) {
			return new ReturnT<String>(500, "请输入AppName");
		}
		if (jobGroup.getAppName().length()>64) {
			return new ReturnT<String>(500, "AppName长度限制为4~64");
		}
		if (jobGroup.getTitle()==null || StringUtils.isBlank(jobGroup.getTitle())) {
			return new ReturnT<String>(500, "请输入名称");
		}
		if (jobGroup.getAddressType()!=0) {
			if (StringUtils.isBlank(jobGroup.getAddressList())) {
				return new ReturnT<String>(500, "手动录入注册方式，机器地址不可为空");
			}
			String[] addresss = jobGroup.getAddressList().split(",");
			for (String item: addresss) {
				if (StringUtils.isBlank(item)) {
					return new ReturnT<String>(500, "机器地址非法");
				}
			}
		}

		int ret = jobGroupDao.save(jobGroup);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(JobGroup jobGroup){
		// valid
		if (jobGroup.getAppName()==null || StringUtils.isBlank(jobGroup.getAppName())) {
			return new ReturnT<String>(500, "请输入AppName");
		}
		if (jobGroup.getAppName().length()>64) {
			return new ReturnT<String>(500, "AppName长度限制为4~64");
		}
		if (jobGroup.getTitle()==null || StringUtils.isBlank(jobGroup.getTitle())) {
			return new ReturnT<String>(500, "请输入名称");
		}
		if (jobGroup.getAddressType()!=0) {
			if (StringUtils.isBlank(jobGroup.getAddressList())) {
				return new ReturnT<String>(500, "手动录入注册方式，机器地址不可为空");
			}
			String[] addresss = jobGroup.getAddressList().split(",");
			for (String item: addresss) {
				if (StringUtils.isBlank(item)) {
					return new ReturnT<String>(500, "机器地址非法");
				}
			}
		}

		int ret = jobGroupDao.update(jobGroup);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public ReturnT<String> remove(int id){

		// valid
		int count = xxlJobInfoDao.pageListCount(0, 10, id, null);
		if (count > 0) {
			return new ReturnT<String>(500, "该分组使用中, 不可删除");
		}

		List<JobGroup> allList = jobGroupDao.findAll();
		if (allList.size() == 1) {
			return new ReturnT<String>(500, "删除失败, 系统需要至少预留一个默认分组");
		}

		int ret = jobGroupDao.remove(id);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

}
