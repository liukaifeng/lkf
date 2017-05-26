package com.lkf.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

/**
 * @package: com.lkf.activiti.listener
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 13-58
 */
@SuppressWarnings("serial")
public class TaskListenerImpl implements TaskListener {

    private Expression arg;

    public Expression getArg() {
        return arg;
    }

    public void setArg(Expression arg) {
        this.arg = arg;
    }


    /**用来指定任务的办理人*/
    @Override
    public void notify(DelegateTask delegateTask) {
        //指定个人任务的办理人，也可以指定组任务的办理人
        //个人任务：通过类去查询数据库，将下一个任务的办理人查询获取，然后通过setAssignee()的方法指定任务的办理人
//        delegateTask.setAssignee("灭绝师太");
        System.out.println("任务监听器:" + delegateTask.toString());
    }

}
