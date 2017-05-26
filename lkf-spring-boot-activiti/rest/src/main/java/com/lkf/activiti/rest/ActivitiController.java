package com.lkf.activiti.rest;

import com.lkf.activiti.api.service.ProcessCoreService;
import com.lkf.common.model.response.ResponseResult;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.lkf.activiti.rest
 * @project-name: lkf
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-25 15-49
 */
@RestController
@RequestMapping("/api/activiti")
public class ActivitiController {
    @Autowired
    private ProcessCoreService processCoreService;

    @GetMapping(path = "/process/deploy",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<String> processDeploy() {
        return processCoreService.processDeploy();
    }

}
