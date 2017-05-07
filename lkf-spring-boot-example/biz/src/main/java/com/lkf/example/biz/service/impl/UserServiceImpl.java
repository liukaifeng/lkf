package com.lkf.example.biz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lkf.common.page.PageOut;
import com.lkf.common.response.ResponseResult;
import com.lkf.example.api.service.UserService;
import com.lkf.example.biz.dao.UserMapper;
import com.lkf.example.model.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author administrator
 * @ClassName: UserServiceImpl
 * @Description: 用户业务实现
 * @date 2017年3月7日 下午5:25:04
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUser(UserPO user) throws Exception {
		userMapper.addUser(user);
		int a=1/0;
	}

	@Override
	public ResponseResult<PageOut> findAllUser() {
		Page<List<UserPO>> page = PageHelper.startPage(1, 2);
		List<UserPO> list = userMapper.getAllUser();
		return ResponseResult.success(PageOut.result(page.getPages(), page.getResult()));
	}
}
