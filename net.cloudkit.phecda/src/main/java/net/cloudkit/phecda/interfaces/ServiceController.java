package net.cloudkit.phecda.interfaces;

import net.cloudkit.phecda.domain.model.shared.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ServiceController.java
 */
// @RestController
// @RequestMapping("/service")
@Controller
public class ServiceController {

    // @PathVariable String version, @PathVariable String extension
    // params="myParam=myValue"
    // headers="Referer=http://www.simple.com/"
    // , RedirectAttributes redirectAttributes
    @RequestMapping(
        value ={"/service", "/service.json"},
        method = {RequestMethod.GET, RequestMethod.POST},
        produces = "application/json"
        // , consumes="application/json"
    )
    @ResponseBody
    public Model service(HttpServletRequest request, HttpServletResponse response, Model model){
        // InternalResourceViewResolver.FORWARD_URL_PREFIX | return "forward:/xxx";
        // InternalResourceViewResolver.REDIRECT_URL_PREFIX | return "redirect:/xxx"
        // ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("status", "ok");

        // City city = new City("BeiJing", "China");
        return model;
    }

    /**
     * HTTP 404 Resource not found
     *
     * @param request
     * @return
     */
    @RequestMapping({"/resource_not_found"})
    public String resourceNotFound(HttpServletRequest request) {

        /*
        String webAppRoot = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        System.setProperty("webapp.root", webAppRoot);
        File tpl = new File(webAppRoot + File.separator + "default.ftl");
        if (!tpl.exists()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", "123456");
            FreemarkerHelper.process("default.ftl", map, webAppRoot + File.separator + "default.ftl");
        }
        */

        return "/resource_not_found";
    }

//    /**
//     * Error
//     *
//     * @return
//     */
//    @RequestMapping({"/error"})
//    public String error() {
//        return "/error";
//    }

    /**
     * test
     *
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    // , @RequestBody String url
    @RequestMapping(value = "/test")
    @ResponseBody
    public Model test(@RequestParam("name") String name, @RequestParam int offset, @RequestParam int limit, Model model) {

        model.addAttribute("success", false);
        try {
            List<String> data = new ArrayList<String>();
            data.add("123456");
            data.add("456789");

            model.addAttribute("data", data);
            model.addAttribute("success", true);
        } catch (Exception e) {
            // logger.error(e.getMessage(), e);
        }
        return model;
    }

    @RequestMapping(value = "/res")
    @ResponseBody
    public ModelAndView res() {
        ModelAndView modelAndView = new ModelAndView();
        City city = new City("BeiJing", "China");
        modelAndView.addObject("user", city);
        return modelAndView;
    }

    @RequestMapping(value = "/find")
    @ResponseBody
    public Model find(Model model) {
        City city = new City("BeiJing", "China");
        model.addAttribute("user", city);
        return model;
    }

    @RequestMapping(value = "get_user_list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList() {

        Map<String, Object> modelMap = new HashMap<>(3);
        modelMap.put("total", "1");
        modelMap.put("data","hello");
        modelMap.put("success", "true");
        return modelMap;
    }

    @RequestMapping({"/", "/home", "index"})
    public String home() {
        return "/index";
    }
}
