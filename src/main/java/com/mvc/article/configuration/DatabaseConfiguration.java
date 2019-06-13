package com.mvc.article.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Profile("localDB")
    public DataSource localSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/homework2");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("123");
        return  driverManagerDataSource;
    }

    @Bean
    @Profile("serverDB")
    public DataSource serverSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/homework2");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("123");
        return  driverManagerDataSource;
    }

    @Bean
    @Profile("memory")
    public DataSource memory(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);
        embeddedDatabaseBuilder.addScript("classPath:/static/sql/createTable.sql");
        //classPth is from resource
        return embeddedDatabaseBuilder.build();
    }
}
