package com.lkf.example.biz.dao;


import com.lkf.example.model.entity.UserPO;

import java.util.List;


public interface UserDao {

    void addUser(UserPO user);
    
    List<UserPO> findAllUser();

}