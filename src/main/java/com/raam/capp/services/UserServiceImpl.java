package com.raam.capp.services;

import com.raam.capp.dao.BaseDAO;
import com.raam.capp.dao.UserDAO;
import com.raam.capp.domain.User;
import com.raam.capp.exception.UserBlockedException;
import com.raam.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raam
 */

@Service
public class UserServiceImpl extends BaseDAO implements UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Override
    public void register(User u) {
        userDAO.save(u);
    }

    @Override
    public User login(String loginName, String password) throws UserBlockedException {
        String sql = "SELECT userId, name, email, phone, address, role, loginStatus, loginName"
                + " FROM user WHERE loginName=:ln AND password=:pw";
        
        Map m = new HashMap<>();
        m.put("ln", loginName);
        m.put("pw", password);
        
        try {
            User u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            
            if (u.getLoginStatus().equals(UserService.LOGIN_STATUS_FAILED)) {
                throw new UserBlockedException("Your account has been blocked. Please contact Administrator!");
            }
            else {
            return u;
            }
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        return userDAO.findByProperty("role", UserService.ROLE_USER);
    }

    @Override
    public void changeLoginStatus(Integer userId, Integer loginStatus) {
        String sql = "UPDATE user SET loginStatus=:loginStatus WHERE userId=:userId";
        
        Map m = new HashMap<>();
        m.put("userId", userId);
        m.put("loginStatus", loginStatus);
        
        getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public Boolean doesUsernameExist(String username) {
        String sql = "SELECT count(loginName) FROM user WHERE loginName=?";
        Integer count = getJdbcTemplate().queryForObject(sql, new String[]{username}, Integer.class);
        
        if (count.equals(1)) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
