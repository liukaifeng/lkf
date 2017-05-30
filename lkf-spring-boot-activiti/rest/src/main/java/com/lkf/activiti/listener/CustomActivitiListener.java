package com.lkf.activiti.listener;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/29 0029.
 */
@Service("customActivitiListener")
public class CustomActivitiListener implements Serializable,ActivitiEventListener, ExecutionListener,TaskListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        if (delegateExecution != null) {
            if (delegateExecution.getEventName().equals("start"))
            {
                System.err.println("start:======开始========");
            }
            if (delegateExecution.getEventName().equals("take"))
            {
                System.err.println("take:======连线========");
            }
            if (delegateExecution.getEventName().equals("end"))
            {
                System.err.println("end:======结束========");
            }
            System.err.println("流程监听器:");
            System.err.println("getCurrentActivityId:" + delegateExecution.getCurrentActivityId());
            System.err.println("getCurrentActivityName:" + delegateExecution.getCurrentActivityName());
            System.err.println("getEventName:" + delegateExecution.getEventName());
            System.err.println("getId:" + delegateExecution.getId());
            System.err.println("getParentId:" + delegateExecution.getParentId());
            System.err.println("getProcessBusinessKey:" + delegateExecution.getProcessBusinessKey());
            System.err.println("getVariables:" + delegateExecution.getVariables());
            System.err.println("getProcessDefinitionId:" + delegateExecution.getProcessDefinitionId());
            System.err.println("getProcessInstanceId:" + delegateExecution.getProcessInstanceId());
            System.err.println("getProcessBusinessKey:" + delegateExecution.getProcessBusinessKey());
        } else {
            System.out.println("流程监听器为null");
        }
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if ("create".endsWith(eventName)) {
            System.out.println("create=========");
        }else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        }else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        }else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
        System.out.println("任务监听器:" +delegateTask.toString());
        System.out.println("当前事件名称:" +delegateTask.getEventName());
    }

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        System.err.println("=========================ActivitiEvent==========================");
        System.err.println("Event received getType: " + activitiEvent.getType());
        System.err.println("Event received getEngineServices: " + activitiEvent.getEngineServices());
        System.err.println("Event received getExecutionId: " + activitiEvent.getExecutionId());
        System.err.println("Event received getProcessDefinitionId: " + activitiEvent.getProcessDefinitionId());
        System.err.println("Event received getProcessInstanceId: " + activitiEvent.getProcessInstanceId());

        ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) activitiEvent;
        TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
        System.err.println("TaskEntity:"+ JSONObject.toJSONString(taskEntity));
        System.err.println("=========================ActivitiEvent==========================");
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
