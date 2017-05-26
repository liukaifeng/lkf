package com.lkf.activiti.listener;

import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * @package: com.tcsl.slyun.bpmn
 * @project-name: sly
 * @description: 自定义activiti事件监听器
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 11-32
 */
public class CustomActivitiEventListener implements ActivitiEventListener {
    /**
     * Called when an event has been fired
     *
     * @param event the event
     */
    @Override
    public void onEvent(ActivitiEvent event) {
        System.out.println("Event received: " + event.getType());
        ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
        TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
        System.out.println("TaskEntity:"+ JSONObject.toJSONString(taskEntity));

    }

    /**
     * @return whether or not the current operation should fail when this listeners execution
     * throws an exception.
     */
    @Override
    public boolean isFailOnException() {
        return false;
    }
}
