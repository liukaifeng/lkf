package com.lkf.job.admin.core.thread;

import com.lkf.job.admin.core.model.JobGroup;
import com.lkf.job.admin.core.model.JobRegistry;
import com.lkf.job.admin.core.schedule.JobDynamicScheduler;

import com.lkf.job.core.constant.RegistryConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * job registry instance
 * @author lkf 2016-10-02 19:10:24
 */
public class JobRegistryMonitorHelper {
	private static Logger logger = LoggerFactory.getLogger(JobRegistryMonitorHelper.class);

	private static JobRegistryMonitorHelper instance = new JobRegistryMonitorHelper();
	public static JobRegistryMonitorHelper getInstance(){
		return instance;
	}

	private Thread registryThread;
	private volatile boolean toStop = false;
	public void start(){
		registryThread = new Thread(() -> {
            while (!toStop) {
                try {
                    // auto registry group
                    List<JobGroup> groupList = JobDynamicScheduler.jobGroupDao.findByAddressType(0);
                    if (CollectionUtils.isNotEmpty(groupList)) {

                        // remove dead address (admin/executor)
                        JobDynamicScheduler.JobRegistryDao.removeDead(RegistryConfig.DEAD_TIMEOUT);

                        // fresh online address (admin/executor)
                        HashMap<String, List<String>> appAddressMap = new HashMap<String, List<String>>();
                        List<JobRegistry> list = JobDynamicScheduler.JobRegistryDao.findAll(RegistryConfig.DEAD_TIMEOUT);
                        if (list != null) {
                            for (JobRegistry item: list) {
                                if (RegistryConfig.RegistType.EXECUTOR.name().equals(item.getRegistryGroup())) {
                                    String appName = item.getRegistryKey();
                                    List<String> registryList = appAddressMap.get(appName);
                                    if (registryList == null) {
                                        registryList = new ArrayList<String>();
                                    }

                                    if (!registryList.contains(item.getRegistryValue())) {
                                        registryList.add(item.getRegistryValue());
                                    }
                                    appAddressMap.put(appName, registryList);
                                }
                            }
                        }

                        // fresh group address
                        for (JobGroup group: groupList) {
                            List<String> registryList = appAddressMap.get(group.getAppName());
                            String addressListStr = null;
                            if (CollectionUtils.isNotEmpty(registryList)) {
                                Collections.sort(registryList);
                                addressListStr = StringUtils.join(registryList, ",");
                            }
                            group.setAddressList(addressListStr);
                            JobDynamicScheduler.jobGroupDao.update(group);
                        }
                    }
                } catch (Exception e) {
                    logger.error("job registry instance error:{}", e);
                }
                try {
                    TimeUnit.SECONDS.sleep(RegistryConfig.BEAT_TIMEOUT);
                } catch (InterruptedException e) {
                    logger.error("job registry instance error:{}", e);
                }
            }
        });
		registryThread.setDaemon(true);
		registryThread.start();
	}

	public void toStop(){
		toStop = true;
		// interrupt and wait
		registryThread.interrupt();
		try {
			registryThread.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
