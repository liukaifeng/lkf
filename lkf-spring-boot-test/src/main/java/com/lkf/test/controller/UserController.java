package com.lkf.test.controller;


import com.lkf.common.model.page.PageOut;
import com.lkf.common.model.response.ResponseResult;

import com.lkf.test.dao.UserPO;
import com.lkf.test.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;





    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")})
    @ApiOperation(value = "添加用户信息", notes = "添加用户信息",httpMethod = "POST")
    public ResponseEntity<String> addUser(RequestEntity<UserPO> userPORequestEntity) throws Exception {
        if (userPORequestEntity.getBody() != null) {
            userService.addUser(userPORequestEntity.getBody());
        }
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "查询用户", notes = "根据用户名查询用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")})
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public String getUser(@PathVariable String name) {
        return name;
    }

    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public ResponseResult<PageOut> findAllUser() {
        return userService.findAllUser();
    }


}
