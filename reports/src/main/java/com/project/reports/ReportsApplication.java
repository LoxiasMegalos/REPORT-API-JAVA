package com.project.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
public class ReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsApplication.class, args);
	}

}
