package com.lkf.example.api.service;


import com.lkf.common.page.PageOut;
import com.lkf.common.response.ResponseResult;
import com.lkf.example.model.entity.UserPO;

import java.util.List;

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
    void addUser(UserPO user);
    
    ResponseResult<PageOut> findAllUser();
}
