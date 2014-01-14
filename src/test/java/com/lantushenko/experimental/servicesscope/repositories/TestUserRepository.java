package com.lantushenko.experimental.servicesscope.repositories;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lantushenko.experimental.servicesscope.dao.impl.UserImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml", "/settings.xml"})
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testInsertUser() throws Exception{
        UserImpl u = new UserImpl();
        u.setFullName("Test user");
        u.setLogin("login");
        u.setPassword("ab14b");
        userRepository.insert(u);
        assertNotNull(u.getId());
    }
    
}
