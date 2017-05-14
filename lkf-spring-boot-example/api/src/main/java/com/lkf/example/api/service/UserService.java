package com.lkf.example.api.service;


import com.lkf.common.model.page.PageOut;
import com.lkf.common.model.response.ResponseResult;
import com.lkf.example.model.entity.User;
import com.lkf.example.model.entity.UserPO;

/**
 * @ClassName: UserService
 * @Description: 用户服务接口
 * @author administrator
 * @date 2017年3月7日 下午5:23:26
 *
 */

public interface UserService
{
    /**
    * @Title: addUser
    * @Description: 添加用户
    * @author administrator
    * @param user
    * @throws
    */
    void addUser(UserPO user) throws Exception;
    
    ResponseResult<PageOut> findAllUser();

    void addUserMongodb(User user) throws Exception;
}
