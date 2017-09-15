package com.raam.capp.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Raam
 */

@Controller
public class TestController {
    
    @RequestMapping("/test/helloWorld")
    public String helloWorld() {
        return "hello"; // -> /WEB-INF/view/hello.jsp
    }
    
    @RequestMapping("/test/ajax_test")
    public String testPage() {
        return "test"; // -> /WEB-INF/view/hello.jsp
    }
    
    @RequestMapping("/test/get_time")
    @ResponseBody
    public String getServerTime() {
        Date d = new Date();
        return d.toString();
    }
    
}
