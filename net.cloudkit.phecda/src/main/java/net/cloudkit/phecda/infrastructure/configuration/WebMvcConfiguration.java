package net.cloudkit.phecda.infrastructure.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// WebMvcConfigurationSupport WebMvcConfigurerAdapter
@EnableWebMvc
@ComponentScan(basePackageClasses = { WebMvcConfiguration.class })
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
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
