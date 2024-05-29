package com.kc.te;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.*;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TokenExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenExchangeApplication.class, args);
	}

}
