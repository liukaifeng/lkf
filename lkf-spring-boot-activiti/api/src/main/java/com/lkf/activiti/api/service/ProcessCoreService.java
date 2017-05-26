package com.lkf.activiti.api.service;

import com.lkf.common.model.response.ResponseResult;

/**
 * @package: com.lkf.activiti.api.service
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-25 16-25
 */
public interface ProcessCoreService {
    ResponseResult<String> processDeploy();
}
