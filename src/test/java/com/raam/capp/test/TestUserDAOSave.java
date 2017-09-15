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
public class TestUserDAOSave {
    
    public static void main (String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        // the user details will be taken from the User-Reg-Form
        User u = new User();
        u.setName("Amit");
        u.setEmail("amit@gmail.com");
        u.setPhone("1234567890");
        u.setAddress("Mumbai");
        u.setLoginName("Amit");
        u.setPassword("Amit123");
        u.setRole(1); // Admin Role
        u.setLoginStatus(1); // Active
        
        userDAO.save(u);
        
        System.out.println("----------Data Saved----------");
    }
    
}
