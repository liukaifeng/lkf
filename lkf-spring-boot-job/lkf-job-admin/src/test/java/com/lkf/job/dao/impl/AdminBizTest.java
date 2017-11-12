package com.lkf.job.dao.impl;

import com.lkf.job.core.biz.AdminBiz;
import com.lkf.job.core.biz.model.RegistryParam;
import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.enums.RegistryConfig;
import com.lkf.job.core.rpc.netcom.NetComClientProxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * admin api test
 *
 * @author lkf 2017-07-28 22:14:52
 */
public class AdminBizTest {

    // admin-client
    private static String addressUrl = "http://127.0.0.1:8080/lkf-job-admin".concat(AdminBiz.MAPPING);
    private static String accessToken = null;

    /**
     * registry executor
     *
     * @throws Exception
     */
    @Test
    public void registryTest() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "lkf-job-executor-example", "127.0.0.1:9999");
        ReturnT<String> returnT = adminBiz.registry(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * registry executor remove
     *
     * @throws Exception
     */
    @Test
    public void registryRemove() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        // test executor registry remove
        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), "lkf-job-executor-example", "127.0.0.1:9999");
        ReturnT<String> returnT = adminBiz.registryRemove(registryParam);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

    /**
     * trigger job for once
     *
     * @throws Exception
     */
    @Test
    public void triggerJob() throws Exception {
        AdminBiz adminBiz = (AdminBiz) new NetComClientProxy(AdminBiz.class, addressUrl, accessToken).getObject();

        int jobId = 1;
        ReturnT<String> returnT = adminBiz.triggerJob(jobId);
        Assert.assertTrue(returnT.getCode() == ReturnT.SUCCESS_CODE);
    }

}
