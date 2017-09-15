package com.raam.capp.test;

import com.raam.capp.config.SpringRootConfig;
import com.raam.capp.dao.UserDAO;
import com.raam.capp.domain.User;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Raam
 */
public class TestUserDAOFindByProperty {
    
    public static void main (String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        //List<User> users = userDAO.findByProperty("userId", 1);
        List<User> users = userDAO.findByProperty("role", 1);

        
        for (User u : users) {
            System.out.println("----------User Details----------");
            System.out.println(u.getUserId());
            System.out.println(u.getName());
            System.out.println(u.getEmail());
            System.out.println(u.getPhone());
            System.out.println(u.getAddress());
            System.out.println(u.getLoginName());
            System.out.println(u.getRole());
            System.out.println(u.getLoginStatus());
            System.out.println("\n\n\n\n");
        }
        
              
    }
    
}
