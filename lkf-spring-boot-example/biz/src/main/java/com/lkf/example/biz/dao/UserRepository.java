package com.lkf.example.biz.dao;

import com.lkf.example.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kaifeng on 2017/5/9.
 */
public interface UserRepository extends MongoRepository<User, Long> {
	User findByUsername(String username);
}
