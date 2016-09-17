package net.cloudkit.phecda.infrastructure.configuration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.annotation.ModelMethodProcessor;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// WebMvcConfigurationSupport WebMvcConfigurerAdapter WebMvcAutoConfiguration
// @ComponentScan(basePackageClasses = { WebMvcConfiguration.class })
@EnableWebMvc
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
////
////    @Override
////    public void addFormatters(FormatterRegistry formatterRegistry) {
////        formatterRegistry.addConverter(new MyConverter());
////    }
////
////    @Override
////    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////        converters.add(new MyHttpMessageConverter());
////    }
//
//    // More overridden methods ...

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Bean
    @ConditionalOnBean(ViewResolver.class)
    @ConditionalOnMissingBean(name = "viewResolver", value = ContentNegotiatingViewResolver.class)
    public ContentNegotiatingViewResolver viewResolver(BeanFactory beanFactory) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(beanFactory.getBean(ContentNegotiationManager.class));
        // ContentNegotiatingViewResolver uses all the other view resolvers to locate
        // a view so it should have a high precedence
        MappingJackson2JsonView mappingJackson2JsonView = beanFactory.getBean(MappingJackson2JsonView.class);
        mappingJackson2JsonView.setPrettyPrint(false);
        mappingJackson2JsonView.setDisableCaching(true);
        Set<String> jsonParameterNames = new HashSet<>();
        jsonParameterNames.add("jsonp");
        jsonParameterNames.add("callback");
        mappingJackson2JsonView.setJsonpParameterNames(jsonParameterNames);
        List<View> defaultViews = new ArrayList<>();
        defaultViews.add(mappingJackson2JsonView);
        resolver.setDefaultViews(defaultViews);
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return resolver;
    }



//    @Bean
//    @ConditionalOnBean({WebMvcProperties.View.class})
//    @ConditionalOnMissingBean
//    public BeanNameViewResolver beanNameViewResolver() {
//        BeanNameViewResolver resolver = new BeanNameViewResolver();
//        resolver.setOrder(2147483637);
//        return resolver;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public InternalResourceViewResolver defaultViewResolver() {
//        InternalResourceViewResolver resolver =         return resolver;new InternalResourceViewResolver();
//        resolver.setPrefix(this.mvcProperties.getView().getPrefix());
//        esolver.setSuffix(this.mvcProperties.getView().getSuffix());
//
//    }
//
//    @Bean
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }

}
