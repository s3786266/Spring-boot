// code causes error when trying to run the spring boot application
// need to config and maybe include the username and password

package com.pluralsight.conferencedemo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration

public class PersistenceConfiguration {

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/conference_app");
        builder.username("postgres");
        builder.password("Yegao1010");
        System.out.println("My custom datasource bean has been initialised and set");
        return builder.build();
    }
}
