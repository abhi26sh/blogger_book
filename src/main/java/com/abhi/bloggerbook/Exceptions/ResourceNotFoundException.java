package com.abhi.bloggerbook.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(long fieldValue) {
        this.fieldValue = fieldValue;
    }

    String resourceName;
    String fieldName;
    long fieldValue;
    public ResourceNotFoundException(String resource, String id, Integer resource_id) {

        super(String.format("%s not found with with %s : %s",resource,id,resource_id));
        resourceName=resource;
        fieldName=id;
        fieldValue=resource_id;

    }
}
