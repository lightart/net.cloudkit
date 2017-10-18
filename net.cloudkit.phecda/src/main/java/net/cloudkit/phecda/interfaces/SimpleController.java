package net.cloudkit.phecda.interfaces;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SimpleController.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
@RestController
public class SimpleController {

    @RequestMapping(value ="/hello", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Model hello(Model model){
        model.addAttribute("hello", "123456");
        return model;
    }

}
