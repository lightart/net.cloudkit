package net.cloudkit.phecda;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;

public class ServletInitializer extends SpringBootServletInitializer {

//    @Bean
//    public Servlet dispatcherServlet() {
//        return new GenericServlet() {
//            @Override
//            public void service(ServletRequest req, ServletResponse res)
//                throws ServletException, IOException {
//                res.setContentType("text/plain");
//                res.getWriter().append("Hello World");
//            }
//        };
//    }

//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.setName("default");
//        registration.addUrlMappings(".html");
//        return registration;
//    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhecdaApplication.class);
	}

}
