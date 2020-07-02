package com.factsdemo.guineaPigFacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Main method called to launch the SpringBoot application
 */

@SpringBootApplication
public class GuineaPigFactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuineaPigFactsApplication.class, args);
	}
}

