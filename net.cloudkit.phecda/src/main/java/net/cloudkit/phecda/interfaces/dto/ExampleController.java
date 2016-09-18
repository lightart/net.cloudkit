package net.cloudkit.phecda.interfaces.dto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//curl -i -X POST -H "Content-Type: application/xml" \
//    -d "<root><element>value</element></root>" \
//    http://localhost:8080/springmvc/test
//
//curl -i -X POST -H "Content-Type: application/json" \
//-d '{ "root": { "element": "value" } }' \
//http://localhost:8080/springmvc/test
@Controller
public class ExampleController {

    /**
     * @param requestBody
     * @return
     */
    @RequestMapping(
        value = "/test",
        method = RequestMethod.POST,
        consumes = "application/xml"
    )
    public ResponseEntity<String> processXml(@RequestBody String requestBody) {
        return new ResponseEntity<>(
            "Handled application/xml request. Request body was: " + requestBody,
            new HttpHeaders(),
            HttpStatus.OK);
    }

    /**
     * @param requestBody
     * @return
     */
    @RequestMapping(
        value = "/test",
        method = RequestMethod.POST,
        consumes = "application/json"
    )
    public ResponseEntity<String> processJson(@RequestBody String requestBody) {
        return new ResponseEntity<>(
            "Handled application/json request. Request body was: " + requestBody,
            new HttpHeaders(),
            HttpStatus.OK);
    }
}
