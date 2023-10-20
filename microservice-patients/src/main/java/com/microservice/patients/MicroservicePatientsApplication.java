package com.microservice.patients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicePatientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePatientsApplication.class, args);
	}

}
