package com.api.ticket.Apiticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiticketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiticketApplication.class, args);
	}

}
