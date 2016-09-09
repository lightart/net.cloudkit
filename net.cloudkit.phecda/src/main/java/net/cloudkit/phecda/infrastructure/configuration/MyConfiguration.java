package net.cloudkit.phecda.infrastructure.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// WebMvcConfigurationSupport WebMvcConfigurerAdapter
@EnableWebMvc
@ComponentScan(basePackageClasses = { MyConfiguration.class })
public class MyConfiguration extends WebMvcConfigurationSupport {
//
//    @Override
//    public void addFormatters(FormatterRegistry formatterRegistry) {
//        formatterRegistry.addConverter(new MyConverter());
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MyHttpMessageConverter());
//    }

    // More overridden methods ...
}
