package com.lkf.job.admin.core.route.strategy;

import com.lkf.job.admin.core.route.ExecutorRouter;
import com.lkf.job.admin.core.trigger.JobTrigger;
import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.biz.model.TriggerParam;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lkf on 17/3/10.
 */
public class ExecutorRouteRound extends ExecutorRouter {

    private static ConcurrentHashMap<Integer, Integer> routeCountEachJob = new ConcurrentHashMap<Integer, Integer>();
    private static long CACHE_VALID_TIME = 0;
    private static int count(int jobId) {
        // cache clear
        if (System.currentTimeMillis() > CACHE_VALID_TIME) {
            routeCountEachJob.clear();
            CACHE_VALID_TIME = System.currentTimeMillis() + 1000*60*60*24;
        }

        // count++
        Integer count = routeCountEachJob.get(jobId);
        count = (count==null || count>1000000)?(new Random().nextInt(100)):++count;  // 初始化时主动Random一次，缓解首次压力
        routeCountEachJob.put(jobId, count);
        return count;
    }

    public String route(int jobId, ArrayList<String> addressList) {
        return addressList.get(count(jobId)%addressList.size());
    }


    @Override
    public ReturnT<String> routeRun(TriggerParam triggerParam, ArrayList<String> addressList) {

        // address
        String address = route(triggerParam.getJobId(), addressList);

        // run executor
        ReturnT<String> runResult = JobTrigger.runExecutor(triggerParam, address);
        runResult.setContent(address);
        return runResult;
    }
}