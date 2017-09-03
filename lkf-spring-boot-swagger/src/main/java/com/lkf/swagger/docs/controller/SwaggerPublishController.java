package com.lkf.swagger.docs.controller;


import com.lkf.swagger.docs.domain.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/swagger")
public class SwaggerPublishController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SwaggerPublishController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(path = "/publish", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public void publish(@RequestBody Swagger swagger) {

//        com.lkf.swagger.docs.domain.Swagger original = swaggerDocRepo.findByBasePath(swagger.getBasePath());
//
//        // MongoDB docs cannot contain "$" when update, so we must delete old one and save the new one.
//        swaggerDocRepo.delete(original.getId());
        mongoTemplate.save(swagger,"swagger_api_doc");

    }
}
