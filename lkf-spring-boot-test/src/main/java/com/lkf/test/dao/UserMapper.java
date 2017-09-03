package com.lkf.test.dao;




import java.util.List;


public interface UserMapper {

    void addUser(UserPO user);
    


    List<UserPO> getAllUser();

}