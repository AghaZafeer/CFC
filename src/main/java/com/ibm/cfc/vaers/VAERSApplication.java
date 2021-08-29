package com.ibm.cfc.vaers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VAERSApplication {

	public static void main(String[] args) {
		SpringApplication.run(VAERSApplication.class, args);
	}

}
