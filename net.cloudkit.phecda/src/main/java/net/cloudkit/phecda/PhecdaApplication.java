package net.cloudkit.phecda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EntityScan("net.cloudkit.phecda.domain.model")
@EnableJpaRepositories(basePackages = "net.cloudkit.phecda.domain")
@EnableTransactionManagement
// @Configuration @EnableAutoConfiguration @ComponentScan(basePackages = "net.cloudkit.phecda")
// @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class PhecdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhecdaApplication.class, args);
	}

}
