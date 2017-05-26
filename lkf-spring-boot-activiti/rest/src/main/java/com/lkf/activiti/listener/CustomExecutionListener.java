package com.lkf.activiti.listener;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * @package: com.tcsl.slyun.bpmn
 * @project-name: sly
 * @description: 流程监听器（启动【start】、结束【end】、连线【take】）
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 11-41
 */
public class CustomExecutionListener implements Serializable, ExecutionListener {
    Logger logger = LogManager.getLogger(this.getClass());
    private static final long serialVersionUID = 8513750196548027535L;
    private Expression message;

    public Expression getMessage() {
        return message;
    }

    public void setMessage(Expression message) {
        this.message = message;
    }

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        if (execution != null) {
            if (execution.getEventName().equals("start"))
            {
                System.err.println("start:======开始========");
            }
            if (execution.getEventName().equals("take"))
            {
                System.err.println("take:======连线========");
            }
            if (execution.getEventName().equals("end"))
            {
                System.err.println("end:======结束========");
            }
            System.err.println("流程监听器:");
            System.err.println("getCurrentActivityId:" + execution.getCurrentActivityId());
            System.err.println("getCurrentActivityName:" + execution.getCurrentActivityName());
            System.err.println("getEventName:" + execution.getEventName());
            System.err.println("getId:" + execution.getId());
            System.err.println("getParentId:" + execution.getParentId());
            System.err.println("getProcessBusinessKey:" + execution.getProcessBusinessKey());
            System.err.println("getVariables:" + execution.getVariables());
            System.err.println("getProcessDefinitionId:" + execution.getProcessDefinitionId());
            System.err.println("getProcessInstanceId:" + execution.getProcessInstanceId());
            System.err.println("getProcessBusinessKey:" + execution.getProcessBusinessKey());
        } else {
            System.out.println("流程监听器为null");
        }

    }
}
