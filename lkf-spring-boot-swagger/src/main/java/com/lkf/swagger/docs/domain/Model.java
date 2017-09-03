package com.lkf.swagger.docs.domain;

import java.util.Map;

public interface Model {
    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Map<String, Object> getProperties();

    void setProperties(Map<String, Object> properties);

    Object getExample();

    void setExample(Object example);

    ExternalDocs getExternalDocs();

    String getReference();

    void setReference(String reference);

    Object clone();

    Map<String, Object> getVendorExtensions();
}
