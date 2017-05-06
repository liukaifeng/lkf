package com.lkf.example.rest;

import com.lkf.example.api.model.UserPO;
import com.lkf.example.api.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
//	@Autowired
//	private UserService userService;
//
//	@ApiOperation(value = "添加用户信息", notes = "添加用户信息")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User") })
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public String addUser(UserPO user) {
//		if (user != null) {
//			userService.addUser(user);
//		}
//		return "success";
//	}
//
//	@ApiOperation(value = "查询用户", notes = "根据用户名查询用户信息")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String") })
//	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
//	public String getUser(@PathVariable String name) {
//		return name;
//	}
//
//	@RequestMapping(value = "/alluser", method = RequestMethod.GET)
//	public List<UserPO> findAllUser() {
//		return userService.findAllUser();
//	}


}
