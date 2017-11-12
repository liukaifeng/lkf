package com.lkf.job.admin.core.route.strategy;

import com.lkf.job.admin.core.route.ExecutorRouter;
import com.lkf.job.admin.core.trigger.JobTrigger;
import com.lkf.job.core.biz.model.ReturnT;
import com.lkf.job.core.biz.model.TriggerParam;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lkf on 17/3/10.
 */
public class ExecutorRouteRandom extends ExecutorRouter {

    private static Random localRandom = new Random();

    public String route(int jobId, ArrayList<String> addressList) {
        // Collections.shuffle(addressList);
        return addressList.get(localRandom.nextInt(addressList.size()));
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
