package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication annotation includes @EnableJpaRepositories implicitly
@SpringBootApplication
public class OnlineShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
	}

}


/**
This is the entry point of the Spring Boot application. The SpringApplication.run() method:

Starts the Spring application context.
Creates and registers all the Spring beans.
Performs class path scans.
Sets up default configurations.
Starts the embedded server (e.g., Tomcat).


The @SpringBootApplication annotation is a convenience annotation that combines three important annotations:

@EnableAutoConfiguration: Enables Spring Boot’s auto-configuration mechanism, which attempts to automatically configure your 
Spring application based on the jar dependencies you have added.
@ComponentScan: Enables component-scanning, which allows Spring to find and register beans within the specified package and sub-packages. 
By default, it scans the package of the class with this annotation.
@Configuration: Marks the class as a source of bean definitions for the application context.
It implicitly includes @EnableJpaRepositories when JPA is on the classpath. This annotation enables the detection of Spring Data repositories
**/