package com.bellagnech.patientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.bellagnech.patientmanagement", "com.example.patientmanagement"})
@EnableJpaRepositories(basePackages = "com.example.patientmanagement.repositories")
@EntityScan(basePackages = "com.example.patientmanagement.entities")
public class PatientmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientmanagementApplication.class, args);
	}
}
