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
public class TestUserDAOUpdate {
    
    public static void main (String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        // the user details will be taken from the Update User Profile Page
        User u = new User();
        u.setUserId(2);
        u.setName("Amit Sinha");
        u.setEmail("amit@outlook.com");
        u.setPhone("1234567890");
        u.setAddress("Mumbai, MH");
        u.setRole(1); // Admin Role
        u.setLoginStatus(1); // Active
        
        userDAO.update(u);
        
        System.out.println("----------Data Updated----------");
    }
    
}
