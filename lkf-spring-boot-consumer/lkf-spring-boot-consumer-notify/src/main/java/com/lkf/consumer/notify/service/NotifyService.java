package com.lkf.consumer.notify.service;


import com.lkf.common.model.response.ResponseResult;


public interface NotifyService {


    ResponseResult<String> saveNotify(String notifyInfo);
}
