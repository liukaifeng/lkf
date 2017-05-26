package com.lkf.activiti.listener;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

/**
 * @package: com.lkf.activiti.listener
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 17-29
 */
public class JumpCmd implements Command<ExecutionEntity> {

    private String processInstanceId;
    private String activityId;
    public static final String REASION_DELETE = "deleted";

    public JumpCmd(String processInstanceId, String activityId) {
        this.processInstanceId = processInstanceId;
        this.activityId = activityId;
    }

    @Override
    public ExecutionEntity execute(CommandContext commandContext) {
        ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findExecutionById(processInstanceId);
        executionEntity.destroyScope(REASION_DELETE);
        ProcessDefinitionImpl processDefinition = executionEntity.getProcessDefinition();
        ActivityImpl activity = processDefinition.findActivity(activityId);
        executionEntity.executeActivity(activity);
        return executionEntity;
    }

}
