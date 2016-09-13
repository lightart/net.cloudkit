//package net.cloudkit.phecda.interfaces;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice(annotations = RestController.class)
//public class ExceptionHandlerAdvice {
//
//    /**
//     * Handle exceptions thrown by handlers.
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public Exception exception(Exception exception, WebRequest request) {
//        return new Exception(Throwables.getRootCause(exception).getMessage());
//    }
//}
