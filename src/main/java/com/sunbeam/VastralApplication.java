package com.sunbeam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.sunbeam.entities")
@EnableJpaRepositories(basePackages = "com.sunbeam.daos")
public class VastralApplication {

	public static void main(String[] args) {
		SpringApplication.run(VastralApplication.class, args);
	}

}
