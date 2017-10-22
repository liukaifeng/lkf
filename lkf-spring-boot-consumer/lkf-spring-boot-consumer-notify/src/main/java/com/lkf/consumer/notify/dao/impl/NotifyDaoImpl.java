package com.lkf.consumer.notify.dao.impl;

import com.lkf.consumer.notify.dao.NotifyDao;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class NotifyDaoImpl implements NotifyDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer saveNotify(String notifyInfo) {
        BasicDBObject bson = (BasicDBObject) JSON.parse(notifyInfo);
        bson.replace("notifyTime",new Date((Long) bson.get("notifyTime")));
        try{
            mongoTemplate.getCollection("notify").save(bson);
            return 1;
        }
        catch (Exception e){
            return -1;
        }
    }
}
