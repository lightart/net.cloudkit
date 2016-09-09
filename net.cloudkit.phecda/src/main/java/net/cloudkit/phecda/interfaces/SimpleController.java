package net.cloudkit.phecda.interfaces;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SimpleController.java
 */
@RestController
@Controller
// @EnableAutoConfiguration
public class SimpleController {

    @RequestMapping(value ="/hello", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String hello(){
        return "hello world";
    }

}
