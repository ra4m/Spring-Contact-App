package com.raam.capp.test;

import com.raam.capp.config.SpringRootConfig;
import com.raam.capp.dao.UserDAO;
import com.raam.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Raam
 */
public class TestUserDAODelete {
    
    public static void main (String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        userDAO.delete(2);
        
        System.out.println("----------Data Deleted----------");
    }
    
}
