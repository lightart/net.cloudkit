//package net.cloudkit.phecda.infrastructure.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@PropertySource("classpath:/database.properties")
//public class DefaultDataSourceConfiguration {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getRequiredProperty("dataSource.driverClassName"));
//        dataSource.setUrl(env.getRequiredProperty("dataSource.url"));
//        dataSource.setUsername(env.getRequiredProperty("dataSource.username"));
//        dataSource.setPassword(env.getRequiredProperty("dataSource.password"));
//        return dataSource;
//    }
//}
