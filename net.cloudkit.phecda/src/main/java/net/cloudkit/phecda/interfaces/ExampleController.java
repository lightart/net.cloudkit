//package net.cloudkit.phecda.interfaces.dto;
//
//import net.cloudkit.phecda.domain.model.shared.City;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////curl -i -X POST -H "Content-Type: application/xml" \
////    -d "<root><element>value</element></root>" \
////    http://localhost:8080/springmvc/test
////
////curl -i -X POST -H "Content-Type: application/json" \
////-d '{ "root": { "element": "value" } }' \
////http://localhost:8080/springmvc/test
//@RestController
////@RequestMapping("/service")
////@Controller
//public class ExampleController {
//
//    /**
//     * @param requestBody
//     * @return
//     */
//    @RequestMapping(
//        value = "/test",
//        method = RequestMethod.POST,
//        consumes = "application/xml"
//    )
//    public ResponseEntity<String> processXml(@RequestBody String requestBody) {
//        return new ResponseEntity<>(
//            "Handled application/xml request. Request body was: " + requestBody,
//            new HttpHeaders(),
//            HttpStatus.OK);
//    }
//
//    /**
//     * @param requestBody
//     * @return
//     */
//    @RequestMapping(
//        value = "/test",
//        method = RequestMethod.POST,
//        consumes = "application/json"
//    )
//    public ResponseEntity<String> processJson(@RequestBody String requestBody) {
//        return new ResponseEntity<>(
//            "Handled application/json request. Request body was: " + requestBody,
//            new HttpHeaders(),
//            HttpStatus.OK);
//    }
//
//
////    public static HttpHeaders httpHeaderExcelFileAttachment(final String fileName,
////                                                            final int fileSize) {
////        String encodedFileName = fileName.replace('"', ' ').replace(' ', '_');
////
////        HttpHeaders responseHeaders = new HttpHeaders();
////        responseHeaders.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
////        responseHeaders.setContentLength(fileSize);
////        responseHeaders.set("Content-Disposition", "attachment");
////        responseHeaders.add("Content-Disposition", "filename=\"" + encodedFileName + '\"');
////        return responseHeaders;
////    }
//
//    // @PathVariable String version, @PathVariable String extension
//    // params="myParam=myValue"
//    // headers="Referer=http://www.simple.com/"
//    // , RedirectAttributes redirectAttributes
//    // ResponseEntity<Person>
//    // @PathVariable
//    // new ResponseEntity<byte[]>(file.getDataArray(), responseHeaders, HttpStatus.OK);
//    // HttpHeaders responseHeaders = httpHeaderExcelFileAttachment(fileName, datei.getDaten().length);
//    @RequestMapping(
//        value ={"/service", "/service.json"},
//        method = {RequestMethod.GET, RequestMethod.POST},
//        produces = "application/json",
//        consumes = "application/json"
//    )
//    //@ResponseBody
//    public Map<String, String> service(HttpServletRequest request,
//                                       HttpServletResponse response, Model model){
//        // InternalResourceViewResolver.FORWARD_URL_PREFIX | return "forward:/xxx";
//        // InternalResourceViewResolver.REDIRECT_URL_PREFIX | return "redirect:/xxx"
//        // ModelAndView modelAndView = new ModelAndView();
//        // modelAndView.setViewName("/index");
//        // modelAndView.addObject("date", new Date());
//
//        // model.addAttribute("status", "ok");
//
//        // City city = new City("BeiJing", "China");
//
//        Map<String, String> dataMap = new HashMap<>();
//        dataMap.put("status", "ok");
//        return dataMap;
//    }
//
//    /**
//     * HTTP 404 Resource not found
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping({"/resource_not_found"})
//    public String resourceNotFound(HttpServletRequest request) {
//
//        /*
//        String webAppRoot = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
//        System.setProperty("webapp.root", webAppRoot);
//        File tpl = new File(webAppRoot + File.separator + "default.ftl");
//        if (!tpl.exists()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("data", "123456");
//            FreemarkerHelper.process("default.ftl", map, webAppRoot + File.separator + "default.ftl");
//        }
//        */
//
//        return "/resource_not_found";
//    }
//
////    /**
////     * Error
////     *
////     * @return
////     */
////    @RequestMapping({"/error"})
////    public String error() {
////        return "/error";
////    }
//
//    /**
//     * test
//     *
//     * @param name
//     * @param offset
//     * @param limit
//     * @return
//     */
//    // , @RequestBody String url
//    @RequestMapping(value = "/test", produces = "application/json")
//    //@ResponseBody
//    public Model test(@RequestParam("name") String name, @RequestParam int offset, @RequestParam int limit, Model model) {
//
//        model.addAttribute("success", false);
//        try {
//            List<String> data = new ArrayList<>();
//            data.add("123456");
//            data.add("456789");
//
//            model.addAttribute("data", data);
//            model.addAttribute("success", true);
//        } catch (Exception e) {
//            // logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return model;
//    }
//
//    @RequestMapping(value = "/res")
//    @ResponseBody
//    public ModelAndView res() {
//        ModelAndView modelAndView = new ModelAndView();
//        City city = new City("BeiJing", "China");
//        modelAndView.addObject("user", city);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/find", produces="application/json")
//    //@ResponseBody
//    public Model find(Model model) {
//        City city = new City("BeiJing", "China");
//        model.addAttribute("user", city);
//        return model;
//    }
//
//    @RequestMapping(value = "get_user_list", method = RequestMethod.GET)
//    //@ResponseBody
//    public Map<String, Object> getUserList() {
//
//        Map<String, Object> modelMap = new HashMap<>(3);
//        modelMap.put("total", "1");
//        modelMap.put("data","hello");
//        modelMap.put("success", "true");
//        return modelMap;
//    }
//
//    @RequestMapping({"/", "/home", "index"})
//    public String home() {
//        return "/index";
//    }
//
//    @RequestMapping({"/map"})
//    public ModelMap map() {
//        return new ModelMap("status", "ok");
//    }
//
//    @RequestMapping("/something")
//    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
//        String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
//        byte[] requestBody = requestEntity.getBody();
//
//        // do something with request header and body
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("MyResponseHeader", "MyValue");
//        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/content", produces="application/json")
//    @ResponseBody
//    public ModelAndView getContent() {
//        ModelAndView mav = new ModelAndView();
//        // mav.setViewName("content");
//        mav.addObject("sampleContentList", "test");
//        // return new ModelAndView(new MappingJackson2JsonView(), new HashMap<String, String>());
//        return mav;
//    }
//}
