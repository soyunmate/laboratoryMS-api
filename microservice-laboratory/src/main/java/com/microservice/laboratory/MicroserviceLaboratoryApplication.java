package com.microservice.laboratory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceLaboratoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLaboratoryApplication.class, args);
	}

}
