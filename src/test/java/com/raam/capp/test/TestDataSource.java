package com.raam.capp.test;

import com.raam.capp.config.SpringRootConfig;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Raam
 */
public class TestDataSource {
    
    public static void main(String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jt = new JdbcTemplate(ds);
        
        String sql = "INSERT INTO user(`name`, `email`, `phone`, `address`, `loginName`, `password`) VALUES(?,?,?,?,?,?)";
        Object[] parameters = new Object[]{"Vikram", "vikram@gmail.com", "1234567890", "Shimla", "v", "v123"};
        jt.update(sql, parameters);
        
        System.out.println("-------------SQL COMPLETED----------------");
    }
    
    
}
