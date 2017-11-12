package com.lkf.job.dao.impl;

import com.lkf.job.admin.core.model.JobRegistry;
import com.lkf.job.admin.dao.JobRegistryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobRegistryDaoTest {

    @Resource
    private JobRegistryDao xxlJobRegistryDao;

    @Test
    public void test(){
        int ret = xxlJobRegistryDao.registryUpdate("g1", "k1", "v1");
        if (ret < 1) {
            ret = xxlJobRegistryDao.registrySave("g1", "k1", "v1");
        }

        List<JobRegistry> list = xxlJobRegistryDao.findAll(1);

        int ret2 = xxlJobRegistryDao.removeDead(1);
    }

}
