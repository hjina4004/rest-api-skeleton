package com.lmfriends.lmfdspost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LmfdsPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmfdsPostApplication.class, args);
	}

}
