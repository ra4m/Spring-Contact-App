package com.raam.capp.test;

import com.raam.capp.config.SpringRootConfig;
import com.raam.capp.domain.User;
import com.raam.capp.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Raam
 */
public class TestUserServiceRegister {
    
    public static void main (String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        
        // the user details will be taken from the User-Reg-Form
        User u = new User();
        u.setName("Nithin");
        u.setEmail("nithin@gmail.com");
        u.setPhone("9867565319");
        u.setAddress("Mumbai");
        u.setLoginName("nithin");
        u.setPassword("nithin876");
        u.setRole(UserService.ROLE_ADMIN); // Admin Role
        u.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE); // Active
        
        userService.register(u);
        
        System.out.println("----------User Registered Successfully----------");
    }
    
}
