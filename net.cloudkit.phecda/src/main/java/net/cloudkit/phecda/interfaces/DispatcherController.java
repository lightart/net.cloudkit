package net.cloudkit.phecda.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ServiceController.java
 */
@Controller
public class DispatcherController {

    @RequestMapping(
        value = {"/services", "/services.json"},
        method = {RequestMethod.GET, RequestMethod.POST},
        produces = "application/json; charset=UTF-8",
        consumes = "application/json; charset=UTF-8"
    )
    @ResponseBody
    public Map<String, Object> services(@RequestBody String requestData) {

        /*
        ResponseEntity<String>
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(
            "Handled application/json request. Request body was: " + requestBody,
            headers, // new HttpHeaders(),
            HttpStatus.OK);
        */

        Map<String, Object> responseData = new LinkedHashMap<>();
        try {
            // ObjectMapper objectMapper = new ObjectMapper();
            // objectMapper.readValue(requestData, RequestData.class);
        } catch (Exception e) {

        }
        responseData.put("status", false);
        return responseData;
    }

//    @RequestMapping({"/", "/home", "index"})
//    public String home() {
//        return "/index";
//    }

//    @RequestMapping(value = "saveUser", method = {RequestMethod.POST})
//    @ResponseBody
//    public void saveUser(@RequestBody List<User> users) {
//        userService.batchSave(users);
//    }

}
