##邮件服务插件使用
#### 1.引用pom
```
<dependency>
  <groupId>com.lkf</groupId>
  <artifactId>lkf-spring-boot-starter-email</artifactId>
  <version>${project.parent.version}</version>
</dependency>
```
#### 2.添加配置文件
```
##email
spring.mail.host=smtp.163.com
spring.mail.username=test@163.com
spring.mail.password=这里不是密码是授权码
spring.mail.to=69856@qq.com,admin@163.com 默认接收人，多个用半角逗号隔开
## Freemarker 配置
## 文件配置路径
spring.freemarker.template-loader-path=classpath:/templates/
spring.freemarker.cache=false
spring.freemarker.charset=UTF-8
spring.freemarker.check-template-location=true
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=true
spring.freemarker.expose-session-attributes=true
spring.freemarker.request-context-attribute=request
spring.freemarker.suffix=.ftl
```
#### 3.注入EmailService
 ```
    @Autowired
     private EmailService emailService;
 
     /**
      * @method-name: 【单元测试】templateEmailTest
      * @description: 模板邮件
      * @author: lkf
      * @date: 2017/5/18 10:33
      * @version V1.0
      **/
     @Test
     public void templateEmailTest() {
         Map<String, Object> model = new HashMap<String, Object>();
         model.put("name", "liukaifeng");
         emailService.sendTemplateMail("邮件主题", model, null, "hello.ftl");
     }
 
     /**
      * @method-name: 【单元测试】sendSimpleMailTest
      * @description: 普通邮件
      * @author: lkf
      * @date: 2017/5/18 10:32
      * @version V1.0
      **/
     @Test
     public void sendSimpleMailTest() {
         emailService.sendSimpleMail("普通邮件主题", "这是一封普通邮件");
     }
 
     /**
      * @method-name: 【单元测试】sendAttachmentsMailTest
      * @description: 附件邮件
      * @author: lkf
      * @date: 2017/5/18 10:40
      * @version V1.0
      **/
     @Test
     public void sendAttachmentsMailTest() {
         List<Pair<String, File>> attachments = Lists.newArrayList();
         emailService.sendAttachmentsMail("附件邮件主题", "这是一封附件邮件", "内容", attachments);
     }
 
 ```
 #### 注：不指定接收人时，默认从配置文件中读取

