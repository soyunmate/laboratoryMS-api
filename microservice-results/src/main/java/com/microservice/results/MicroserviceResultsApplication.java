package com.microservice.results;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceResultsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceResultsApplication.class, args);
	}

}
