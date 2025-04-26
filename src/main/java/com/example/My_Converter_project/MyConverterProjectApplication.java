package com.example.My_Converter_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

// start the Spring Boot application
@SpringBootApplication
@ComponentScan("com.example.My_Converter_project")
public class MyConverterProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyConverterProjectApplication.class, args);
	}


}
