package com.lkf.example.biz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lkf.common.page.PageOut;
import com.lkf.common.response.ResponseResult;
import com.lkf.example.api.service.UserService;
import com.lkf.example.biz.dao.UserDao;
import com.lkf.example.model.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户业务实现
 * @author administrator
 * @date 2017年3月7日 下午5:25:04
 *
 */
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userMapper;

    @Override
    public void addUser(UserPO user)
    {
        userMapper.addUser(user);
    }

    @Override
    public ResponseResult<PageOut> findAllUser()
    {
	    Page<List<UserPO>> page=PageHelper.startPage(1,2);
       List<UserPO> list= userMapper.findAllUser();
        return ResponseResult.success(PageOut.result(page.getPages(),page.getResult()));
    }
}
