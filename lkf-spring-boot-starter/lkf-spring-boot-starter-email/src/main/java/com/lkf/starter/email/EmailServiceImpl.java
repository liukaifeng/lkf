package com.lkf.starter.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import javafx.util.Pair;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.tcsl.slyun.starter.email
 * @project-name: sly
 * @description: 邮件服务实现
 * @author: Created by 刘凯峰
 * @create-datetime: 2017-05-17 16-01
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration configuration;

    private final String FAILURE = "FAILURE";
    private final String SUCCESS = "SUCCESS";

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
    @Override
    public String sendSimpleMail(String sendTo, String title, String content) {
        String result = FAILURE;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        if (Strings.isNullOrEmpty(sendTo))
            message.setTo(emailConfig.getEmailTo().split(","));
        else
            message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        result = SUCCESS;
        return result;
    }

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
    @Override
    public String sendSimpleMail(String title, String content) {
        return sendSimpleMail(null, title, content);
    }

    /**
     * @param sendTo      收件人地址
     * @param title       邮件标题
     * @param content     邮件内容
     * @param attachments
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
    @Override
    public String sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String result = FAILURE;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            if (Strings.isNullOrEmpty(sendTo))
                helper.setTo(emailConfig.getEmailTo().split(","));
            else
                helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);
            if (attachments != null && !attachments.isEmpty())
                for (Pair<String, File> pair : attachments) {
                    helper.addAttachment(pair.getKey(), new FileSystemResource(pair.getValue()));
                }
            mailSender.send(mimeMessage);
            result = SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @param title       邮件标题
     * @param content     邮件内容
     * @param attachments
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
    @Override
    public String sendAttachmentsMail(String title, String content, List<Pair<String, File>> attachments) {
        return sendAttachmentsMail(null, title, content, attachments);
    }

    /**
     * @param sendTo      收件人地址
     * @param title       邮件标题
     * @param content     邮件内容
     * @param attachments 附件
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
    @Override
    public String sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>> attachments, String templateFullName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String result = FAILURE;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            if (Strings.isNullOrEmpty(sendTo))
                helper.setTo(emailConfig.getEmailTo().split(","));
            else
                helper.setTo(sendTo);
            helper.setSubject(title);
            /*freeMarker template*/
            Template template = configuration.getTemplate(templateFullName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, content);
            helper.setText(text, true);
            if (attachments != null && !attachments.isEmpty())
                for (Pair<String, File> pair : attachments) {
                    helper.addAttachment(pair.getKey(), new FileSystemResource(pair.getValue()));
                }
            mailSender.send(mimeMessage);
            result = SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @param title            邮件标题
     * @param content          邮件内容
     * @param attachments      附件
     * @param templateFullName 模板全名带后缀
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
    @Override
    public String sendTemplateMail(String title, Map<String, Object> content, List<Pair<String, File>> attachments, String templateFullName) {
        return sendTemplateMail(null, title, content, attachments, templateFullName);
    }
}
