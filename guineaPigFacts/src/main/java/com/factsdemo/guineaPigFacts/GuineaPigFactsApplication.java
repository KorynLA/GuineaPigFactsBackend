package com.factsdemo.guineaPigFacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.beans.factory.annotation.Autowired;

//SpringApplication.run() method to launch an application.
@SpringBootApplication
public class GuineaPigFactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuineaPigFactsApplication.class, args);
	}
/*
	//Web endpoint that has 1 parameter. When parameter isn't given, default value is world.
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/facts")
	public String facts() {
		return "facts";
	}

 */
}
