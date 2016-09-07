package net.cloudkit.phecda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("net.cloudkit.phecda.domain.model")
@EnableJpaRepositories(basePackages = "net.cloudkit.phecda.domain")
@EnableTransactionManagement

// @Configuration
// @EnableAutoConfiguration
// @ComponentScan(basePackages = "net.cloudkit.ecosystem")

// @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class PhecdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhecdaApplication.class, args);
	}
}
