package com.raam.capp.services;

import com.raam.capp.domain.User;
import com.raam.capp.exception.UserBlockedException;
import java.util.List;

/**
 * This interface specifies all the business operations for the User entity
 * @author Raam
 */
public interface UserService {
    
    public static final Integer LOGIN_STATUS_ACTIVE = 1;
    public static final Integer LOGIN_STATUS_FAILED = 2;

    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_USER = 2;
    
    
    /**
     * This method handles the user registration task.
     * @param u 
     */
    public void register(User u);
    
    
    /**
     * The method handles login operation(authentication) using the given credentials,
     * it returns User object when success and NULL when failed
     * When user account is blocked, an exception will be thrown by this method
     * @param loginName
     * @param password
     * @return 
     * @throws com.raam.capp.exception.UserBlockedException When user account is blocked
     */
    public User login(String loginName, String password) throws UserBlockedException;
    
    
    /**
     * Call this method to get list of all registered users
     * @return 
     */
    public List<User> getUserList();
    
    
    /**
     * This method changes the User's login status for details passed as arguments
     * @param userId
     * @param loginStatus 
     */
    public void changeLoginStatus(Integer userId, Integer loginStatus);
    
    
    /**
     * Check the availability of username
     * @param username
     * @return 
     */
    public Boolean doesUsernameExist(String username);
    
}
