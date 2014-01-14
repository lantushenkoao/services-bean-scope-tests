package com.lantushenko.experimental.servicesscope.repositories;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *  All repository transactions should be defined on services layer 
 */
@Transactional(propagation=Propagation.MANDATORY)
public interface AbstractRepository {

}
