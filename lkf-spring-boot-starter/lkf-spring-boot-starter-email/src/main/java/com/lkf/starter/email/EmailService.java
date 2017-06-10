package com.lkf.starter.email;

import javafx.util.Pair;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @package: com.tcsl.slyun.starter.email
 * @project-name: sly
 * @description: 邮件服务接口
 * 1. 发送简单邮件
 * 2. 发送带附件简单邮件
 * 3. 发送模板邮件。
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-17 15-58
 */
public interface EmailService {

    /**
     * @param sendTo  收件人地址
     * @param title   邮件标题
     * @param content 邮件内容
     * @method-name: sendSimpleMail
     * @description: 发送简单邮件
     * @author: 刘凯峰
     * @date: 2017/5/17 16:01
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendSimpleMail(String sendTo, String title, String content);

    /**
     * @param title   邮件标题
     * @param content 邮件内容
     * @method-name: sendSimpleMail
     * @description: 发送简单邮件, 收件人默认从配置文件中获取
     * @author: 刘凯峰
     * @date: 2017/5/17 16:01
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendSimpleMail(String title, String content);

    /**
     * @param sendTo              收件人地址
     * @param title               邮件标题
     * @param content             邮件内容
     * @param attachments<文件名，附件> 附件列表
     * @method-name:
     * @description: 发送带附件简单邮件
     * @author: 刘凯峰
     * @date: 2017/5/17 16:01
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments);

    /**
     * @param title               邮件标题
     * @param content             邮件内容
     * @param attachments<文件名，附件> 附件列表
     * @method-name:
     * @description: 发送带附件简单邮件，收件人默认从配置文件中获取
     * @author: 刘凯峰
     * @date: 2017/5/17 16:01
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendAttachmentsMail(String title, String content, List<Pair<String, File>> attachments);

    /**
     * @param sendTo              收件人地址
     * @param title               邮件标题
     * @param content<key,内容>     邮件内容
     * @param attachments<文件名,附件> 附件列表
     * @param templateFullName    模板全名带后缀
     * @method-name: sendTemplateMail
     * @description: 发送模板邮件
     * @author: 刘凯峰
     * @date: 2017/5/17 16:03
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>> attachments, String templateFullName);

    /**
     * @param title               邮件标题
     * @param content<key,内容>     邮件内容
     * @param attachments<文件名,附件> 附件列表
     * @param templateFullName    模板全名带后缀
     * @method-name: sendTemplateMail
     * @description: 发送模板邮件，收件人默认从配置文件中获取
     * @author: 刘凯峰
     * @date: 2017/5/17 16:03
     * @return:
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    String sendTemplateMail(String title, Map<String, Object> content, List<Pair<String, File>> attachments, String templateFullName);

}
