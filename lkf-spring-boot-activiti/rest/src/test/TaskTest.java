import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lkf.activiti.listener.JumpCmd;
import com.lkf.activiti.rest.AcivitiApplication;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @package: java.com.tcsl.slyun.bpmn.test
 * @project-name: sly
 * @description: todo 一句话描述该类的用途
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-26 13-39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcivitiApplication.class)
public class TaskTest {
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
    @Autowired
    private ManagementService managementService;
    /**
     * 部署流程定义（从inputStream）
     */
    @Test
    public void deploymentProcessDefinition_inputStream() throws FileNotFoundException {
        InputStream inputStreamBpmn = this.getClass().getClassLoader().getResourceAsStream("processes/slyun_workflow_default.bpmn");

        Deployment deployment = repositoryService.createDeployment()
                .addInputStream("slyun_workflow_default.bpmn", inputStreamBpmn)
                .name("商龙云默认工作流").deploy();

        System.out.println("部署：" + JSONObject.toJSONString(deployment));

    }

    @Test
    public void getDeploymentProcess() {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().desc().list();

        for (ProcessDefinition processDefinition:processDefinitionList){
            System.err.println(processDefinition.getDeploymentId()+"\n"+
            processDefinition.getKey()+"\n"+processDefinition.getName()+"\n"+processDefinition.getId());
            System.err.println("========================================");
        }
    }

    @Test
    public void getRuntimeProcess() {
      List<Execution> executionList=  runtimeService.createExecutionQuery().orderByProcessDefinitionId().desc().list();
      if (executionList!=null&&executionList.size()>0)
      {
          for (Execution execution: executionList) {
              System.err.println("getProcessInstanceId=="+execution.getProcessInstanceId());
              System.err.println("getDescription=="+execution.getDescription());
              System.err.println("getId=="+execution.getId());
              System.err.println("getName=="+execution.getName());

          }
      }
      else{
          System.err.println("没有正在执行的流程");
      }

    }
    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {
        String processDefinitionKey = "slyun_workflow_default:4:385008";
        identityService.setAuthenticatedUserId("100001");
        identityService.setUserInfo("100001","username","liukaifeng");
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinitionKey);
        System.err.println("=================ProcessInstance=======================");
        System.err.println("getBusinessKey:" +  pi.getBusinessKey());
        System.err.println("getDeploymentId:" + pi.getDeploymentId());
        System.err.println("getDescription:" + pi.getDescription());
        System.err.println("getLocalizedDescription:" + pi.getLocalizedDescription());
        System.err.println("getLocalizedName:" + pi.getLocalizedName());
        System.err.println("getName:" + pi.getName());
        System.err.println("getProcessDefinitionId:" + pi.getProcessDefinitionId());
        System.err.println("getProcessDefinitionKey:" + pi.getProcessDefinitionKey());
        System.err.println("getProcessDefinitionName:" + pi.getProcessDefinitionName());
        System.err.println("getProcessVariables:" + pi.getProcessVariables().toString());
        System.err.println("getParentId:" + pi.getParentId());
        System.err.println("getProcessDefinitionName:" + pi.getProcessDefinitionName());
        System.err.println("getProcessDefinitionVersion:" + pi.getProcessDefinitionVersion());
        System.err.println("getProcessDefinitionName:" + pi.getActivityId());
        System.err.println("===================ProcessInstance=====================");

    }
    @Test
    public void getProcessByUser(){
    }

    @Test
    public void findTaskList(){
        List<Task> taskList=taskService.createTaskQuery().processDefinitionId("slyun_workflow_default:2:367508").orderByTaskCreateTime().desc().list();
        if (taskList != null&&taskList.size()>0) {
            for (Task task:taskList)
            {
                System.err.println("=================Task=======================");
                System.err.println("getId:" +  task.getId());
                System.err.println("getName:" + task.getName());
                System.err.println("getOwner:" + task.getOwner());
                System.err.println("getAssignee:" +task.getAssignee());
                System.err.println("===================Task=====================");
            }
        }
        else {
            System.err.println("没有正在执行的任务");
        }
    }

    @Test
    public void completeTask(){
        Map<String,Object> map= Maps.newHashMap();
        map.put("business_leader_flag",0);
        String taskId="387505";
        taskService.setAssignee(taskId,"1000900");
        taskService.addComment(taskId,null,"当前是商务审批，下一步流转到财务审批");
        taskService.complete(taskId,map);
        System.err.println("任务完成");
    }
    @Test
    public void taskRollback(){
        taskRollback("82504");
    }

    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonalTask() {

//        String assignee = "张翠山";
//        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
//                .createTaskQuery()//创建任务查询对象
//                /**查询条件（where部分）*/
//                .taskAssignee(assignee)//指定个人任务查询，指定办理人
////                      .taskCandidateUser(candidateUser)//组任务的办理人查询
////                      .processDefinitionId(processDefinitionId)//使用流程定义ID查询
////                      .processInstanceId(processInstanceId)//使用流程实例ID查询
////                      .executionId(executionId)//使用执行对象ID查询
//                /**排序*/
//                .orderByTaskCreateTime().asc()//使用创建时间的升序排列
//                /**返回结果集*/
////                      .singleResult()//返回惟一结果集
////                      .count()//返回结果集的数量
////                      .listPage(firstResult, maxResults);//分页查询
//                .list();//返回列表


    }


//    //可以分配个人任务从一个人到另一个人（认领任务）
//    @Test
//    public void setAssigneeTask(){
//        //任务ID
//        String taskId = "5804";
//        //指定的办理人
//        String userId = "张翠山";
//        processEngine.getTaskService()//
//                .setAssignee(taskId, userId);
//    }

    public void taskRollback(String taskId){
        //根据要跳转的任务ID获取其任务
        HistoricTaskInstance hisTask = historyService
                .createHistoricTaskInstanceQuery().taskId(taskId)
                .singleResult();
        //进而获取流程实例
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(hisTask.getProcessInstanceId())
                .singleResult();
        //取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(hisTask.getProcessDefinitionId());
        //获取历史任务的Activity
        ActivityImpl hisActivity = definition.findActivity(hisTask.getTaskDefinitionKey());
        //实现跳转
        managementService.executeCommand(new JumpCmd(instance.getId(), hisActivity.getId()));
    }
}
