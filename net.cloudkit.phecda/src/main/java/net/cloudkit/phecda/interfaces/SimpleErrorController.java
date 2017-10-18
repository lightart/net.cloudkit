package net.cloudkit.phecda.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * SimpleErrorController
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
@RestController
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    private final static String ERROR_PATH = "/error";

    /**
     * @param errorAttributes
     */
    @Autowired
    public SimpleErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

//    /**
//     * Supports the HTML Error View
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH, produces = "text/html")
//    public ModelAndView errorHtml(HttpServletRequest request) {
//        return new ModelAndView("greeting", getErrorAttributes(request, false));
//    }

//    /**
//     * Supports other formats like JSON, XML
//     * @param request
//     * @return
//     */
//    //@RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(body, status);
//    }

    @RequestMapping
    public Map<String, Object> error(HttpServletRequest aRequest) {
        Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
        String trace = (String) body.get("trace");
        if (trace != null) {
            String[] lines = trace.split("\n\t");
            body.put("trace", lines);
        }
        return body;
    }

    /**
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        String url = request.getRequestURL().toString();
        Map<String, Object> map = this.errorAttributes.getErrorAttributes(requestAttributes,includeStackTrace);
        map.put("url", url);
        return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    /**
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
