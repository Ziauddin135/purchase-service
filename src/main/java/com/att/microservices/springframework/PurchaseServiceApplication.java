package com.att.microservices.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableAutoConfiguration 
@SpringCloudApplication
@ComponentScan(basePackages = "com.att.microservices.springframework")
@EnableDiscoveryClient
@EnableFeignClients/*(basePackages =  {"com.att.microservices.springframework.controllers", "com.att.microservices.springframework.services"})*/
//@EnableCircuitBreaker
public class PurchaseServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseServiceApplication.class, args);
	}
}
