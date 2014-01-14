package com.lantushenko.experimental.servicesscope.dao;

public interface AbstractEntity {

    Long getId();

    void setId(Long id);

    /**
     * Returns false if the object has ID. In general it means 
     * that object is retrieved from database  
     */
    boolean isTransient();

}