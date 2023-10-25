package com.microservice.security;

import com.microservice.security.entities.RoleEntity;
import com.microservice.security.entities.UserEntity;
import com.microservice.security.entities.enums.ERole;
import com.microservice.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSecurityApplication.class, args);
	}


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;



	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity userEntity = UserEntity.builder()
					.email("admin@gmail.com")
					.staffId(1L)
					.username("admin")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.id(1L)
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			userRepository.save(userEntity);
		};
	}

}
