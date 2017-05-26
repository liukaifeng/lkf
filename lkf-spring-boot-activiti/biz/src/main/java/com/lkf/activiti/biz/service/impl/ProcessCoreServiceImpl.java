package com.lkf.activiti.biz.service.impl;

import com.lkf.activiti.api.service.ProcessCoreService;
import com.lkf.common.model.response.ResponseResult;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @package: com.lkf.activiti.biz.service.impl
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-25 16-12
 */
@Service
public class ProcessCoreServiceImpl implements ProcessCoreService {
    @Autowired
    private RepositoryService repositoryService;/*管理流程定义*/
    @Autowired
    private RuntimeService runtimeService;/*执行管理，包括启动、推进、删除流程实例等操作*/
    @Autowired
    private TaskService taskService;/*任务管理*/
    @Autowired
    private HistoryService historyService;/* 历史管理(执行完的数据的管理)*/
    @Autowired
    private IdentityService identityService;/* 历史管理(执行完的数据的管理)*/
    @Autowired
    private FormService formService;/*一个可选服务，任务表单管理*/

    public ResponseResult<String> processDeploy() {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("/processes/apply_for_open.bpmn").deploy();
        if (deployment != null) {
        return ResponseResult.success(deployment.getId());
        }
        return ResponseResult.failure();
    }
}
