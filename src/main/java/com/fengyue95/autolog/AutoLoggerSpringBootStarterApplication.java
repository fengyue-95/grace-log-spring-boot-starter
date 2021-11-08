package com.fengyue95.autolog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"applicationContext.xml"})
public class AutoLoggerSpringBootStarterApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutoLoggerSpringBootStarterApplication.class, args);
	}

}
