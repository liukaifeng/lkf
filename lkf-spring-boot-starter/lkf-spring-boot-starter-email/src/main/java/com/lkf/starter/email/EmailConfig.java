package com.lkf.starter.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @package: com.tcsl.slyun.starter.email
 * @project-name: sly
 * @description: email配置信息
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-17 15-55
 */
@Component
public class EmailConfig {
    /**
     * 发件人邮箱
     */
    @Value("${spring.mail.username}")
    private String emailFrom;
    /**
     * 收件人邮箱
     */
    @Value("${spring.mail.to}")
    private String emailTo;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
}
