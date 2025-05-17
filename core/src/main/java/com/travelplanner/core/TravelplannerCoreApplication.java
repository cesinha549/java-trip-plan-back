package com.travelplanner.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.travelplanner"
})
@EntityScan(basePackages = "com.travelplanner")
public class TravelplannerCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelplannerCoreApplication.class, args);
	}

}
