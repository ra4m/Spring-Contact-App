package com.raam.capp.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 *
 * @author Raam
 */

// Note: Do not use @Repository or @Service or @Component annotation
abstract public class BaseDAO extends NamedParameterJdbcDaoSupport {
    
    @Autowired
    public void setDataSource2(DataSource ds) {
        super.setDataSource(ds);
    }
    
}
