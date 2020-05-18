package com.example.simple_micro_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SimpleMicroServiceApplication {


	@Value("${TARGET:World Version 1.0}")
	String target;

	@RestController
	class HelloController {
		@GetMapping("/")
		String hello() {
			return "Hello " + target + "!";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleMicroServiceApplication.class, args);
	}

}
