package com.raam.capp.dao;

import com.raam.capp.domain.User;
import com.raam.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raam
 */

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public void save(User u) {
        String sql = "INSERT INTO user (`name`, `email`, `phone`, `address`, `loginName`, `password`, `role`, `loginStatus`)" + 
                     " VALUES(:name, :email, :phone, :address, :loginName, :password, :role, :loginStatus)";
        
        Map m = new HashMap();
        m.put("name", u.getName());
        m.put("email", u.getEmail());
        m.put("phone", u.getPhone());
        m.put("address", u.getAddress());
        m.put("loginName", u.getLoginName());
        m.put("password", u.getPassword());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
              
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
        
        Integer userId = kh.getKey().intValue();
        u.setUserId(userId);
    }

    @Override
    public void update(User u) {
        String sql = "UPDATE user SET name=:name, email=:email, phone=:phone, address=:address, role=:role, loginStatus=:loginStatus WHERE userId=:userId";

        Map m = new HashMap();
        m.put("name", u.getName());
        m.put("email", u.getEmail());
        m.put("phone", u.getPhone());
        m.put("address", u.getAddress());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
        m.put("userId", u.getUserId());
        
        super.getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public void delete(User u) {
        this.delete(u.getUserId());
    }

    @Override
    public void delete(Integer userId) {
        String sql = "DELETE FROM user WHERE userID=?";
        getJdbcTemplate().update(sql, userId);
    }

    @Override
    public User findById(Integer userId) {
        String sql = "SELECT `userId`, `name`, `email`, `phone`, `address`, `loginName`, `role`, `loginStatus` FROM user WHERE userId=?";   
        User u = super.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), userId);
        return u;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT `userId`, `name`, `email`, `phone`, `address`, `loginName`, `role`, `loginStatus` FROM user";   
        List<User> users = super.getJdbcTemplate().query(sql, new UserRowMapper());
        return users;
    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        String sql = "SELECT `userId`, `name`, `email`, `phone`, `address`, `loginName`, `role`, `loginStatus` FROM user WHERE " + propName + "=?";   
        List<User> users = super.getJdbcTemplate().query(sql, new UserRowMapper(), propValue);
        return users;
    }
    
}
