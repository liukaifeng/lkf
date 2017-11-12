package com.lkf.job.core.handler;

import com.lkf.job.core.biz.model.ReturnT;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
public abstract class IJobHandler {
    /**
     * job handler
     * @param params
     * @return
     * @throws Exception
     */
    public abstract ReturnT<String> execute(String... params) throws Exception;
}
