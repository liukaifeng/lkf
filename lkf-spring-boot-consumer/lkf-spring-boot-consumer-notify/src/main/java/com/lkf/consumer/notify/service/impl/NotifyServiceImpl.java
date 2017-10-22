package com.lkf.consumer.notify.service.impl;


import com.lkf.common.model.response.ResponseResult;
import com.lkf.consumer.notify.dao.impl.NotifyDaoImpl;
import com.lkf.consumer.notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyDaoImpl notifyDaoImpl;


    @Override
    public ResponseResult<String> saveNotify(String notifyInfo) {
        Integer result= notifyDaoImpl.saveNotify(notifyInfo);
        if(result!=1)
            return ResponseResult.failure();
        return ResponseResult.success();
    }

}
