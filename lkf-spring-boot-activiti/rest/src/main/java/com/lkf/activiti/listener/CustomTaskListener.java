package com.lkf.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

import java.io.Serializable;

/**
 * @package: com.tcsl.slyun.bpmn
 * @project-name: sly
 * @description: 任务监听器
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 11-43
 */
public class CustomTaskListener implements Serializable, TaskListener {

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

}