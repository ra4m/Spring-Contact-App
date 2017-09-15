package com.raam.capp.controller;

import com.raam.capp.command.LoginCommand;
import com.raam.capp.command.UserCommand;
import com.raam.capp.domain.User;
import com.raam.capp.exception.UserBlockedException;
import com.raam.capp.services.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Raam
 */

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    
    @RequestMapping(value = {"/", "/index"})
    public String index(Model m) {
        m.addAttribute("command", new LoginCommand());
        return "index";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session) {
        
        try {
            User loggedInUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            
            if (loggedInUser == null) {
                // add error message and go back to login page
                m.addAttribute("err", "Login Failed! Enter valid credentials.");
                return "index";
            }
            else {
                // SUCCESS Part
                // check the role and re-direct to the appropriate dashboard
                if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:admin/dashboard";
                }
                else if (loggedInUser.getRole().equals(UserService.ROLE_USER)) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:user/dashboard";
                }
                else {
                    // add error message and go back to login page
                    m.addAttribute("err", "Invalid User ROLE");
                    return "index"; 
                }
            }
            
        } 
        catch (UserBlockedException ex) {
            // add error message and go back to login page
            m.addAttribute("err", ex.getMessage());
            return "index";
        }
        
        }
    
    @RequestMapping(value = {"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index?act=lo";
    }
    
    @RequestMapping(value = {"/admin/users"})
    public String getUserList(Model m) {
        m.addAttribute("userList", userService.getUserList());
        return "users";
    }
    
    @RequestMapping("/reg_form")
    public String registrationForm(Model m) {
        UserCommand cmd = new UserCommand();
        m.addAttribute("command", cmd);
        return "reg_form";
    }
    
    @RequestMapping("/admin/change_status")
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
        try {
            userService.changeLoginStatus(userId, loginStatus);
            return "SUCCESS: Status changed!";
        } 
        catch (Exception e) {
            return "FAILED: Unable to change status!";
        }
        
    }
    
    @RequestMapping("/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model m) {
        
        try {
            User user = cmd.getUser();
            user.setRole(UserService.ROLE_USER);
            user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
            
            userService.register(user);
            return "redirect:index?act=reg"; //Login Page
        } 
        catch (DuplicateKeyException e) {
            m.addAttribute("err", "Username is already taken. Please try with a different username.");
            return "reg_form"; //Registration Form
        }
    }
    
    @RequestMapping(value = {"/user/dashboard"})
    public String userDashboard() {
        return "dashboard_user";
    }
    
    @RequestMapping(value = {"/admin/dashboard"})
    public String adminDashboard() {
        return "dashboard_admin";
    }
    
    @RequestMapping(value = {"/check_availability"})
    @ResponseBody
    public String checkAvailability(@RequestParam String username) {
        if (userService.doesUsernameExist(username)) {
            return "This username is already taken!";
        }
        else {
            return "Yes! You can use this username.";
        }
    }
    
    
    
    
    // Helper methods
    private void addUserInSession(User u, HttpSession session) {
        session.setAttribute("user", u);
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("role", u.getRole());
    }
    
    
    
    
}

