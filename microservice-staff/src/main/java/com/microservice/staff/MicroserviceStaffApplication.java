package com.microservice.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceStaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceStaffApplication.class, args);
	}

}
