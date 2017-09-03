package com.lkf.swagger.docs.controller;


import com.google.common.collect.Lists;
import com.lkf.swagger.docs.domain.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project: zhcore
 *
 * @Comments
 * @Author Zhong Han
 * @Created Date 2017/4/6
 */

@RestController
@RequestMapping(path = "/api")
public class DocumentController {
    private final MongoTemplate mongoTemplate;
    private final String swagger_api_doc="swagger_api_doc";

    @Autowired
    public DocumentController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Swagger> getSwaggers() {
        return mongoTemplate.findAll(Swagger.class,swagger_api_doc);
    }

    @RequestMapping(path = "/{basePath}", method = RequestMethod.GET)
    public Swagger getByBasePath(@PathVariable String basePath) {

        Query query = new Query();
        List<Criteria> criteriaList = Lists.newArrayList();
//        query.addCriteria(new Criteria());
//        query.with(new Sort(Sort.Direction.DESC, "notifyTime"));
        if (!basePath.startsWith("/")) {
            basePath = "/" + basePath;
        }

        criteriaList.add(new Criteria().andOperator(Criteria.where("basePath").is(basePath)));
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        Swagger swagger=new Swagger();
        try{
             String swaggerJson;
            swagger = (Swagger) mongoTemplate.findOne(query,Swagger.class, swagger_api_doc);
        }
        catch (Exception e){
            System.err.print(e);
        }

        return swagger;
    }
}
